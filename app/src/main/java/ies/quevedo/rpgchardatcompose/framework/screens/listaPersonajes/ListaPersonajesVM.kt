package ies.quevedo.rpgchardatcompose.framework.screens.listaPersonajes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.repository.local.PersonajeLocalRepository
import ies.quevedo.rpgchardatcompose.domain.Personaje
import ies.quevedo.rpgchardatcompose.framework.screens.listaPersonajes.ListaPersonajesContract.Event
import ies.quevedo.rpgchardatcompose.framework.screens.listaPersonajes.ListaPersonajesContract.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListaPersonajesVM @Inject constructor(
    private val personajeLocalRepository: PersonajeLocalRepository
) : ViewModel() {

    init {
        handleEvent(Event.GetAllPersonajes)
    }

    private val _uiState: MutableStateFlow<State> by lazy {
        MutableStateFlow(State())
    }
    val uiState: StateFlow<State> = _uiState

    fun handleEvent(
        event: Event,
    ) {
        when (event) {
            is Event.GetPersonajeById -> getPersonajeById(event.id)
            Event.GetAllPersonajes -> getAllPersonajes()
            is Event.InsertPersonaje -> insertPersonaje(event.personaje)
            is Event.DeletePersonaje -> deletePersonaje(event.id)
            is Event.ErrorConsumed -> errorConsumed()
        }
    }

    private fun getPersonajeById(id: Int) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(personaje = personajeLocalRepository.getPersonaje(id)) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun getAllPersonajes() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(listaPersonajes = personajeLocalRepository.getPersonajes()) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun insertPersonaje(personaje: Personaje) {
        viewModelScope.launch {
            try {
                personajeLocalRepository.insertPersonaje(personaje = personaje)
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun deletePersonaje(id: Int) {
        viewModelScope.launch {
            try {
                personajeLocalRepository.deletePersonaje(id = id)
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun errorConsumed() {
        _uiState.update {
            it.copy(
                error = null
            )
        }
    }
}