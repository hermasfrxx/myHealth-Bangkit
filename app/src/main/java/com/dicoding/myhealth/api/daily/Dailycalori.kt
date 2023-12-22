package com.dicoding.myhealth.api.daily

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "daily_calori")
data class Dailycalori(
    val id : Int,
    val cal : Int

):Serializable
