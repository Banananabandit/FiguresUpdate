package android.banananabandit.figuresupdate

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "week")
data class FinancialWeek(
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "id")
//    val id: Int,

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
