package android.banananabandit.figuresupdate

import java.util.*
import kotlin.collections.ArrayList

data class FinancialWeek(
    val weekNumber: Int,
    val weekTarget: Int,
    val weekTargetAchieved: Int,
    val sundayFigure: Int,
    val mondayFigure: Int,
    val tuesdayFigure: Int,
    val wednesdayFigure: Int,
    val thursdayFigure: Int,
    val fridayFigure: Int,
    val saturdayFigure: Int,

    val weekCompleted: Boolean = true,
    val isCurrentWeek: Boolean = false

) {
    companion object {
        private var weekNumeral: Int = 0
        private val random = Random()


        fun generateListOfWeeks(number: Int): ArrayList<FinancialWeek> {
            val listOfWeeks = ArrayList<FinancialWeek>()
            for (i in 0..number) {
                // TODO: later on can assign random values to the days of the week for the testing purposes before switching to room
                listOfWeeks.add(FinancialWeek(weekNumeral++, rand(3000,2000), rand(3000, 2000),
                    0, 0, 0, 0,
                    0 ,0 ,0,))
            }
            return listOfWeeks
        }

        private fun rand(from : Int, to : Int) : Int {
            return random.nextInt(to - from) + from
        }
    }
}