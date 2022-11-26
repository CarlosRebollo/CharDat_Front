package ies.quevedo.rpgchardatcompose.framework.screens.personajes.addPersonaje

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.repository.local.PersonajeLocalRepository
import ies.quevedo.rpgchardatcompose.domain.Personaje
import ies.quevedo.rpgchardatcompose.framework.screens.personajes.addPersonaje.AddPersonajeContract.Event
import ies.quevedo.rpgchardatcompose.framework.screens.personajes.addPersonaje.AddPersonajeContract.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPersonajeVM @Inject constructor(
    private val personajeLocalRepository: PersonajeLocalRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<State> by lazy {
        MutableStateFlow(State())
    }
    val uiState: StateFlow<State> = _uiState

    fun handleEvent(event: Event) {
        when (event) {
            is Event.AddPersonaje -> addPersonaje(event.personaje)
            is Event.ShowError -> showError(event.error)
            Event.ErrorConsumed -> errorConsumed()
        }
    }

    private fun addPersonaje(personaje: Personaje) {
        viewModelScope.launch {
            try {
                personajeLocalRepository.insertPersonaje(personaje = personaje)
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun showError(error: String) {
        _uiState.update {
            it.copy(
                error = error
            )
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