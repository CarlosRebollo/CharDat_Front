package ies.quevedo.rpgchardatcompose.framework.screens.usuarios.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.rpgchardatcompose.data.entities.UsuarioEntity
import ies.quevedo.rpgchardatcompose.data.repository.local.UsuarioLocalRepository
import ies.quevedo.rpgchardatcompose.data.repository.remote.UsuarioRemoteRepository
import ies.quevedo.rpgchardatcompose.data.utils.NetworkResult
import ies.quevedo.rpgchardatcompose.domain.Usuario
import ies.quevedo.rpgchardatcompose.framework.screens.usuarios.login.LoginUsuarioContract.Event
import ies.quevedo.rpgchardatcompose.framework.screens.usuarios.login.LoginUsuarioContract.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginUsuarioVM @Inject constructor(
    private val usuarioRemoteRepository: UsuarioRemoteRepository,
    private val usuarioLocalRepository: UsuarioLocalRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<State> by lazy {
        MutableStateFlow(State())
    }
    val uiState: StateFlow<State> = _uiState

    fun handleEvent(event: Event) {
        when (event) {
            is Event.LoginUsuario -> loginUsuario(usuario = event.usuario)
            is Event.InsertUsuarioToken -> insertUsuarioToken(usuarioConToken = event.usuario)
            is Event.ShowError -> showError(error = event.error)
            Event.ErrorConsumed -> errorConsumed()
        }
    }

    private fun loginUsuario(usuario: Usuario) {
        viewModelScope.launch {
            usuarioRemoteRepository.loguearUsuario(usuario = usuario)
                .catch(action = { cause ->
                    _uiState.update { it.copy(error = cause.message, isLoading = false) }
                    Timber.tag("Error").e(cause)
                })
                .collect { result ->
                    when (result) {
                        is NetworkResult.Error -> {
                            _uiState.update { it.copy(error = result.message, isLoading = false) }
                            Timber.tag("Error").e(result.message)
                        }
                        is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is NetworkResult.Success -> _uiState.update {
                            it.copy(
                                usuarioLogueado = result.data?.let { tokenDevuelto ->
                                    UsuarioEntity(
                                        correoElectronico = usuario.email,
                                        token = tokenDevuelto.jwt
                                    )
                                }, isLoading = false
                            )
                        }
                    }
                }
        }
    }

    private fun insertUsuarioToken(usuarioConToken: UsuarioEntity?) {
        viewModelScope.launch {
            try {
                if (usuarioConToken != null) {
                    usuarioLocalRepository.insertUsuarioConToken(usuario = usuarioConToken)
                }
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