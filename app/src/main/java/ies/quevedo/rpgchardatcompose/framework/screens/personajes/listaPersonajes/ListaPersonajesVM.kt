package ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.repository.local.*
import ies.quevedo.rpgchardatcompose.data.repository.remote.PersonajeRemoteRepository
import ies.quevedo.rpgchardatcompose.data.utils.NetworkResult
import ies.quevedo.rpgchardatcompose.domain.Personaje
import ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes.ListaPersonajesContract.Event
import ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes.ListaPersonajesContract.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ListaPersonajesVM @Inject constructor(
    private val armaduraLocalRepository: ArmaduraLocalRepository,
    private val armaLocalRepository: ArmaLocalRepository,
    private val escudoLocalRepository: EscudoLocalRepository,
    private val objetoLocalRepository: ObjetoLocalRepository,
    private val personajeLocalRepository: PersonajeLocalRepository,
    private val personajeRemoteRepository: PersonajeRemoteRepository,
    private val usuarioLocalRepository: UsuarioLocalRepository
) : ViewModel() {

    init {
        getAllPersonajes()
    }

    private val _uiState: MutableStateFlow<State> by lazy {
        MutableStateFlow(State())
    }
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    fun handleEvent(
        event: Event,
    ) {
        when (event) {
            Event.GetAllPersonajes -> getAllPersonajes()
            is Event.GetPersonajeById -> getPersonajeById(idPersonaje = event.idPersonaje)
            Event.DeleteAllRoom -> deleteAllRoom()
            is Event.InsertAllRoom -> insertAllRoom(listaPersonajesDescargados = event.listaPersonajesDescargados)
            is Event.DeletePersonaje -> deletePersonaje(personaje = event.personaje)
            is Event.DownloadPersonajes -> downloadPersonajes(token = event.token)
            is Event.UploadPersonajes -> uploadPersonajes(
                token = event.token,
                personajes = event.personajes
            )
            Event.GetTokenLocal -> getTokenLocal()
            Event.BorrarTokenLocal -> borrarTokenLocal()
            is Event.ShowError -> showError(error = event.error)
            is Event.ErrorConsumed -> errorConsumed()
            Event.RespuestaExitosaConsumed -> respuestaExitosaConsumed()
            Event.ShowDialog -> showDialog()
            Event.DismissDialog -> dismissDialog()
        }
    }

    private fun getAllPersonajes() {
        viewModelScope.launch {
            try {
                val personajesCompletos = personajeLocalRepository.getPersonajes()
                personajesCompletos.forEach { personaje ->
                    personaje.armas = armaLocalRepository.getArmas(personaje.id)
                    personaje.armaduras = armaduraLocalRepository.getArmaduras(personaje.id)
                    personaje.escudos = escudoLocalRepository.getEscudos(personaje.id)
                    personaje.objetos = objetoLocalRepository.getObjetos(personaje.id)
                }
                _uiState.update { it.copy(listaPersonajes = personajesCompletos) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun downloadPersonajes(token: String) {
        viewModelScope.launch {
            var personajesDescargados: List<Personaje>? = null
            personajeRemoteRepository.getPersonajes(token = token)
                .catch(action = { cause ->
                    _uiState.update { it.copy(error = cause.message, isLoading = false) }
                    Timber.tag("Error").e(cause)
                })
                .collect { result ->
                    when (result) {
                        is NetworkResult.Error -> {
                            _uiState.update { it.copy(error = result.message, isLoading = false) }
                            Timber.tag("Error").e(result.message)
                        }
                        is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is NetworkResult.Success -> {
                            personajesDescargados = result.data
                            _uiState.update {
                                it.copy(
                                    listaPersonajesDescargados = result.data
                                )
                            }
                        }
                    }
                }
            deleteAllRoom()
            insertAllRoom(personajesDescargados)
            getAllPersonajes()
        }
    }

    private fun deleteAllRoom() {
        viewModelScope.launch {
            try {
                armaduraLocalRepository.deleteAllArmaduras()
                armaLocalRepository.deleteAllArmas()
                escudoLocalRepository.deleteAllEscudos()
                objetoLocalRepository.deleteAllObjetos()
                personajeLocalRepository.deleteAllPersonajes()
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun insertAllRoom(listaPersonajesDescargados: List<Personaje>?) {
        viewModelScope.launch {
            try {
                listaPersonajesDescargados?.forEach { personaje ->
                    personaje.let { personajeLocalRepository.insertPersonaje(it) }
                    personaje.armas?.let { armaLocalRepository.insertAllArmas(it) }
                    personaje.armaduras?.let { armaduraLocalRepository.insertAllArmaduras(it) }
                    personaje.escudos?.let { escudoLocalRepository.insertAllEscudos(it) }
                    personaje.objetos?.let { objetoLocalRepository.insertAllObjetos(it) }
                }
                _uiState.update {
                    it.copy(
                        listaPersonajesDescargados = null,
                        respuestaExitosaDownload = true,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun uploadPersonajes(token: String, personajes: List<Personaje>) {
        viewModelScope.launch {
            personajeRemoteRepository.postPersonajes(token = token, personajes = personajes)
                .catch(action = { cause ->
                    _uiState.update { it.copy(error = cause.message, isLoading = false) }
                    Timber.tag("Error").e(cause)
                })
                .collect { result ->
                    when (result) {
                        is NetworkResult.Error -> {
                            _uiState.update { it.copy(error = result.message, isLoading = false) }
                            Timber.tag("Error").e(result.message)
                        }
                        is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is NetworkResult.Success -> _uiState.update {
                            it.copy(respuestaExitosaUpload = true, isLoading = false)
                        }
                    }
                }
        }
    }

    private fun getPersonajeById(idPersonaje: Int) {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(
                        personaje = personajeLocalRepository.getPersonaje(
                            idPersonaje
                        )
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun deletePersonaje(personaje: Personaje) {
        viewModelScope.launch {
            try {
                armaduraLocalRepository.deleteAllArmadurasDelPersonaje(idPJ = personaje.id)
                armaLocalRepository.deleteAllArmasDelPersonaje(idPJ = personaje.id)
                escudoLocalRepository.deleteAllEscudosDelPersonaje(idPJ = personaje.id)
                objetoLocalRepository.deleteAllObjetosDelPersonaje(idPJ = personaje.id)
                personajeLocalRepository.deletePersonaje(personaje = personaje)
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun getTokenLocal() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(usuarioLogueado = usuarioLocalRepository.getTokenLocal()) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun borrarTokenLocal() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(usuarioLogueado = null) }
                usuarioLocalRepository.borrarTokenLocal()
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun respuestaExitosaConsumed() {
        _uiState.update {
            it.copy(
                respuestaExitosaUpload = false,
                respuestaExitosaDownload = false
            )
        }
    }

    private fun showError(error: String) {
        _uiState.update { it.copy(error = error) }
    }

    private fun errorConsumed() {
        _uiState.update { it.copy(error = null) }
    }

    private fun showDialog() {
        _uiState.update { it.copy(showDialog = true) }
    }

    private fun dismissDialog() {
        _uiState.update { it.copy(showDialog = false) }
    }

}