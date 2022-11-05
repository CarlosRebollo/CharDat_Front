package ies.quevedo.rpgchardatcompose.framework.screens.addPersonaje

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.repository.remote.PersonajeRemoteRepository
import ies.quevedo.rpgchardatcompose.data.utils.NetworkResult
import ies.quevedo.rpgchardatcompose.domain.Personaje
import ies.quevedo.rpgchardatcompose.framework.screens.addPersonaje.AddPersonajeContract.Event
import ies.quevedo.rpgchardatcompose.framework.screens.addPersonaje.AddPersonajeContract.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddPersonajeVM @Inject constructor(
    private val personajeRepository: PersonajeRemoteRepository
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

    private fun showError(error: String) {
        _uiState.update {
            it.copy(
                error = error,
                isLoading = false,
                success = false
            )
        }
    }

    private fun addPersonaje(personaje: Personaje) {
        viewModelScope.launch {
            personajeRepository.insertPersonaje(personaje)
                .catch(action = { cause ->
                    _uiState.update {
                        it.copy(
                            error = cause.message,
                            isLoading = false,
                            success = false
                        )
                    }
                    Timber.tag("Error").e(cause)
                })
                .collect { result ->
                    when (result) {
                        is NetworkResult.Error -> {
                            _uiState.update {
                                it.copy(
                                    error = result.message,
                                    isLoading = false,
                                    success = false
                                )
                            }
                            Timber.tag("Error").e(result.message)
                        }
                        is NetworkResult.Loading -> _uiState.update {
                            it.copy(
                                isLoading = true,
                                success = false
                            )
                        }
                        is NetworkResult.Success -> _uiState.update {
                            State(
                                isLoading = false,
                                success = true
                            )
                        }
                    }
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

    private val _personaje = MutableStateFlow(Personaje())
    val personaje: StateFlow<Personaje> = _personaje

    fun setPersonaje(personaje: Personaje) {
        _personaje.value = personaje
    }
}