package ies.quevedo.rpgchardatcompose.framework.screens.escudos.showEscudo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.repository.local.EscudoLocalRepository
import ies.quevedo.rpgchardatcompose.domain.Escudo
import ies.quevedo.rpgchardatcompose.framework.screens.escudos.showEscudo.ShowEscudoContract.Event
import ies.quevedo.rpgchardatcompose.framework.screens.escudos.showEscudo.ShowEscudoContract.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowEscudoVM @Inject constructor(
    private val escudoLocalRepository: EscudoLocalRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<State> by lazy {
        MutableStateFlow(State())
    }
    val uiState: StateFlow<State> = _uiState

    fun handleEvent(
        event: Event,
    ) {
        when (event) {
            is Event.GetEscudo -> getEscudo(idEscudo = event.idEscudo)
            is Event.UpdateEscudo -> updateEscudo(escudo = event.escudo)
            is Event.ShowError -> showError(error = event.error)
            Event.ErrorConsumed -> errorConsumed()
        }
    }

    private fun getEscudo(idEscudo: Int) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(escudo = escudoLocalRepository.getEscudo(idEscudo = idEscudo)) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun updateEscudo(escudo: Escudo) {
        viewModelScope.launch {
            try {
                escudoLocalRepository.updateEscudo(escudo = escudo)
                _uiState.update { it.copy(escudo = null) }
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