package ies.quevedo.rpgchardatcompose.framework.screens.objetos.listaObjetos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.repository.local.ObjetoLocalRepository
import ies.quevedo.rpgchardatcompose.domain.Objeto
import ies.quevedo.rpgchardatcompose.framework.screens.objetos.listaObjetos.ListaObjetosContract.Event
import ies.quevedo.rpgchardatcompose.framework.screens.objetos.listaObjetos.ListaObjetosContract.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListaObjetosVM @Inject constructor(
    private val objetoLocalRepository: ObjetoLocalRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<State> by lazy {
        MutableStateFlow(State())
    }
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    fun handleEvent(
        event: Event
    ) {
        when (event) {
            is Event.GetAllObjetos -> getAllObjetosByIDPersonaje(idPersonaje = event.idPersonaje)
            is Event.DeleteObjeto -> deleteObjeto(objeto = event.objeto)
            is Event.ShowError -> showError(error = event.error)
            Event.ErrorConsumed -> errorConsumed()
        }
    }

    private fun getAllObjetosByIDPersonaje(idPersonaje: Int) {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(listaObjetos = objetoLocalRepository.getObjetos(idPJ = idPersonaje))
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun deleteObjeto(objeto: Objeto) {
        viewModelScope.launch {
            try {
                objetoLocalRepository.deleteObjeto(objeto = objeto)
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