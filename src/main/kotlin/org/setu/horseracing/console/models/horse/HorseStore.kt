package org.setu.horseracing.console.models.horse


interface HorseStore {
    fun findAll(): List<HorseModel>
    fun findOne(id: Long): HorseModel?
    fun create(horse: HorseModel)
    fun update(horse: HorseModel)
    fun delete(horse: HorseModel)
}