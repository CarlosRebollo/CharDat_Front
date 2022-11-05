package ies.quevedo.rpgchardatcompose.framework.screens.mainMenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.repository.remote.PersonajeRemoteRepository
import ies.quevedo.rpgchardatcompose.data.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainMenuVM @Inject constructor(
    private val personajeRepository: PersonajeRemoteRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<MainMenuContract.State> by lazy {
        MutableStateFlow(MainMenuContract.State())
    }
    val uiState: StateFlow<MainMenuContract.State> = _uiState

    fun handleEvent(
        event: MainMenuContract.Event,
    ) {
        when (event) {
            is MainMenuContract.Event.FetchPersonaje -> fetchPersonaje(event.id)
            is MainMenuContract.Event.ErrorConsumed -> showError()
        }
    }

    private fun fetchPersonaje(id: Int) {
        viewModelScope.launch {
            personajeRepository.getPersonaje(id)
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
                            MainMenuContract.State(
                                personaje = result.data,
                                isLoading = false
                            )
                        }
                    }
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