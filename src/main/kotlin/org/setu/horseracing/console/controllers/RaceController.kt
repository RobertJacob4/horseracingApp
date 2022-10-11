package org.setu.horseracing.console.controllers

import mu.KotlinLogging
import org.setu.horseracing.console.models.RaceMemStore
import org.setu.horseracing.console.models.RaceModel
import org.setu.horseracing.console.views.RaceView

class RaceController {

    val races = RaceMemStore()
    val raceView = RaceView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Placemark Console App" }
        println("Placemark Kotlin App Version 1.0")
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
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Horse Racess App" }
    }

    fun menu() :Int { return raceView.menu() }

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
        races.create(RaceModel(raceName = "New York New York", venue = "So Good They Named It Twice"))
        races.create(RaceModel(raceName = "Ring of Kerry", venue = "Some place in the Kingdom"))
        races.create(RaceModel(raceName = "Waterford City", venue = "You get great Blaas Here!!"))
    }
}