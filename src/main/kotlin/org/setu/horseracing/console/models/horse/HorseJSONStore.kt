package org.setu.horseracing.console.models.horse

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import org.setu.horseracing.console.helpers.*

import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "horses.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<HorseModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class HorseJSONStore : HorseStore {

    var horses = mutableListOf<HorseModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<HorseModel> {
        return horses
    }

    override fun findOne(id: Long) : HorseModel? {
        var foundHorse: HorseModel? = horses.find { p -> p.id == id }
        return foundHorse
    }

    override fun create(horse: HorseModel) {
        horse.id = generateRandomId()
        horses.add(horse)
        serialize()
    }

    override fun update(horse: HorseModel) {
        var foundHorse = findOne(horse.id!!)
        if (foundHorse != null) {
            foundHorse.horseName = horse.horseName
            foundHorse.horseAge = horse.horseAge
            foundHorse.jockey = horse.jockey
            foundHorse.trainer = horse.trainer
        }
        serialize()
    }


    override fun delete(horse: HorseModel) {
        horses.remove(horse)
        serialize()
    }

    internal fun logAll() {
        horses.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(horses, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        horses = Gson().fromJson(jsonString, listType)
    }
}