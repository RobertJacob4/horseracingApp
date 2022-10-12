package org.setu.horseracing.console.views

import org.setu.horseracing.console.models.race.RaceJSONStore
import org.setu.horseracing.console.models.race.RaceMemStore
import org.setu.horseracing.console.models.race.RaceModel

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

    fun listRaces(races: RaceJSONStore) {
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
        val tempRaceSize: Int
        val tempRaceDate: String?
        val tempStartTime: String?
        val tempRaceWinner: String?

        if (race != null) {
            print("Enter a new Race name for [ " + race.raceName + " ] : ")
            tempRaceName = readLine()!!
            print("Enter a new venue for [ " + race.venue + " ] : ")
            tempRaceVenue = readLine()!!
            print("Enter a new race size for [ " + race.raceSize + " ] : ")
            tempRaceSize = readLine()!!.toInt()
            print("Enter a new race Date for [ " + race.raceDate + " ] : ")
            tempRaceDate = readLine()!!
            print("Enter a new start time for [ " + race.startTime + " ] : ")
            tempStartTime = readLine()!!
            print("Enter a new race Winner for [ " + race.raceWinner + " ] : ")
            tempRaceWinner = readLine()!!

            if (tempRaceName.isNotEmpty() && tempRaceVenue.isNotEmpty() && tempRaceSize > 0 && tempRaceDate.isNotEmpty() && tempStartTime.isNotEmpty() && tempRaceWinner.isNotEmpty()) {
                race.raceName = tempRaceName
                race.venue = tempRaceVenue
                race.raceSize = tempRaceSize
                race.raceDate = tempRaceDate
                race.startTime = tempStartTime
                race.raceWinner = tempRaceWinner
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