package ies.quevedo.rpgchardatcompose.framework.utils

import ies.quevedo.rpgchardatcompose.R

object Constantes {

    const val SELECCIONA_UNA_CLASE: String = "SELECCIONA UNA CLASE"

    fun getClases(): Array<String> {
        return arrayOf(
            "GUERRERO",
            "GUERRERO ACRÓBATA",
            "PALADÍN",
            "PALADÍN OSCURO",
            "MAESTRO DE ARMAS",
            "TECNICISTA",
            "TAO",
            "EXPLORADOR",
            "SOMBRA",
            "LADRÓN",
            "ASESINO",
            "HECHICERO",
            "WARLOCK",
            "ILUSIONISTA",
            "HECHICERO MENTALISTA",
            "CONJURADOR",
            "GUERRERO CONJURADOR",
            "MENTALISTA",
            "GUERRERO MENTALISTA",
            "NOVEL"
        )
    }

    fun getArmas(): Array<String> {
        return arrayOf(
            "ALABARDA",
            "ARPÓN",
            "CADENA",
            "CESTUS",
            "CIMITARRA",
            "DAGA",
            "DAGA DE PARADA",
            "ESPADA ANCHA",
            "ESPADA BASTARDA",
            "ESPADA CORTA",
            "ESPADA LARGA",
            "ESTILETE",
            "ESTOQUE",
            "FLAGELO",
            "FLORETE",
            "GARFIO",
            "GARROTE",
            "GRAN MARTILLO DE GUERRA",
            "GUADAÑA",
            "HACHA A DOS MANOS",
            "HACHA DE GUERRA",
            "HACHA DE MANO",
            "JABALINA",
            "LANZA",
            "LANZA DE CABALLERÍA",
            "LÁTIGO",
            "LAZO",
            "MANDOBLE",
            "MANGUAL",
            "MARTILLO DE GUERRA",
            "MAYAL",
            "MAZA",
            "MAZA PESADA",
            "RED DE GLADIADOR",
            "SABLE",
            "TRIDENTE",
            "VARA"
        )
    }

    fun getArmaduras(): Array<String> {
        return arrayOf(
            "ACOLCHADA",
            "ANILLAS",
            "COMPLETA",
            "COMPLETA DE CUERO",
            "COMPLETA DE CAMPAÑA",
            "COMPLETA PESADA",
            "COTA DE CUERO",
            "CUERO ENDURECIDO",
            "CUERO TACHONADO",
            "ESCAMAS",
            "GABARDINA",
            "MALLAS",
            "PETO",
            "PIEL",
            "PIEZAS",
            "PLACAS",
            "SEMICOMPLETA"
        )
    }

    fun getEscudos(): Array<String> {
        return arrayOf(
            "ESCUDO",
            "ESCUDO CORPORAL",
            "RODELA"
        )
    }

    fun getImageBannerMini(clase: String): Int {
        return when (clase) {
            "GUERRERO" -> R.drawable.guerrero_mini
            "GUERRERO ACRÓBATA" -> R.drawable.guerrero_acrobata_mini
            "PALADÍN" -> R.drawable.paladin_mini
            "PALADÍN OSCURO" -> R.drawable.paladin_oscuro_mini
            "MAESTRO DE ARMAS" -> R.drawable.maestro_de_armas_mini
            "TECNICISTA" -> R.drawable.tecnicista_mini
            "TAO" -> R.drawable.tao_mini
            "EXPLORADOR" -> R.drawable.explorador_mini
            "SOMBRA" -> R.drawable.sombra_mini
            "LADRÓN" -> R.drawable.ladron_mini
            "ASESINO" -> R.drawable.asesino_mini
            "HECHICERO" -> R.drawable.hechicero_mini
            "WARLOCK" -> R.drawable.warlock_mini
            "ILUSIONISTA" -> R.drawable.ilusionista_mini
            "HECHICERO MENTALISTA" -> R.drawable.hechicero_mentalista_mini
            "CONJURADOR" -> R.drawable.conjurador_mini
            "GUERRERO CONJURADOR" -> R.drawable.guerrero_conjurador_mini
            "MENTALISTA" -> R.drawable.mentalista_mini
            "GUERRERO MENTALISTA" -> R.drawable.guerrero_mentalista_mini
            "NOVEL" -> R.drawable.novel_mini
            else -> 0
        }
    }

    fun getImageBanner(clase: String): Int {
        return when (clase) {
            "GUERRERO" -> R.drawable.guerrero_banner
            "GUERRERO ACRÓBATA" -> R.drawable.guerrero_acrobata_banner
            "PALADÍN" -> R.drawable.paladin_banner
            "PALADÍN OSCURO" -> R.drawable.paladin_oscuro_banner
            "MAESTRO DE ARMAS" -> R.drawable.maestro_de_armas_banner
            "TECNICISTA" -> R.drawable.tecnicista_banner
            "TAO" -> R.drawable.tao_banner
            "EXPLORADOR" -> R.drawable.explorador_banner
            "SOMBRA" -> R.drawable.sombra_banner
            "LADRÓN" -> R.drawable.ladron_banner
            "ASESINO" -> R.drawable.asesino_banner
            "HECHICERO" -> R.drawable.hechicero_banner
            "WARLOCK" -> R.drawable.warlock_banner
            "ILUSIONISTA" -> R.drawable.ilusionista_banner
            "HECHICERO MENTALISTA" -> R.drawable.hechicero_mentalista_banner
            "CONJURADOR" -> R.drawable.conjurador_banner
            "GUERRERO CONJURADOR" -> R.drawable.guerrero_conjurador_banner
            "MENTALISTA" -> R.drawable.mentalista_banner
            "GUERRERO MENTALISTA" -> R.drawable.guerrero_mentalista_banner
            "NOVEL" -> R.drawable.novel_banner
            else -> 0
        }
    }

    fun getStats(): Array<String> {
        return arrayOf("10", "9", "8", "7", "6", "5", "4", "3", "2", "1")
    }

}
