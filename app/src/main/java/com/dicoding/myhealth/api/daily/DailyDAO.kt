package com.dicoding.myhealth.api.daily

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DailyDAO {
    @Insert
    suspend fun addToDaily(dailycalori: Dailycalori)

    @Query("SELECT * FROM daily_calori")
    fun getCalori():LiveData<List<Dailycalori>>

    @Query("SELECT count(*) FROM daily_calori WHERE daily_calori.id= :id")
    suspend fun checkDaily(id: Int): Int

    @Query("DELETE FROM daily_calori WHERE daily_calori.id= :id")
    suspend fun deleteFromDaily(id: Int): Int
}