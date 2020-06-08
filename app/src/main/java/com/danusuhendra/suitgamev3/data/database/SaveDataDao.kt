package com.danusuhendra.suitgamev3.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.danusuhendra.suitgamev3.data.database.model.SaveBattle

@Dao
interface SaveDataDao {
    @Query("DELETE FROM saveData WHERE id = (SELECT MAX(id) FROM saveData WHERE userId = :userId)")
    suspend fun getLatestSaveBattle(userId: String)

    @Query("SELECT * FROM saveData WHERE userId = :userId")
    suspend fun getSaveByUserId(userId : String) : MutableList<SaveBattle>

    @Insert(onConflict = REPLACE)
    suspend fun insert(saveBattle: SaveBattle)

    @Delete
    suspend fun delete(saveBattle: SaveBattle)
}