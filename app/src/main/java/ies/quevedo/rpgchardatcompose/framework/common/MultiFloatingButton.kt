package ies.quevedo.rpgchardatcompose.framework.common

import androidx.compose.runtime.Composable

enum class MultiFloatingState {
    Expanded,
    Collapsed
}

@Composable
fun MultiFloatingButton(
    multiFloatingState: MultiFloatingState,
    onMultiFabStateChange:(MultiFloatingState) -> Unit
) {
    //TODO -> Seguir viendo https://www.youtube.com/watch?v=9SHNfpnzdEU
}