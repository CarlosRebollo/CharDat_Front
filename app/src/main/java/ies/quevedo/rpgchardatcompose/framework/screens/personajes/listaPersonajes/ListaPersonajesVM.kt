package ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.entities.UsuarioEntity
import ies.quevedo.rpgchardatcompose.data.repository.local.*
import ies.quevedo.rpgchardatcompose.data.repository.remote.PersonajeRemoteRepository
import ies.quevedo.rpgchardatcompose.data.utils.NetworkResult
import ies.quevedo.rpgchardatcompose.domain.Personaje
import ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes.ListaPersonajesContract.Event
import ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes.ListaPersonajesContract.State
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
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
            Event.GetAllPersonajes -> getAllPersonajes()
            is Event.GetPersonajeById -> getPersonajeById(idPersonaje = event.idPersonaje)
            is Event.DeleteAllRoom -> deleteAllRoom(listaPersonajes = event.listaPersonajes)
            is Event.InsertAllRoom -> insertAllRoom(listaPersonajes = event.listaPersonajes)
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

    private fun insertAllRoom(listaPersonajes: List<Personaje>?) {
        viewModelScope.launch {
            try {
                listaPersonajes?.forEach { personaje ->
                    personaje.let { personajeLocalRepository.insertPersonaje(it) }
                    personaje.armas?.let { armaLocalRepository.insertAllArmas(it) }
                    personaje.armaduras?.let { armaduraLocalRepository.insertAllArmaduras(it) }
                    personaje.escudos?.let { escudoLocalRepository.insertAllEscudos(it) }
                    personaje.objetos?.let { objetoLocalRepository.insertAllObjetos(it) }
                }
                _uiState.update {
                    it.copy(
                        listaPersonajes = listaPersonajes,
                        listaPersonajesDescargados = null,
                        respuestaExitosaDownload = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun deleteAllRoom(listaPersonajes: List<Personaje>?) {
        viewModelScope.launch {
            try {
                listaPersonajes?.forEach { personaje ->
                    deletePersonaje(personaje = personaje)
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
                        is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is NetworkResult.Success -> _uiState.update {
                            it.copy(
                                listaPersonajesDescargados = result.data,
                                respuestaExitosaDownload = true,
                                isLoading = false
                            )
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
                armaduraLocalRepository.deleteAllArmaduras(
                    listaArmaduras = armaduraLocalRepository.getArmaduras(
                        idPJ = personaje.id
                    )
                )
                armaLocalRepository.deleteAllArmas(
                    listaArmas = armaLocalRepository.getArmas(
                        idPJ = personaje.id
                    )
                )
                escudoLocalRepository.deleteAllEscudos(
                    listaEscudos = escudoLocalRepository.getEscudos(
                        idPJ = personaje.id
                    )
                )
                objetoLocalRepository.deleteAllObjetos(
                    listaObjetos = objetoLocalRepository.getObjetos(
                        idPJ = personaje.id
                    )
                )
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

    private fun showError(error: String) {
        _uiState.update {
            it.copy(error = error)
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

    private fun errorConsumed() {
        _uiState.update {
            it.copy(error = null)
        }
    }
}