package ies.quevedo.rpgchardatcompose.framework.screens.usuarios.registro

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.remote.dataSources.UsuarioRemoteDataSource
import ies.quevedo.rpgchardatcompose.domain.Usuario
import ies.quevedo.rpgchardatcompose.framework.screens.usuarios.registro.RegistroUsuarioContract.Event
import ies.quevedo.rpgchardatcompose.framework.screens.usuarios.registro.RegistroUsuarioContract.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegistroUsuarioVM @Inject constructor(
    private val usuarioRemoteDataSource: UsuarioRemoteDataSource
) : ViewModel() {

    private val _uiState: MutableStateFlow<State> by lazy {
        MutableStateFlow(State())
    }
    val uiState: StateFlow<State> = _uiState

    fun handleEvent(event: Event) {
        when (event) {
            is Event.RegisterUsuario -> registerUsuario(usuario = event.usuario)
            is Event.ShowError -> showError(error = event.error)
            Event.ErrorConsumed -> errorConsumed()
        }
    }

    private fun registerUsuario(usuario: Usuario) {

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