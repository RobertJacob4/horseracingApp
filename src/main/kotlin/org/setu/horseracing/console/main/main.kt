package org.setu.horseracing.console.main

import mu.KotlinLogging
import org.setu.horseracing.console.controllers.HorseController
import org.setu.horseracing.console.controllers.RaceController

val ANSI_RESET = "\u001B[0m"
val ANSI_GREEN = "\u001B[32m"
val ANSI_RED = "\u001B[31m"
val ANSI_BLUE = "\u001B[34m"
val CYAN = "\u001b[0;36m" // CYAN


private val logger = KotlinLogging.logger {}
fun main(args: Array<String>) {
    //RaceController().start()
    //HorseController().start()
    startMenu()
}

    fun startMenu() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> HorseController().start()
                2 -> RaceController().start()
                -1 -> println(ANSI_RED + "Exiting Horse Racing App" + ANSI_RESET)
                else -> println(ANSI_RED+"Invalid Option"+ ANSI_RESET)
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Horse Racing App" }
    }

    fun menu(): Int {

        var option: Int
        var input: String?

        println(ANSI_BLUE + "MENU" + ANSI_RESET)
        println(CYAN + " 1. Enter Horse Menu")
        println(" 2. Enter Race Menu" + ANSI_RESET)
        println(ANSI_RED + "-1. Exit" + ANSI_RESET)
        println()
        print(ANSI_BLUE + "Enter Option : " + ANSI_RESET)
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }
