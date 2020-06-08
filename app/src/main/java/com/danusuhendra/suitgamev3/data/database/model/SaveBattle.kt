package com.danusuhendra.suitgamev3.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saveData")
data class SaveBattle(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "userId") var userId : String,
    @ColumnInfo(name = "date") var date : String,
    @ColumnInfo(name = "mode") var mode : String,
    @ColumnInfo(name = "result") var result : String
)