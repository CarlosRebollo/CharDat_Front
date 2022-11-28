package ies.quevedo.rpgchardatcompose.framework.screens.escudos.listaEscudos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.repository.local.EscudoLocalRepository
import ies.quevedo.rpgchardatcompose.domain.Escudo
import ies.quevedo.rpgchardatcompose.framework.screens.escudos.listaEscudos.ListaEscudosContract.Event
import ies.quevedo.rpgchardatcompose.framework.screens.escudos.listaEscudos.ListaEscudosContract.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListaEscudosVM @Inject constructor(
    private val escudoLocalRepository: EscudoLocalRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<State> by lazy {
        MutableStateFlow(State())
    }
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    fun handleEvent(
        event: Event
    ) {
        when (event) {
            is Event.GetAllEscudos -> getAllEscudosByIDPersonaje(idPersonaje = event.idPersonaje)
            is Event.DeleteEscudo -> deleteEscudo(escudo = event.escudo)
            is Event.ShowError -> showError(error = event.error)
            Event.ErrorConsumed -> errorConsumed()
        }
    }

    private fun getAllEscudosByIDPersonaje(idPersonaje: Int) {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(listaEscudos = escudoLocalRepository.getEscudos(idPJ = idPersonaje))
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun deleteEscudo(escudo: Escudo) {
        viewModelScope.launch {
            try {
                escudoLocalRepository.deleteEscudo(escudo = escudo)
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