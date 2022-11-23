package ies.quevedo.rpgchardatcompose.framework.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ies.quevedo.rpgchardatcompose.framework.screens.addPersonaje.AddPersonaje
import ies.quevedo.rpgchardatcompose.framework.screens.armas.listaArmas.ListaArmas
import ies.quevedo.rpgchardatcompose.framework.screens.listaPersonajes.ListPersonajes
import ies.quevedo.rpgchardatcompose.framework.screens.mainMenu.MainMenu
import ies.quevedo.rpgchardatcompose.framework.screens.showPersonaje.ShowPersonaje

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.LISTA_PERSONAJES) {
        composable(route = Routes.LISTA_PERSONAJES) {
            ListPersonajes(onNavigate = { route -> navController.navigate(route) })
        }
        composable(
            route = Routes.MAIN_MENU_ID_PERSONAJE,
            arguments = listOf(navArgument(Routes.ID_PERSONAJE) { type = NavType.IntType }
            )) {
            val idPersonaje = it.arguments?.getInt(Routes.ID_PERSONAJE)
            MainMenu(
                idPersonaje = idPersonaje ?: 0,
                onNavigate = { route -> navController.navigate(route = route) }
            )
        }
        composable(route = Routes.ADD_PERSONAJE) {
            AddPersonaje(
                onNavigate = { route -> navController.navigate(route = route) },
                onBackPressed = { navController.popBackStack() }
            )
        }
        composable(
            route = Routes.SHOW_PERSONAJE_ID_PERSONAJE,
            arguments = listOf(navArgument(Routes.ID_PERSONAJE) { type = NavType.IntType })
        ) {
            val idPersonaje = it.arguments?.getInt(Routes.ID_PERSONAJE)
            ShowPersonaje(
                idPersonaje = idPersonaje ?: 0,
                onBackPressed = { navController.popBackStack() }
            )
        }
        composable(
            route = Routes.LISTA_ARMAS_ID_PERSONAJE,
            arguments = listOf(navArgument(Routes.ID_PERSONAJE) { type = NavType.IntType })
        ) {
            val idPersonaje = it.arguments?.getInt(Routes.ID_PERSONAJE)
            ListaArmas(
                idPersonaje = idPersonaje ?: 0,
                onNavigate = { route -> navController.navigate(route = route) })
        }
    }
}