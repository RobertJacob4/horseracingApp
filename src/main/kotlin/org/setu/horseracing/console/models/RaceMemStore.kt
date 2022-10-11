package org.setu.horseracing.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class RaceMemStore : RaceStore {

    val races = ArrayList<RaceModel>()

    override fun findAll(): List<RaceModel> {
        return races
    }

    override fun findOne(id: Long) : RaceModel? {
        var foundRace: RaceModel? = races.find { p -> p.id == id }
        return foundRace
    }

    override fun create(race: RaceModel) {
        race.id = getId()
        races.add(race)
        logAll()
    }

    override fun update(race: RaceModel) {
        var foundRace = findOne(race.id!!)
        if (foundRace != null) {
            foundRace.raceName = race.raceName
            foundRace.venue = race.venue
            foundRace.raceDate = race.raceDate
            foundRace.startTime = race.startTime
            foundRace.raceSize = race.raceSize
            foundRace.raceWinner = race.raceWinner
        }
    }

    internal fun logAll() {
        races.forEach { logger.info("${it}") }
    }
}