package android.banananabandit.figuresupdate

import java.util.*

data class FinancialWeek(
    // TODO: add date range for a given week
    val weekNumber: Int,

    val weekTarget: Int,
    val weekTargetAchieved: Int,
    val weeklyTargetIsAchieved: Boolean,

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

        // Need to find a way to initialize weeklyTargetIsAchieved
        fun generateWeek(): FinancialWeek {
            return FinancialWeek(
                weekNumeral++, rand(2000, 3000), rand(2000, 3000),
                false, 0, 0, 0, 0,
                0, 0, 0,
            )
        }

        private fun rand(from: Int, to: Int): Int {
            return random.nextInt(to - from) + from
        }

    }
    fun getIsTargetMissed(): Boolean {
        return weekTarget > weekTargetAchieved
    }
}
