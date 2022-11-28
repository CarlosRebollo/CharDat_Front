package ies.quevedo.rpgchardatcompose.framework.screens.armaduras.addArmadura

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.repository.local.ArmaduraLocalRepository
import ies.quevedo.rpgchardatcompose.domain.Armadura
import ies.quevedo.rpgchardatcompose.framework.screens.armaduras.addArmadura.AddArmaduraContract.Event
import ies.quevedo.rpgchardatcompose.framework.screens.armaduras.addArmadura.AddArmaduraContract.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddArmaduraVM @Inject constructor(
    private val armaduraLocalRepository: ArmaduraLocalRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<State> by lazy {
        MutableStateFlow(State())
    }
    val uiState: StateFlow<State> = _uiState

    fun handleEvent(event: Event) {
        when (event) {
            is Event.AddArmadura -> addArmadura(event.armadura)
            is Event.ShowError -> showError(event.error)
            Event.ErrorConsumed -> errorConsumed()
        }
    }

    private fun addArmadura(armadura: Armadura) {
        viewModelScope.launch {
            try {
                armaduraLocalRepository.insertArmadura(armadura = armadura)
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