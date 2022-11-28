package ies.quevedo.rpgchardatcompose.framework.screens.objetos.showObjeto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.repository.local.ObjetoLocalRepository
import ies.quevedo.rpgchardatcompose.domain.Objeto
import ies.quevedo.rpgchardatcompose.framework.screens.objetos.showObjeto.ShowObjetoContract.Event
import ies.quevedo.rpgchardatcompose.framework.screens.objetos.showObjeto.ShowObjetoContract.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowObjetoVM @Inject constructor(
    private val objetoLocalRepository: ObjetoLocalRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<State> by lazy {
        MutableStateFlow(State())
    }
    val uiState: StateFlow<State> = _uiState

    fun handleEvent(
        event: Event,
    ) {
        when (event) {
            is Event.GetObjeto -> getObjeto(idObjeto = event.idObjeto)
            is Event.UpdateObjeto -> updateObjeto(objeto = event.objeto)
            is Event.ShowError -> showError(error = event.error)
            Event.ErrorConsumed -> errorConsumed()
        }
    }

    private fun getObjeto(idObjeto: Int) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(objeto = objetoLocalRepository.getObjeto(idObjeto = idObjeto)) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun updateObjeto(objeto: Objeto) {
        viewModelScope.launch {
            try {
                objetoLocalRepository.updateObjeto(objeto = objeto)
                _uiState.update { it.copy(objeto = null) }
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