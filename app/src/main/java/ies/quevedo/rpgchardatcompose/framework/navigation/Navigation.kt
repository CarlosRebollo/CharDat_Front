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
import ies.quevedo.rpgchardatcompose.framework.screens.escudos.addEscudo.AddEscudos
import ies.quevedo.rpgchardatcompose.framework.screens.escudos.listaEscudos.ListaEscudos
import ies.quevedo.rpgchardatcompose.framework.screens.escudos.showEscudo.ShowEscudos
import ies.quevedo.rpgchardatcompose.framework.screens.mainMenu.MainMenu
import ies.quevedo.rpgchardatcompose.framework.screens.objetos.addObjeto.AddObjeto
import ies.quevedo.rpgchardatcompose.framework.screens.objetos.listaObjetos.ListaObjetos
import ies.quevedo.rpgchardatcompose.framework.screens.objetos.showObjeto.ShowObjeto
import ies.quevedo.rpgchardatcompose.framework.screens.personajes.addPersonaje.AddPersonaje
import ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes.ListaPersonajes
import ies.quevedo.rpgchardatcompose.framework.screens.personajes.showPersonaje.ShowPersonaje
import ies.quevedo.rpgchardatcompose.framework.screens.usuarios.login.LoginUsuario
import ies.quevedo.rpgchardatcompose.framework.screens.usuarios.registro.RegistroUsuario

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.ListaPersonajes.route
    ) {
        //PERSONAJES
        composable(
            route = Screen.ListaPersonajes.route,
            arguments = listOf(navArgument(Screen.TOKEN) { type = NavType.StringType })
        ) {
            ListaPersonajes(
                navController = navController
            )
        }
        composable(route = Screen.AddPersonaje.route) {
            AddPersonaje(navController = navController)
        }

        //USUARIO
        composable(route = Screen.LoginUsuario.route) {
            LoginUsuario(navController = navController)
        }
        composable(route = Screen.RegistroUsuario.route) {
            RegistroUsuario(navController = navController)
        }

        //MENU PRINCIPAL
        composable(
            route = Screen.MainMenu.route,
            arguments = listOf(navArgument(Screen.ID_PERSONAJE) { type = NavType.IntType })
        ) {
            MainMenu(
                idPersonaje = it.arguments?.getInt(Screen.ID_PERSONAJE) ?: 0,
                navController = navController
            )
        }
        composable(
            route = Screen.ShowPersonaje.route,
            arguments = listOf(navArgument(Screen.ID_PERSONAJE) { type = NavType.IntType })
        ) {
            ShowPersonaje(
                idPersonaje = it.arguments?.getInt(Screen.ID_PERSONAJE) ?: 0,
                navController = navController
            )
        }

        // ARMAS
        composable(
            route = Screen.ListaArmas.route,
            arguments = listOf(navArgument(Screen.ID_PERSONAJE) { type = NavType.IntType })
        ) {
            ListaArmas(
                idPersonaje = it.arguments?.getInt(Screen.ID_PERSONAJE) ?: 0,
                navController = navController
            )
        }
        composable(
            route = Screen.AddArma.route,
            arguments = listOf(navArgument(Screen.ID_PERSONAJE) { type = NavType.IntType })
        ) {
            AddArma(
                idPersonaje = it.arguments?.getInt(Screen.ID_PERSONAJE) ?: 0,
                navController = navController
            )
        }
        composable(
            route = Screen.ShowArma.route,
            arguments = listOf(navArgument(Screen.ID_ARMA) { type = NavType.IntType })
        ) {
            ShowArma(
                idArma = it.arguments?.getInt(Screen.ID_ARMA) ?: 0,
                navController = navController
            )
        }

        // ARMADURAS
        composable(
            route = Screen.ListaArmaduras.route,
            arguments = listOf(navArgument(Screen.ID_PERSONAJE) { type = NavType.IntType })
        ) {
            ListaArmaduras(
                idPersonaje = it.arguments?.getInt(Screen.ID_PERSONAJE) ?: 0,
                navController = navController
            )
        }
        composable(
            route = Screen.AddArmadura.route,
            arguments = listOf(navArgument(Screen.ID_PERSONAJE) { type = NavType.IntType })
        ) {
            AddArmadura(
                idPersonaje = it.arguments?.getInt(Screen.ID_PERSONAJE) ?: 0,
                navController = navController
            )
        }
        composable(
            route = Screen.ShowArmadura.route,
            arguments = listOf(navArgument(Screen.ID_ARMADURA) { type = NavType.IntType })
        ) {
            ShowArmadura(
                idArmadura = it.arguments?.getInt(Screen.ID_ARMADURA) ?: 0,
                navController = navController
            )
        }

        // ESCUDOS
        composable(
            route = Screen.ListaEscudos.route,
            arguments = listOf(navArgument(Screen.ID_PERSONAJE) { type = NavType.IntType })
        ) {
            ListaEscudos(
                idPersonaje = it.arguments?.getInt(Screen.ID_PERSONAJE) ?: 0,
                navController = navController
            )
        }
        composable(
            route = Screen.AddEscudo.route,
            arguments = listOf(navArgument(Screen.ID_PERSONAJE) { type = NavType.IntType })
        ) {
            AddEscudos(
                idPersonaje = it.arguments?.getInt(Screen.ID_PERSONAJE) ?: 0,
                navController = navController
            )
        }
        composable(
            route = Screen.ShowEscudo.route,
            arguments = listOf(navArgument(Screen.ID_ESCUDO) { type = NavType.IntType })
        ) {
            ShowEscudos(
                idEscudo = it.arguments?.getInt(Screen.ID_ESCUDO) ?: 0,
                navController = navController
            )
        }

        // OBJETOS
        composable(
            route = Screen.ListaObjetos.route,
            arguments = listOf(navArgument(Screen.ID_PERSONAJE) { type = NavType.IntType })
        ) {
            ListaObjetos(
                idPersonaje = it.arguments?.getInt(Screen.ID_PERSONAJE) ?: 0,
                navController = navController
            )
        }
        composable(
            route = Screen.AddObjeto.route,
            arguments = listOf(navArgument(Screen.ID_PERSONAJE) { type = NavType.IntType })
        ) {
            AddObjeto(
                idPersonaje = it.arguments?.getInt(Screen.ID_PERSONAJE) ?: 0,
                navController = navController
            )
        }
        composable(
            route = Screen.ShowObjeto.route,
            arguments = listOf(navArgument(Screen.ID_OBJETO) { type = NavType.IntType })
        ) {
            ShowObjeto(
                idObjeto = it.arguments?.getInt(Screen.ID_OBJETO) ?: 0,
                navController = navController
            )
        }
    }
}