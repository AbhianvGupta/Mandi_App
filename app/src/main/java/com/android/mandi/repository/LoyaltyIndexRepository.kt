package com.android.mandi.repository

import com.android.mandi.room.dao.LoyaltyIndexDao
import com.android.mandi.room.entity.LoyaltyIndexEntity
import kotlinx.coroutines.flow.Flow

class LoyaltyIndexRepository(private val loyaltyIndexDao: LoyaltyIndexDao) {
    val allLoyaltyIndices: Flow<List<LoyaltyIndexEntity>> = loyaltyIndexDao.getAllLoyaltyIndices()

    suspend fun insertAll(indices: List<LoyaltyIndexEntity>) {
        loyaltyIndexDao.insertAll(*indices.toTypedArray())
    }
}