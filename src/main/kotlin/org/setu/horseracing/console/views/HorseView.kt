package org.setu.horseracing.console.views

import org.setu.horseracing.console.models.horse.HorseMemStore
import org.setu.horseracing.console.models.horse.HorseModel

class HorseView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add a Horse")
        println(" 2. Update a Horse")
        println(" 3. List All Horses")
        println(" 4. Search Horses")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listHorses(horses : HorseMemStore) {
        println("List All Horses")
        println()
        horses.logAll()
        println()
    }

    fun showHorse(horse : HorseModel) {
        if(horse != null)
            println("Horse Details [ $horse ]")
        else
            println("Horse Not Found...")
    }

    fun addHorseData(horse : HorseModel) : Boolean {

        println()
        print("Enter a Horse Name : ")
        horse.horseName = readLine()!!
        print("Enter their Age : ")
        horse.horseAge = readln().toInt()
        print("Enter the jockeys name : ")
        horse.jockey = readln()
        print("Enter the trainers Name: ")
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
            print("Enter a new Name for [ " + horse.horseName + " ] : ")
            tempHorseName = readLine()!!
            print("Enter a new Age for [ " + horse.horseAge + " ] : ")
            tempHorseAge = readln().toInt()
            print("Enter a new jockey for [ " + horse.jockey + " ] : ")
            tempJockey = readln()!!
            print("Enter a new trainer for [ " + horse.trainer + " ] : ")
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
        print("Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}