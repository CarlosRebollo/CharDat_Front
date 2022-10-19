package ies.quevedo.rpgchardatcompose.framework.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ies.quevedo.rpgchardatcompose.framework.screens.addPersonaje.AddPersonaje
import ies.quevedo.rpgchardatcompose.framework.screens.listaPersonajes.ListPersonajes
import ies.quevedo.rpgchardatcompose.framework.screens.mainMenu.MainMenu

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.LISTA_PERSONAJES) {
        composable(route = Routes.LISTA_PERSONAJES) {
            ListPersonajes(onNavigate = { route -> navController.navigate(route) })
        }
        composable(route = Routes.MAIN_MENU_ID_PERSONAJE, arguments = listOf(
            navArgument(Routes.ID_PERSONAJE) { type = NavType.IntType }
        )) {
            val idPersonaje = it.arguments?.getInt(Routes.ID_PERSONAJE)
            MainMenu(
                idPersonaje = idPersonaje ?: 0,
                onNavigate = { route -> navController.navigate(route) }
            )
        }
        composable(route = Routes.ADD_PERSONAJE) {
            AddPersonaje(
                returnToList = { navController.popBackStack() }
            )
        }
    }
}