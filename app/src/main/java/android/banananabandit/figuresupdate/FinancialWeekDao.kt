package android.banananabandit.figuresupdate

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FinancialWeekDao {
    @Insert
    suspend fun insert(financialWeek: FinancialWeek)

    @Query("SELECT * FROM week")
    suspend fun getAll(): List<FinancialWeek>

    //TODO: Add here another query for retrieving a specific financial week
}