package com.android.mandi.repository

import com.android.mandi.room.dao.VillageDao
import com.android.mandi.room.entity.VillageEntity
import kotlinx.coroutines.flow.Flow

class VillageRepository(private val villageDao: VillageDao) {
    val allVillages: Flow<List<VillageEntity>> = villageDao.getAllVillages()

    suspend fun insertAll(villages: List<VillageEntity>) {
        villageDao.insertAll(*villages.toTypedArray())
    }
}