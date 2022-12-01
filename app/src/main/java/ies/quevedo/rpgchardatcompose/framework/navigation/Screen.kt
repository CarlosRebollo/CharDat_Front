package ies.quevedo.rpgchardatcompose.framework.navigation

sealed class Screen(val route: String) {

    companion object {
        const val TOKEN = "tokenUsuario"
        const val NO_TOKEN = "noToken"
        const val ID_PERSONAJE = "idPersonaje"
        const val ID_ARMA = "idArma"
        const val ID_ARMADURA = "idArmadura"
        const val ID_ESCUDO = "idEscudo"
        const val ID_OBJETO = "idObjeto"
    }

    object LoginUsuario : Screen(route = "loginUsuario")
    object RegistroUsuario : Screen(route = "registroUsuario")
    object ListaPersonajes : Screen(route = "listaPersonajes/{$TOKEN}") {
        fun mandarToken(token: String): String {
            return this.route.replace(oldValue = "{$TOKEN}", newValue = token)
        }
    }

    object AddPersonaje : Screen(route = "addPersonaje")
    object MainMenu : Screen(route = "mainMenu/{$ID_PERSONAJE}") {
        fun mandarIdPersonaje(id: Int): String {
            return route.replace(oldValue = "{$ID_PERSONAJE}", newValue = id.toString())
        }
    }

    object ShowPersonaje : Screen(route = "showPersonaje/{$ID_PERSONAJE}") {
        fun mandarIdPersonaje(id: Int): String {
            return this.route.replace(oldValue = "{$ID_PERSONAJE}", newValue = id.toString())
        }
    }

    object ListaArmas : Screen(route = "listaArmas/{$ID_PERSONAJE}") {
        fun mandarIdPersonaje(id: Int): String {
            return this.route.replace(oldValue = "{$ID_PERSONAJE}", newValue = id.toString())
        }
    }

    object AddArma : Screen(route = "addArma/{$ID_PERSONAJE}") {
        fun mandarIdPersonaje(id: Int): String {
            return this.route.replace(oldValue = "{$ID_PERSONAJE}", newValue = id.toString())
        }
    }

    object ShowArma : Screen(route = "showArma/{$ID_ARMA}") {
        fun mandarIdArma(id: Int): String {
            return this.route.replace(oldValue = "{$ID_ARMA}", newValue = id.toString())
        }
    }

    object ListaArmaduras : Screen(route = "listaArmaduras/{$ID_PERSONAJE}") {
        fun mandarIdPersonaje(id: Int): String {
            return this.route.replace(oldValue = "{$ID_PERSONAJE}", newValue = id.toString())
        }
    }

    object AddArmadura : Screen(route = "addArmadura/{$ID_PERSONAJE}") {
        fun mandarIdPersonaje(id: Int): String {
            return this.route.replace(oldValue = "{$ID_PERSONAJE}", newValue = id.toString())
        }
    }

    object ShowArmadura : Screen(route = "showArmadura/{$ID_ARMADURA}") {
        fun mandarIdArmadura(id: Int): String {
            return this.route.replace(oldValue = "{$ID_ARMADURA}", newValue = id.toString())
        }
    }

    object ListaEscudos : Screen(route = "listaEscudos/{$ID_PERSONAJE}") {
        fun mandarIdPersonaje(id: Int): String {
            return this.route.replace(oldValue = "{$ID_PERSONAJE}", newValue = id.toString())
        }
    }

    object AddEscudo : Screen(route = "addEscudo/{$ID_PERSONAJE}") {
        fun mandarIdPersonaje(id: Int): String {
            return this.route.replace(oldValue = "{$ID_PERSONAJE}", newValue = id.toString())
        }
    }

    object ShowEscudo : Screen(route = "showEscudo/{$ID_ESCUDO}") {
        fun mandarIdEscudo(id: Int): String {
            return this.route.replace(oldValue = "{$ID_ESCUDO}", newValue = id.toString())
        }
    }

    object ListaObjetos : Screen(route = "listaObjetos/{$ID_PERSONAJE}") {
        fun mandarIdPersonaje(id: Int): String {
            return this.route.replace(oldValue = "{$ID_PERSONAJE}", newValue = id.toString())
        }
    }

    object AddObjeto : Screen(route = "addObjeto/{$ID_PERSONAJE}") {
        fun mandarIdPersonaje(id: Int): String {
            return this.route.replace(oldValue = "{$ID_PERSONAJE}", newValue = id.toString())
        }
    }

    object ShowObjeto : Screen(route = "showObjeto/{$ID_OBJETO}") {
        fun mandarIdObjeto(id: Int): String {
            return this.route.replace(oldValue = "{$ID_OBJETO}", newValue = id.toString())
        }
    }
}