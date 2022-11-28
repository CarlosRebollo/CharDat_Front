package ies.quevedo.rpgchardatcompose.framework.screens.armas.addArma

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.repository.local.ArmaLocalRepository
import ies.quevedo.rpgchardatcompose.domain.Arma
import ies.quevedo.rpgchardatcompose.framework.screens.armas.addArma.AddArmaContract.Event
import ies.quevedo.rpgchardatcompose.framework.screens.armas.addArma.AddArmaContract.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddArmaVM @Inject constructor(
    private val armaLocalRepository: ArmaLocalRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<State> by lazy {
        MutableStateFlow(State())
    }
    val uiState: StateFlow<State> = _uiState

    fun handleEvent(event: Event) {
        when (event) {
            is Event.AddArma -> addArma(event.arma)
            is Event.ShowError -> showError(event.error)
            Event.ErrorConsumed -> errorConsumed()
        }
    }

    private fun addArma(arma: Arma) {
        viewModelScope.launch {
            try {
                armaLocalRepository.insertArma(arma = arma)
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