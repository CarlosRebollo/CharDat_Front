package ies.quevedo.rpgchardatcompose.framework.screens.personajes.showPersonaje

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.repository.local.PersonajeLocalRepository
import ies.quevedo.rpgchardatcompose.domain.Personaje
import ies.quevedo.rpgchardatcompose.framework.screens.personajes.showPersonaje.ShowPersonajeContract.Event
import ies.quevedo.rpgchardatcompose.framework.screens.personajes.showPersonaje.ShowPersonajeContract.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowPersonajeVM @Inject constructor(
    private val personajeLocalRepository: PersonajeLocalRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<State> by lazy {
        MutableStateFlow(State())
    }
    val uiState: StateFlow<State> = _uiState

    fun handleEvent(
        event: Event,
    ) {
        when (event) {
            is Event.GetPersonaje -> getPersonaje(idPersonaje = event.idPersonaje)
            is Event.UpdatePersonaje -> updatePersonaje(personaje = event.personaje)
            is Event.ShowError -> showError(error = event.error)
            Event.ErrorConsumed -> errorConsumed()
        }
    }

    private fun getPersonaje(idPersonaje: Int) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(personaje = personajeLocalRepository.getPersonaje(id = idPersonaje)) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun updatePersonaje(personaje: Personaje) {
        viewModelScope.launch {
            try {
                personajeLocalRepository.updatePersonaje(personaje = personaje)
                _uiState.update { it.copy(personaje = null) }
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