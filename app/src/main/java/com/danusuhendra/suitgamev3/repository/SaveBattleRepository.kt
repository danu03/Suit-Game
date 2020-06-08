package com.danusuhendra.suitgamev3.repository

import com.danusuhendra.suitgamev3.data.database.AppDatabase
import com.danusuhendra.suitgamev3.data.database.SaveDataDao
import com.danusuhendra.suitgamev3.data.database.model.SaveBattle

class SaveBattleRepository(private val appDatabase: AppDatabase) {
    private var saveDataDao: SaveDataDao = appDatabase.saveDataDao()

    suspend fun getSaveByUserId(userId : String, onGetSaveBattle : (MutableList<SaveBattle>) -> Unit) {
        val result = saveDataDao.getSaveByUserId(userId)
        onGetSaveBattle(result)
    }

    suspend fun getLatestSaveBattle(userId: String) {
        return saveDataDao.getLatestSaveBattle(userId)
    }

    suspend fun insertSaveBattle(saveBattle: SaveBattle) {
        saveDataDao.insert(saveBattle)
    }

    suspend fun deleteSaveBattle(saveBattle: SaveBattle) {
        saveDataDao.delete(saveBattle)
    }
}