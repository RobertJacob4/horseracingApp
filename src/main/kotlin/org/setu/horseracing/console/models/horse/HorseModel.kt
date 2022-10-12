package org.setu.horseracing.console.models.horse

data class HorseModel(var id: Long = 0,
                     var horseName: String = "",
                     var horseAge: Int = 0,
                     var jockey: String = "",
                     var trainer: String = "",
)