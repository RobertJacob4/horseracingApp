package org.setu.horseracing.console.models.race

data class RaceModel(var id: Long = 0,
                     var raceName: String = "",
                     var raceDate: String = "",
                     var startTime: String = "",
                     var venue: String = "",
                     var raceSize: Int = 0,
                     var raceWinner: String  = ""
){

}