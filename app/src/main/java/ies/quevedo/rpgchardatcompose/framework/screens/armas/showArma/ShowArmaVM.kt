package ies.quevedo.rpgchardatcompose.framework.screens.armas.showArma

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.repository.local.ArmaLocalRepository
import ies.quevedo.rpgchardatcompose.domain.Arma
import ies.quevedo.rpgchardatcompose.framework.screens.armas.showArma.ShowArmaContract.Event
import ies.quevedo.rpgchardatcompose.framework.screens.armas.showArma.ShowArmaContract.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowArmaVM @Inject constructor(
    private val armaLocalRepository: ArmaLocalRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<State> by lazy {
        MutableStateFlow(State())
    }
    val uiState: StateFlow<State> = _uiState

    fun handleEvent(
        event: Event,
    ) {
        when (event) {
            is Event.GetArma -> getArma(idArma = event.idArma)
            is Event.UpdateArma -> updateArma(arma = event.arma)
            is Event.ShowError -> showError(error = event.error)
            Event.ErrorConsumed -> errorConsumed()
        }
    }

    private fun getArma(idArma: Int) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(arma = armaLocalRepository.getArma(idArma = idArma)) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun updateArma(arma: Arma) {
        viewModelScope.launch {
            try {
                armaLocalRepository.updateArma(arma = arma)
                _uiState.update { it.copy(arma = null) }
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