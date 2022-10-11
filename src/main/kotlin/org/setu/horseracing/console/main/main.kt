package org.setu.horseracing.console.main

import mu.KotlinLogging
import org.setu.horseracing.console.models.RaceModel

private val logger = KotlinLogging.logger {}

val races = ArrayList<RaceModel>()

fun main(args: Array<String>){
    logger.info { "Launching Horse racing App" }
    println("Horse Racing App Version 1.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addRace()
            2 -> listRaces()
            3 -> deleteRace()
            4 -> searchRace()
            5 -> addHorse()
            6 -> listHorses()
            7 -> updateHorse()
            8 -> deleteHorse()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Horse Racing App" }
}

fun menu() : Int {

    var option : Int
    var input: String? = null

    println("Main Menu")
    println(" 1. Create Race")
    println(" 2. List Races")
    println(" 3. Delete Race")
    println(" 4. Search Race")
    println(" ----------------------- ")
    println(" 5. Add Horse")
    println(" 6. List Horses")
    println(" 7. Update Horse")
    println(" 8. Delete Horse")
    println("-1. Exit")
    println()
    print("Enter an integer : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option

}

fun addRace() {
    var tempRace = RaceModel()
    println("You Chose To Create a Race")


    print("Enter Race Name: ")
    tempRace.raceName = readLine()!!

    print("Enter Race Date: ")
    tempRace.raceDate = readLine()!!

    print("Enter Race Size: ")
    tempRace.raceSize = readLine()!!.toInt()

    print("Enter Race start time: ")
    tempRace.startTime = readLine()!!

    print("Enter Race Winner: ")
    tempRace.raceWinner = readLine()!!

    print("Enter Race Venue: ")
    tempRace.venue = readLine()!!

    println("The ${tempRace.raceName} was held at ${tempRace.venue} on the ${tempRace.raceDate} and the winner was ${tempRace.raceWinner}.")

    println("Race: ${tempRace.raceName} has been added")
    tempRace.id = races.size.toLong()
    races.add(tempRace.copy())
}
fun searchRace() {

    var searchId = getId()
    val tempRace = search(searchId)

    if(tempRace != null)
        println("Race Details [ $tempRace ]")
    else
        println("Race Not Found...")
}

fun search(id: Long) : RaceModel? {
    var foundRace: RaceModel? = races.find { p -> p.id == id }
    return foundRace
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

fun listRaces() {
    println("You Chose To View Races")
    races.forEach{ logger.info("$it")}
}

fun deleteRace() {
    println("You Chose To Delete a Race")
}

fun listHorses() {
    println("You Chose To View Horses")
}

fun addHorse() {
    println("You Chose To Add a Horse")
}

fun updateHorse() {
    println("You Chose To Update a Horse")
}

fun deleteHorse() {
    println("You Chose To Delete a Horse")
}