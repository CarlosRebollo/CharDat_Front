package ies.quevedo.rpgchardatcompose.framework.screens.mainMenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.repository.local.PersonajeLocalRepository
import ies.quevedo.rpgchardatcompose.framework.screens.mainMenu.MainMenuContract.Event
import ies.quevedo.rpgchardatcompose.framework.screens.mainMenu.MainMenuContract.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainMenuVM @Inject constructor(
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
            is Event.GetPersonaje -> fetchPersonaje(event.id)
            is Event.ErrorConsumed -> showError()
        }
    }

    private fun fetchPersonaje(id: Int) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(personaje = personajeLocalRepository.getPersonaje(idPersonaje = id)) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun showError() {
        _uiState.update {
            it.copy(
                error = null
            )
        }
    }
}