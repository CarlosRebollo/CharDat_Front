package ies.quevedo.rpgchardatcompose.framework.screens.objetos.addObjeto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.repository.local.ObjetoLocalRepository
import ies.quevedo.rpgchardatcompose.domain.Objeto
import ies.quevedo.rpgchardatcompose.framework.screens.objetos.addObjeto.AddObjetoContract.Event
import ies.quevedo.rpgchardatcompose.framework.screens.objetos.addObjeto.AddObjetoContract.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddObjetoVM @Inject constructor(
    private val objetoLocalRepository: ObjetoLocalRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<State> by lazy {
        MutableStateFlow(State())
    }
    val uiState: StateFlow<State> = _uiState

    fun handleEvent(event: Event) {
        when (event) {
            is Event.AddObjeto -> addObjeto(objeto = event.objeto)
            is Event.ShowError -> showError(error = event.error)
            Event.ErrorConsumed -> errorConsumed()
        }
    }

    private fun addObjeto(objeto: Objeto) {
        viewModelScope.launch {
            try {
                objetoLocalRepository.insertObjeto(objeto = objeto)
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