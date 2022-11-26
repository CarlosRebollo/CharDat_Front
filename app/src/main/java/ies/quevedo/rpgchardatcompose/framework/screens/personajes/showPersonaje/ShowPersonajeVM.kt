package ies.quevedo.rpgchardatcompose.framework.screens.personajes.showPersonaje

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.repository.local.PersonajeLocalRepository
import ies.quevedo.rpgchardatcompose.domain.Personaje
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowPersonajeVM @Inject constructor(
    private val personajeLocalRepository: PersonajeLocalRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<ShowPersonajeContract.State> by lazy {
        MutableStateFlow(ShowPersonajeContract.State())
    }
    val uiState: StateFlow<ShowPersonajeContract.State> = _uiState

    fun handleEvent(
        event: ShowPersonajeContract.Event,
    ) {
        when (event) {
            is ShowPersonajeContract.Event.GetPersonaje -> getPersonaje(event.id)
            is ShowPersonajeContract.Event.UpdatePersonaje -> updatePersonaje(event.personaje)
            is ShowPersonajeContract.Event.ShowError -> showError(event.error)
            ShowPersonajeContract.Event.ErrorConsumed -> errorConsumed()
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

    private fun updatePersonaje(personajeEditado: Personaje) {
        viewModelScope.launch {
            try {
                personajeLocalRepository.updatePersonaje(personaje = personajeEditado)
                _uiState.update { it.copy(personaje = null) }
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