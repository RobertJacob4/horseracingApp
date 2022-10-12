package org.setu.horseracing.console.models.horse

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class HorseMemStore : HorseStore {

    val horses = ArrayList<HorseModel>()

    override fun findAll(): List<HorseModel> {
        return horses
    }

    override fun findOne(id: Long) : HorseModel? {
        var foundHorse: HorseModel? = horses.find { p -> p.id == id }
        return foundHorse
    }

    override fun create(horse: HorseModel) {
        horse.id = getId()
        horses.add(horse)
        logAll()
    }

    override fun update(horse: HorseModel) {
        var foundHorse = findOne(horse.id!!)
        if (foundHorse != null) {
            foundHorse.horseName = horse.horseName
            foundHorse.horseAge = horse.horseAge
            foundHorse.jockey = horse.jockey
            foundHorse.trainer = horse.trainer
        }
    }

    internal fun logAll() {
        horses.forEach { logger.info("${it}") }
    }
}