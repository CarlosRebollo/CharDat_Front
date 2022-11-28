package ies.quevedo.rpgchardatcompose.framework.screens.armaduras.listaArmaduras

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.repository.local.ArmaduraLocalRepository
import ies.quevedo.rpgchardatcompose.domain.Armadura
import ies.quevedo.rpgchardatcompose.framework.screens.armaduras.listaArmaduras.ListaArmadurasContract.Event
import ies.quevedo.rpgchardatcompose.framework.screens.armaduras.listaArmaduras.ListaArmadurasContract.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListaArmadurasVM @Inject constructor(
    private val armaduraLocalRepository: ArmaduraLocalRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<State> by lazy {
        MutableStateFlow(State())
    }
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    fun handleEvent(event: Event) {
        when (event) {
            is Event.GetAllArmaduras -> getAllArmadurasByIdPersonaje(idPersonaje = event.idPersonaje)
            is Event.DeleteArmadura -> deleteArmadura(armadura = event.armadura)
            is Event.ShowError -> showError(error = event.error)
            Event.ErrorConsumed -> errorConsumed()
        }
    }

    private fun getAllArmadurasByIdPersonaje(idPersonaje: Int) {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(listaArmaduras = armaduraLocalRepository.getArmaduras(idPJ = idPersonaje))
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun deleteArmadura(armadura: Armadura) {
        viewModelScope.launch {
            try {
                armaduraLocalRepository.deleteArmadura(armadura = armadura)
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