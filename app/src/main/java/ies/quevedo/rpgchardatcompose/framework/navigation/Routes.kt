package ies.quevedo.rpgchardatcompose.framework.navigation

object Routes {

    /**
     * Datos de PERSONAJE
     */
    const val ID_PERSONAJE: String = "idPersonaje"
    const val LISTA_PERSONAJES: String = "listaPersonajes/"
    const val LISTA_PERSONAJES_CORREO_LOGIN = "listaPersonajes/{correoUsuario}"
    const val MAIN_MENU: String = "mainMenu/"
    const val MAIN_MENU_ID_PERSONAJE: String = "mainMenu/{idPersonaje}"
    const val ADD_PERSONAJE: String = "addPersonaje"
    const val SHOW_PERSONAJE: String = "showPersonaje/"
    const val SHOW_PERSONAJE_ID_PERSONAJE: String = "showPersonaje/{idPersonaje}"

    /**
     * Datos de USUARIO
     */
    const val CORREO_ELECTRONICO: String = "correoUsuario"
    const val LOGIN: String = "loginUsuario"
    const val REGISTRO: String = "registroUsuario"

    /**
     * Datos de ARMA
     */
    const val ID_ARMA: String = "idArma"
    const val LISTA_ARMAS: String = "listaArmas/"
    const val LISTA_ARMAS_ID_PERSONAJE: String = "listaArmas/{idPersonaje}"
    const val ADD_ARMA: String = "addArma/"
    const val ADD_ARMA_ID_PERSONAJE: String = "addArma/{idPersonaje}"
    const val SHOW_ARMA: String = "showArma/"
    const val SHOW_ARMA_ID_ARMA: String = "showArma/{idArma}"

    /**
     * Datos de ARMADURA
     */
    const val ID_ARMADURA: String = "idArmadura"
    const val LISTA_ARMADURAS: String = "listaArmaduras/"
    const val LISTA_ARMADURAS_ID_PERSONAJE: String = "listaArmaduras/{idPersonaje}"
    const val ADD_ARMADURA: String = "addArmadura/"
    const val ADD_ARMADURA_ID_PERSONAJE: String = "addArmadura/{idPersonaje}"
    const val SHOW_ARMADURA: String = "showArmadura/"
    const val SHOW_ARMADURA_ID_ARMADURA: String = "showArmadura/{idArmadura}"

    /**
     * Datos de ESCUDO
     */
    const val ID_ESCUDO: String = "idEscudo"
    const val LISTA_ESCUDOS: String = "listaEscudos/"
    const val LISTA_ESCUDOS_ID_PERSONAJE: String = "listaEscudos/{idPersonaje}"
    const val ADD_ESCUDO: String = "addEscudo/"
    const val ADD_ESCUDO_ID_PERSONAJE: String = "addEscudo/{idPersonaje}"
    const val SHOW_ESCUDO: String = "showEscudo/"
    const val SHOW_ESCUDO_ID_ESCUDO: String = "showEscudo/{idEscudo}"

    /**
     * Datos de OBJETO
     */
    const val ID_OBJETO: String = "idObjeto"
    const val LISTA_OBJETOS: String = "listaObjetos/"
    const val LISTA_OBJETOS_ID_PERSONAJE: String = "listaObjetos/{idPersonaje}"
    const val ADD_OBJETO: String = "addObjeto/"
    const val ADD_OBJETO_ID_PERSONAJE: String = "addObjeto/{idPersonaje}"
    const val SHOW_OBJETO: String = "showObjeto/"
    const val SHOW_OBJETO_ID_OBJETO: String = "showObjeto/{idObjeto}"
}