package ies.quevedo.rpgchardatcompose.framework.screens.armas.listaArmas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.repository.local.ArmaLocalRepository
import ies.quevedo.rpgchardatcompose.domain.Arma
import ies.quevedo.rpgchardatcompose.framework.screens.armas.listaArmas.ListaArmasContract.Event
import ies.quevedo.rpgchardatcompose.framework.screens.armas.listaArmas.ListaArmasContract.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListaArmasVM @Inject constructor(
    private val armaLocalRepository: ArmaLocalRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<State> by lazy {
        MutableStateFlow(State())
    }
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    fun handleEvent(
        event: Event
    ) {
        when (event) {
            is Event.GetAllArmas -> getAllArmasByIdPersonaje(event.idPersonaje)
            is Event.DeleteArma -> deleteArma(event.arma)
            is Event.RestoreArma -> restoreArma()
            is Event.ShowError -> showError(event.error)
            Event.ErrorConsumed -> errorConsumed()
        }
    }

    private fun getArmaById(idArma: Int) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(arma = armaLocalRepository.getArma(id = idArma)) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun getAllArmasByIdPersonaje(idPersonaje: Int) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(listaArmas = armaLocalRepository.getArmas(idPJ = idPersonaje)) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun deleteArma(armaParaBorrar: Arma) {
        viewModelScope.launch {
            try {
                getArmaById(armaParaBorrar.id)
                _uiState.value.arma?.let { armaLocalRepository.deleteArma(it) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun restoreArma() {
        viewModelScope.launch {
            try {
                _uiState.value.arma?.let { armaLocalRepository.insertArma(it) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun showError(error: String?) {
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