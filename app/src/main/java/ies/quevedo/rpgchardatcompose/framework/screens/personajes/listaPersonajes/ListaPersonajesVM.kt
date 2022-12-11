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

    private val _uiState: MutableStateFlow<State> by lazy {
        MutableStateFlow(State())
    }
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    fun handleEvent(
        event: Event,
    ) {
        when (event) {
            is Event.DeletePersonaje -> deletePersonaje(personaje = event.personaje)
            is Event.DownloadPersonajes -> downloadPersonajes(token = event.token)
            is Event.ErrorConsumed -> errorConsumed()
            is Event.GetPersonajeById -> getPersonajeById(idPersonaje = event.idPersonaje)
            is Event.InsertAllRoom -> insertAllRoom(listaPersonajesDescargados = event.listaPersonajesDescargados)
            is Event.ShowError -> showError(error = event.error)
            is Event.UploadPersonajes -> uploadPersonajes(
                token = event.token,
                personajes = event.personajes
            )
            Event.BorrarTokenLocal -> borrarTokenLocal()
            Event.DeleteAllRoom -> deleteAllRoom()
            Event.DismissDialog -> dismissDialog()
            Event.GetAllPersonajesConObjetos -> getAllPersonajesConObjetos()
            Event.GetTokenLocal -> getTokenLocal()
            Event.RespuestaExitosaConsumed -> respuestaExitosaConsumed()
            Event.ShowDialog -> showDialog()
        }
    }

    private fun getAllPersonajesConObjetos() {
        viewModelScope.launch {
            try {
                val personajesCompletos: List<Personaje> = personajeLocalRepository.getPersonajes()
                personajesCompletos.forEach { personaje ->
                    personaje.armas = armaLocalRepository.getArmas(personaje.id)
                    personaje.armaduras = armaduraLocalRepository.getArmaduras(personaje.id)
                    personaje.escudos = escudoLocalRepository.getEscudos(personaje.id)
                    personaje.objetos = objetoLocalRepository.getObjetos(personaje.id)
                }
                _uiState.update { it.copy(listaPersonajesCompletos = personajesCompletos) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun downloadPersonajes(token: String) {
        viewModelScope.launch {
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
                        is NetworkResult.ApiError -> {
                            _uiState.update {
                                it.copy(
                                    error = result.apiError?.msg,
                                    isLoading = false
                                )
                            }
                            Timber.tag("Error").e(result.apiError?.msg)
                        }
                        is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is NetworkResult.Success ->
                            _uiState.update {
                                it.copy(listaPersonajesDescargados = result.data)
                            }
                    }
                }
            if (_uiState.value.listaPersonajesDescargados?.isNotEmpty()!!) {
                deleteAllRoom()
                withContext(Dispatchers.IO) {
                    Thread.sleep(1000)
                }
                insertAllRoom(_uiState.value.listaPersonajesDescargados)
                withContext(Dispatchers.IO) {
                    Thread.sleep(1000)
                }
                getAllPersonajesConObjetos()
            }
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
        try {
            viewModelScope.launch {
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
            }
        } catch (e: Exception) {
            _uiState.update { it.copy(error = e.message) }
        }
    }

    private fun uploadPersonajes(token: String, personajes: List<Personaje>) {
        viewModelScope.launch {
            if (personajes.isNotEmpty()) {
                personajeRemoteRepository.postPersonajes(token = token, personajes = personajes)
                    .catch(action = { cause ->
                        _uiState.update { it.copy(error = cause.message, isLoading = false) }
                        Timber.tag("Error").e(cause)
                    })
                    .collect { result ->
                        when (result) {
                            is NetworkResult.Error -> {
                                _uiState.update {
                                    it.copy(
                                        error = result.message,
                                        isLoading = false
                                    )
                                }
                                Timber.tag("Error").e(result.message)
                            }
                            is NetworkResult.ApiError -> {
                                _uiState.update {
                                    it.copy(
                                        error = result.apiError?.msg,
                                        isLoading = false
                                    )
                                }
                                Timber.tag("Error").e(result.apiError?.msg)
                            }
                            is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                            is NetworkResult.Success -> _uiState.update {
                                it.copy(respuestaExitosaUpload = true, isLoading = false)
                            }
                        }
                    }
            } else {
                _uiState.update { it.copy(error = "No hay personajes que guardar") }
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
            getAllPersonajesConObjetos()
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