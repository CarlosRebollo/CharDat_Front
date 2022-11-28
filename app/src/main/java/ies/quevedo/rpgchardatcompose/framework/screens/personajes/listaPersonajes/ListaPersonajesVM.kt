package ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.repository.local.PersonajeLocalRepository
import ies.quevedo.rpgchardatcompose.domain.Personaje
import ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes.ListaPersonajesContract.Event
import ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes.ListaPersonajesContract.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListaPersonajesVM @Inject constructor(
    private val personajeLocalRepository: PersonajeLocalRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<State> by lazy {
        MutableStateFlow(State())
    }
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    fun handleEvent(
        event: Event,
    ) {
        when (event) {
            is Event.GetPersonajeById -> getPersonajeById(idPersonaje = event.idPersonaje)
            Event.GetAllPersonajes -> getAllPersonajes()
            is Event.DeletePersonaje -> deletePersonaje(personaje = event.personaje)
            is Event.ShowError -> showError(error = event.error)
            is Event.ErrorConsumed -> errorConsumed()
        }
    }

    private fun getPersonajeById(idPersonaje: Int) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(personaje = personajeLocalRepository.getPersonaje(idPersonaje)) }
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

    private fun deletePersonaje(personaje: Personaje) {
        viewModelScope.launch {
            try {
                personajeLocalRepository.deletePersonaje(personaje = personaje)
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

    private fun errorConsumed() {
        _uiState.update {
            it.copy(error = null)
        }
    }
}