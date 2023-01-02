package android.banananabandit.figuresupdate

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FinancialWeek::class],
    version = 1,
    exportSchema = false
)
abstract class FinancialWeeksDb : RoomDatabase() {
    abstract val dao: FinancialWeekDao

    companion object {
        @Volatile
        private var INSTANCE: FinancialWeekDao? = null
        fun getDaoInstance(context: Context): FinancialWeekDao {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = buildDatabase(context).dao
                    INSTANCE = instance
                }
                return instance
            }
        }

        private fun buildDatabase(context: Context):
                FinancialWeeksDb = Room.databaseBuilder(
            context.applicationContext,
            FinancialWeeksDb::class.java,
            "weeks_database"
        ).fallbackToDestructiveMigration()
            .build()
    }
}