package ies.quevedo.rpgchardatcompose.framework.screens.mainMenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.repository.local.PersonajeLocalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainMenuVM @Inject constructor(
    private val personajeLocalRepository: PersonajeLocalRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<MainMenuContract.State> by lazy {
        MutableStateFlow(MainMenuContract.State())
    }
    val uiState: StateFlow<MainMenuContract.State> = _uiState

    fun handleEvent(
        event: MainMenuContract.Event,
    ) {
        when (event) {
            is MainMenuContract.Event.GetPersonaje -> fetchPersonaje(event.id)
            is MainMenuContract.Event.ErrorConsumed -> showError()
        }
    }

    private fun fetchPersonaje(id: Int) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(personaje = personajeLocalRepository.getPersonaje(id = id)) }
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