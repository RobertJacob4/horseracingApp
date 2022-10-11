package org.setu.horseracing.console.models

interface RaceStore {
    fun findAll(): List<RaceModel>
    fun findOne(id: Long): RaceModel?
    fun create(race: RaceModel)
    fun update(race: RaceModel)
}