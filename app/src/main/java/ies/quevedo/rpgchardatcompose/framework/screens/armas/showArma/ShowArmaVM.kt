package ies.quevedo.rpgchardatcompose.framework.screens.armas.showArma

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.repository.local.ArmaLocalRepository
import ies.quevedo.rpgchardatcompose.domain.Arma
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowArmaVM @Inject constructor(
    private val armaLocalRepository: ArmaLocalRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<ShowArmaContract.State> by lazy {
        MutableStateFlow(ShowArmaContract.State())
    }
    val uiState: StateFlow<ShowArmaContract.State> = _uiState

    fun handleEvent(
        event: ShowArmaContract.Event,
    ) {
        when (event) {
            is ShowArmaContract.Event.GetArma -> getArma(event.idArma)
            is ShowArmaContract.Event.UpdateArma -> updateArma(event.arma)
            is ShowArmaContract.Event.ShowError -> showError(event.error)
            ShowArmaContract.Event.ErrorConsumed -> errorConsumed()
        }
    }

    private fun getArma(idArma: Int) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(arma = armaLocalRepository.getArma(id = idArma)) }
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