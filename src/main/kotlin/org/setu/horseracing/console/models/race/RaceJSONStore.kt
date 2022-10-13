package org.setu.horseracing.console.models.race

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import org.setu.horseracing.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "races.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<RaceModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class RaceJSONStore : RaceStore {

    var races = mutableListOf<RaceModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<RaceModel> {
        return races
    }

    override fun findOne(id: Long) : RaceModel? {
        var foundRace: RaceModel? = races.find { p -> p.id == id }
        return foundRace
    }

    override fun create(race: RaceModel) {
        race.id = generateRandomId()
        races.add(race)
        serialize()
    }

    override fun update(race: RaceModel) {
        var foundRace = findOne(race.id!!)
        if (foundRace != null) {
            foundRace.raceName = race.raceName
            foundRace.venue = race.venue
            foundRace.raceDate = race.raceDate
            foundRace.raceSize = race.raceSize
            foundRace.startTime = race.startTime
            foundRace.raceWinner = race.raceWinner
        }
        serialize()
    }

    override fun delete(race: RaceModel) {
        races.remove(race)
        serialize()
    }

    internal fun logAll() {
        races.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(races, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        races = Gson().fromJson(jsonString, listType)
    }
}