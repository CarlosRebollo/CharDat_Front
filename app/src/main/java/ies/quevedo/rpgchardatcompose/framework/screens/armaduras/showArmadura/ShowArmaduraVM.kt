package ies.quevedo.rpgchardatcompose.framework.screens.armaduras.showArmadura

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.repository.local.ArmaduraLocalRepository
import ies.quevedo.rpgchardatcompose.domain.Armadura
import ies.quevedo.rpgchardatcompose.framework.screens.armaduras.showArmadura.ShowArmaduraContract.Event
import ies.quevedo.rpgchardatcompose.framework.screens.armaduras.showArmadura.ShowArmaduraContract.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowArmaduraVM @Inject constructor(
    private val armaduraLocalRepository: ArmaduraLocalRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<State> by lazy {
        MutableStateFlow(State())
    }
    val uiState: StateFlow<State> = _uiState

    fun handleEvent(
        event: Event,
    ) {
        when (event) {
            is Event.GetArmadura -> getArma(idArmadura = event.idArmadura)
            is Event.UpdateArma -> updateArma(armadura = event.armadura)
            is Event.ShowError -> showError(error = event.error)
            Event.ErrorConsumed -> errorConsumed()
        }
    }

    private fun getArma(idArmadura: Int) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(arma = armaduraLocalRepository.getArmadura(idArmadura = idArmadura)) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun updateArma(armadura: Armadura) {
        viewModelScope.launch {
            try {
                armaduraLocalRepository.updateArmadura(armadura = armadura)
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