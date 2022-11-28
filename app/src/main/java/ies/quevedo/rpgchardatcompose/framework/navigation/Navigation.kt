package ies.quevedo.rpgchardatcompose.framework.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ies.quevedo.rpgchardatcompose.framework.screens.armaduras.addArmadura.AddArmadura
import ies.quevedo.rpgchardatcompose.framework.screens.armaduras.listaArmaduras.ListaArmaduras
import ies.quevedo.rpgchardatcompose.framework.screens.armaduras.showArmadura.ShowArmadura
import ies.quevedo.rpgchardatcompose.framework.screens.armas.addArma.AddArma
import ies.quevedo.rpgchardatcompose.framework.screens.armas.listaArmas.ListaArmas
import ies.quevedo.rpgchardatcompose.framework.screens.armas.showArma.ShowArma
import ies.quevedo.rpgchardatcompose.framework.screens.mainMenu.MainMenu
import ies.quevedo.rpgchardatcompose.framework.screens.personajes.addPersonaje.AddPersonaje
import ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes.ListPersonajes
import ies.quevedo.rpgchardatcompose.framework.screens.personajes.showPersonaje.ShowPersonaje

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.LISTA_PERSONAJES) {

        //PERSONAJES
        composable(route = Routes.LISTA_PERSONAJES) {
            ListPersonajes(onNavigate = { route -> navController.navigate(route) })
        }
        composable(route = Routes.ADD_PERSONAJE) {
            AddPersonaje(onBackPressed = { navController.popBackStack() })
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

        //MENU PRINCIPAL
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

        // ARMAS
        composable(
            route = Routes.LISTA_ARMAS_ID_PERSONAJE,
            arguments = listOf(navArgument(Routes.ID_PERSONAJE) { type = NavType.IntType })
        ) {
            val idPersonaje = it.arguments?.getInt(Routes.ID_PERSONAJE)
            ListaArmas(
                idPersonaje = idPersonaje ?: 0,
                onNavigate = { route -> navController.navigate(route = route) })
        }
        composable(
            route = Routes.ADD_ARMA_ID_PERSONAJE,
            arguments = listOf(navArgument(Routes.ID_PERSONAJE) { type = NavType.IntType })
        ) {
            val idPersonaje = it.arguments?.getInt(Routes.ID_PERSONAJE)
            AddArma(
                idPersonaje = idPersonaje,
                onBackPressed = { navController.popBackStack() }
            )
        }
        composable(
            route = Routes.SHOW_ARMA_ID_ARMA,
            arguments = listOf(navArgument(Routes.ID_ARMA) { type = NavType.IntType })
        ) {
            val idArma = it.arguments?.getInt(Routes.ID_ARMA)
            ShowArma(
                idArma = idArma ?: 0,
                onBackPressed = { navController.popBackStack() }
            )
        }

        // ARMADURAS
        composable(
            route = Routes.LISTA_ARMADURAS_ID_PERSONAJE,
            arguments = listOf(navArgument(Routes.ID_PERSONAJE) { type = NavType.IntType })
        ) {
            val idPersonaje = it.arguments?.getInt(Routes.ID_PERSONAJE)
            ListaArmaduras(
                idPersonaje = idPersonaje ?: 0,
                onNavigate = { route -> navController.navigate(route = route) })
        }
        composable(
            route = Routes.ADD_ARMADURA_ID_PERSONAJE,
            arguments = listOf(navArgument(Routes.ID_PERSONAJE) { type = NavType.IntType })
        ) {
            val idPersonaje = it.arguments?.getInt(Routes.ID_PERSONAJE)
            AddArmadura(
                idPersonaje = idPersonaje,
                onBackPressed = { navController.popBackStack() }
            )
        }
        composable(
            route = Routes.SHOW_ARMADURA_ID_ARMADURA,
            arguments = listOf(navArgument(Routes.ID_ARMADURA) { type = NavType.IntType })
        ) {
            val idArmadura = it.arguments?.getInt(Routes.ID_ARMADURA)
            ShowArmadura(
                idArmadura = idArmadura ?: 0,
                onBackPressed = { navController.popBackStack() }
            )
        }

        // ESCUDOS
        composable(
            route = Routes.LISTA_ESCUDOS_ID_PERSONAJE,
            arguments = listOf(navArgument(Routes.ID_PERSONAJE) { type = NavType.IntType })
        ) {
            val idPersonaje = it.arguments?.getInt(Routes.ID_PERSONAJE)
            ListaEscudos(
                idPersonaje = idPersonaje ?: 0,
                onNavigate = { route -> navController.navigate(route = route) })
        }
        composable(
            route = Routes.ADD_ESCUDO_ID_PERSONAJE,
            arguments = listOf(navArgument(Routes.ID_PERSONAJE) { type = NavType.IntType })
        ) {
            val idPersonaje = it.arguments?.getInt(Routes.ID_PERSONAJE)
            AddEscudos(
                idPersonaje = idPersonaje,
                onBackPressed = { navController.popBackStack() }
            )
        }
        composable(
            route = Routes.SHOW_ESCUDO_ID_ESCUDO,
            arguments = listOf(navArgument(Routes.ID_ESCUDO) { type = NavType.IntType })
        ) {
            val idEscudo = it.arguments?.getInt(Routes.ID_ESCUDO)
            ShowEscudos(
                idEscudo = idEscudo ?: 0,
                onBackPressed = { navController.popBackStack() }
            )
        }

        // OBJETOS
        composable(
            route = Routes.LISTA_OBJETOS_ID_PERSONAJE,
            arguments = listOf(navArgument(Routes.ID_PERSONAJE) { type = NavType.IntType })
        ) {
            val idPersonaje = it.arguments?.getInt(Routes.ID_PERSONAJE)
            ListaObjetos(
                idPersonaje = idPersonaje ?: 0,
                onNavigate = { route -> navController.navigate(route = route) })
        }
        composable(
            route = Routes.ADD_OBJETO_ID_PERSONAJE,
            arguments = listOf(navArgument(Routes.ID_PERSONAJE) { type = NavType.IntType })
        ) {
            val idPersonaje = it.arguments?.getInt(Routes.ID_PERSONAJE)
            AddObjeto(
                idPersonaje = idPersonaje,
                onBackPressed = { navController.popBackStack() }
            )
        }
        composable(
            route = Routes.SHOW_OBJETO_ID_OBJETO,
            arguments = listOf(navArgument(Routes.ID_OBJETO) { type = NavType.IntType })
        ) {
            val idObjeto = it.arguments?.getInt(Routes.ID_OBJETO)
            ShowObjeto(
                idObjeto = idObjeto ?: 0,
                onBackPressed = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun ShowObjeto(
    idObjeto: Int,
    onBackPressed: () -> Boolean
) {

}

@Composable
fun AddObjeto(
    idPersonaje: Int?,
    onBackPressed: () -> Boolean
) {

}

@Composable
fun ListaObjetos(
    idPersonaje: Int,
    onNavigate: (String) -> Unit
) {

}

@Composable
fun ShowEscudos(
    idEscudo: Int,
    onBackPressed: () -> Boolean
) {

}

@Composable
fun AddEscudos(
    idPersonaje: Int?,
    onBackPressed: () -> Boolean
) {

}

@Composable
fun ListaEscudos(
    idPersonaje: Int,
    onNavigate: (String) -> Unit
) {

}
