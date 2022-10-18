package org.setu.horseracing.console.views

import org.setu.horseracing.console.models.race.RaceJSONStore
import org.setu.horseracing.console.models.race.RaceModel



class RaceView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println(ANSI_BLUE + "RACE MENU" + ANSI_RESET)
        println("$CYAN 1. Add Race")
        println(" 2. Update Race")
        println(" 3. List All Races")
        println(" 4. Search Races")
        println(" 5. Delete Race$ANSI_RESET")
        println("$ANSI_RED-1. Exit$ANSI_RESET")
        println()
        print(ANSI_BLUE + "Enter Option : " + ANSI_RESET)
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listRaces(races: RaceJSONStore) {
        println(CYAN + "List All Races" + ANSI_RESET)
        println()
        races.logAll()
        println()
    }

    fun showRace(race : RaceModel) {
        if(race != null)
            println(ANSI_GREEN + "Race Details [ $race ]" + ANSI_RESET)
        else
            println(ANSI_RED+ "Race Not Found..." + ANSI_RESET)
    }


    fun addRaceData(race : RaceModel) : Boolean {

        println()
        print(ANSI_BLUE + "Enter a Race Name : ")
        race.raceName = readLine()!!

        print("Enter a Race Date : ")
        race.raceDate = readLine()!!

        print("Enter a Race Size : ")
        race.raceSize = readLine()!!.toInt()

        print("Enter a Race start time : ")
        race.startTime = readLine()!!

        print("Enter a Race Winner : ")
        race.raceWinner = readLine()!!

        print("Enter a Race Venue : $ANSI_RESET")
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
            print(ANSI_BLUE + "Enter a new Race name for [ " + race.raceName + " ] : ")
            tempRaceName = readLine()!!
            print("Enter a new venue for [ " + race.venue + " ] : ")
            tempRaceVenue = readLine()!!
            print("Enter a new race size for [ " + race.raceSize + " ] : ")
            tempRaceSize = readLine()!!.toInt()
            print("Enter a new race Date for [ " + race.raceDate + " ] : ")
            tempRaceDate = readLine()!!
            print("Enter a new start time for [ " + race.startTime + " ] : ")
            tempStartTime = readLine()!!
            print("Enter a new race Winner for [ " + race.raceWinner + " ] : " + ANSI_RESET)
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
        print(ANSI_BLUE + "Enter id to Search/Update : " + ANSI_RESET)
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}