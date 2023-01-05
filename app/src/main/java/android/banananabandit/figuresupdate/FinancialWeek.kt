package android.banananabandit.figuresupdate

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "week")
data class FinancialWeek(
    // TODO: still probably going to have to reintroduce either the id or code the week number as something like 43_2023
    
    @PrimaryKey
    @ColumnInfo(name = "week_number")
    val weekNumber: Int,

    @ColumnInfo(name = "week_target")
    val weekTarget: Int,

    @ColumnInfo(name = "week_target_achieved")
    val weekTargetAchieved: Int,

    @ColumnInfo(name = "target_achieved")
    val weeklyTargetIsAchieved: Boolean,

    val sundayFigure: Int,
    val mondayFigure: Int,
    val tuesdayFigure: Int,
    val wednesdayFigure: Int,
    val thursdayFigure: Int,
    val fridayFigure: Int,
    val saturdayFigure: Int,

    val sundayTransactions: Int,
    val mondayTransactions: Int,
    val tuesdayTransactions: Int,
    val wednesdayTransactions: Int,
    val thursdayTransactions: Int,
    val fridayTransactions: Int,
    val saturdayTransactions: Int,

    val weekCompleted: Boolean = true,
    val isCurrentWeek: Boolean = false

) {
    companion object {
        private var weekNumeral: Int = 0
        private val random = Random()


        private fun rand(from: Int, to: Int): Int {
            return random.nextInt(to - from) + from
        }

    }
    fun getIsTargetMissed(): Boolean {
        return weekTarget > weekTargetAchieved
    }
}
