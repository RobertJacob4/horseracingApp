package org.setu.horseracing.console.models.race

import org.setu.horseracing.console.models.race.RaceModel

interface RaceStore {
    fun findAll(): List<RaceModel>
    fun findOne(id: Long): RaceModel?
    fun create(race: RaceModel)
    fun update(race: RaceModel)
    fun delete(race: RaceModel)
}