package org.setu.horseracing.console.controllers

import mu.KotlinLogging
import org.setu.horseracing.console.models.horse.HorseJSONStore
import org.setu.horseracing.console.models.horse.HorseMemStore
import org.setu.horseracing.console.models.horse.HorseModel
import org.setu.horseracing.console.views.HorseView


class HorseController {

    //val horses = HorseMemStore()
    val horses = HorseJSONStore()
    val horseView = HorseView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Horse Race App" }
        println("Horse Race App Version 1.0")
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
        logger.info { "Shutting Down Horse Race App" }
    }

    fun menu() :Int { return horseView.menu() }

    fun add(){
        val tempHorse = HorseModel()

        if (horseView.addHorseData(tempHorse))
            horses.create(tempHorse)
        else
            logger.info("Horse Not Added")
    }

    fun list() {
        horseView.listHorses(horses)
    }

    fun delete() {
        horseView.listHorses(horses)
        var searchId = horseView.getId()
        val aHorse = search(searchId)

        if(aHorse != null) {
            horses.delete(aHorse)
            println("Horse Deleted")
        }
        else
            println("Horse Not Deleted")
    }

    fun update() {

        horseView.listHorses(horses)
        val searchId = horseView.getId()
        val aHorse = search(searchId)

        if(aHorse != null) {
            if(horseView.updateHorseData(aHorse)) {
                horses.update(aHorse)
                horseView.showHorse(aHorse)
                logger.info("Horse Updated : [ $aHorse ]")
            }
            else
                logger.info("Horse Not Updated")
        }
        else
            println("Horse Not Updated...")
    }

    fun search() {
        val tempHorse = search(horseView.getId())!!
        horseView.showHorse(tempHorse)
    }


    fun search(id: Long) : HorseModel? {
        val foundHorse = horses.findOne(id)
        return foundHorse
    }

    fun dummyData() {
        horses.create(
            HorseModel(horseName = "gold dust", horseAge = 4, jockey = "John Phillips", trainer = "JP McManus"))
        horses.create(
            HorseModel(horseName = "Lightning", horseAge = 5, jockey = "Johnny Delahunty", trainer = "Paddy Power"))
        horses.create(
            HorseModel(horseName = "The queens gambit", horseAge = 3, jockey = "Alex Power", trainer = "John Foley"))
    }
}