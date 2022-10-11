package org.setu.horseracing.console.views

import org.setu.horseracing.console.models.RaceMemStore
import org.setu.horseracing.console.models.RaceModel

class RaceView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add Race")
        println(" 2. Update Race")
        println(" 3. List All Races")
        println(" 4. Search Races")
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

    fun listRaces(races : RaceMemStore) {
        println("List All Races")
        println()
        races.logAll()
        println()
    }

    fun showRace(race : RaceModel) {
        if(race != null)
            println("Race Details [ $race ]")
        else
            println("Race Not Found...")
    }


    fun addRaceData(race : RaceModel) : Boolean {

        println()
        print("Enter a Race Name : ")
        race.raceName = readLine()!!

        print("Enter a Race Date : ")
        race.raceDate = readLine()!!

        print("Enter a Race Size : ")
        race.raceSize = readLine()!!.toInt()

        print("Enter a Race start time : ")
        race.startTime = readLine()!!

        print("Enter a Race Winner : ")
        race.raceWinner = readLine()!!

        print("Enter a Race Venue : ")
        race.venue = readLine()!!

        return race.raceName.isNotEmpty() &&
                race.venue.isNotEmpty() &&
                race.raceDate.isNotEmpty() &&
                race.raceSize > 0 && race.startTime.isNotEmpty() &&
                race.raceWinner.isNotEmpty()
    }

    fun updateRaceData(race : RaceModel) : Boolean {

        val tempRaceName: String?
        val tempRaceVenue: String?

        if (race != null) {
            print("Enter a new Title for [ " + race.raceName + " ] : ")
            tempRaceName = readLine()!!
            print("Enter a new Description for [ " + race.venue + " ] : ")
            tempRaceVenue = readLine()!!

            if (!tempRaceName.isNullOrEmpty() && !tempRaceVenue.isNullOrEmpty()) {
                race.raceName = tempRaceName
                race.venue = tempRaceVenue
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}