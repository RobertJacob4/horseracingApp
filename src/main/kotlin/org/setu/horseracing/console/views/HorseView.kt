package org.setu.horseracing.console.views

import org.setu.horseracing.console.models.horse.HorseJSONStore
import org.setu.horseracing.console.models.horse.HorseModel

val ANSI_RESET = "\u001B[0m"
val ANSI_GREEN = "\u001B[32m"
val ANSI_RED = "\u001B[31m"
val ANSI_BLUE = "\u001B[34m"
val CYAN = "\u001b[0;36m" // CYAN


class HorseView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println(ANSI_BLUE + "HORSE MENU" + ANSI_RESET)
        println(CYAN + " 1. Add a Horse")
        println(" 2. Update a Horse")
        println(" 3. List All Horses")
        println(" 4. Search Horses")
        println(" 5. Delete Horse" + ANSI_RESET)
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

    fun listHorses(horses: HorseJSONStore) {
        println(CYAN + "List All Horses" + ANSI_RESET)
        println()
        horses.logAll()
        println()
    }

    fun showHorse(horse : HorseModel) {
        if(horse != null)
            println(ANSI_GREEN + "Horse Details [ $horse ]" + ANSI_RESET)
        else
            println(ANSI_RED + "Horse Not Found..." + ANSI_RESET)
    }

    fun addHorseData(horse : HorseModel) : Boolean {

        println()
        print(ANSI_BLUE + "Enter a Horse Name : ")
        horse.horseName = readLine()!!
        print("Enter their Age : ")
        horse.horseAge = readln().toInt()
        print("Enter the jockeys name : ")
        horse.jockey = readln()
        print("Enter the trainers Name: " + ANSI_RESET)
        horse.trainer = readln()

        return horse.horseName.isNotEmpty() &&
                horse.horseAge > 0 &&
                horse.jockey.isNotEmpty() &&
                horse.trainer.isNotEmpty()
    }

    fun updateHorseData(horse: HorseModel) : Boolean {

        val tempHorseName: String?
        val tempHorseAge: Int
        val tempJockey: String?
        val tempTrainer: String?

        if (horse != null) {
            print(ANSI_BLUE + "Enter a new Name for [ " + horse.horseName + " ] : ")
            tempHorseName = readLine()!!
            print("Enter a new Age for [ " + horse.horseAge + " ] : ")
            tempHorseAge = readln().toInt()
            print("Enter a new jockey for [ " + horse.jockey + " ] : ")
            tempJockey = readln()!!
            print("Enter a new trainer for [ " + horse.trainer + " ] : " + ANSI_RESET)
            tempTrainer = readln()!!


            if (tempHorseName.isNotEmpty() && tempHorseAge > 0 && tempJockey.isNotEmpty() && tempTrainer.isNotEmpty()) {
                horse.horseName = tempHorseName
                horse.horseAge = tempHorseAge
                horse.jockey = tempJockey
                horse.trainer = tempTrainer
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String?
        var searchId : Long
        print(ANSI_BLUE+ "Enter id to Search/Update : "+ ANSI_RESET)
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}