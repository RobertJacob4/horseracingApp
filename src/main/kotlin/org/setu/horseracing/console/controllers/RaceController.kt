package org.setu.horseracing.console.controllers

import mu.KotlinLogging
import org.setu.horseracing.console.models.race.RaceJSONStore
import org.setu.horseracing.console.models.race.RaceMemStore
import org.setu.horseracing.console.models.race.RaceModel
import org.setu.horseracing.console.views.RaceView

class RaceController {

    //val races = RaceMemStore()
    val races = RaceJSONStore()
    val raceView = RaceView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Horse Racing App" }
        println("Horse Racing Kotlin App Version 1.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Horse Races App" }
    }

    fun menu() :Int { return raceView.menu() }

    fun delete() {
        raceView.listRaces(races)
        var searchId = raceView.getId()
        val aRace = search(searchId)

        if(aRace != null) {
            races.delete(aRace)
            println("Race Deleted")
        }
        else
            println("Race Not Deleted")
    }

    fun add(){
        val aRace = RaceModel()

        if (raceView.addRaceData(aRace))
            races.create(aRace)
        else
            logger.info("Race Not Added")
    }


    fun list() {
        raceView.listRaces(races)
    }

    fun update() {

        raceView.listRaces(races)
        val searchId = raceView.getId()
        val aRace = search(searchId)

        if(aRace != null) {
            if(raceView.updateRaceData(aRace)) {
                races.update(aRace)
                raceView.showRace(aRace)
                logger.info("Race Updated : [ $aRace ]")
            }
            else
                logger.info("Race Not Updated")
        }
        else
            println("Race Not Updated...")
    }

    fun search() {
        val aRace = search(raceView.getId())!!
        raceView.showRace(aRace)
    }


    fun search(id: Long) : RaceModel? {
        val foundRace = races.findOne(id)
        return foundRace
    }

    fun dummyData() {
        races.create(RaceModel(raceName = "New York 200", venue = "New York Race Course", raceDate = "23/1/2022", startTime = "", raceSize = 10, raceWinner = "Pink Panther"))
        races.create(RaceModel(raceName = "May park Dash", venue = "Maypark", raceDate = "", startTime = "12/6/2022", raceSize = 12, raceWinner = "Gold Dust"))
        races.create(RaceModel(raceName = "Goldman 500", venue = "Tramore Racecourse", raceDate = "", startTime = "26/9/2022", raceSize = 15, raceWinner = "Chocolate Flash"))
    }
}