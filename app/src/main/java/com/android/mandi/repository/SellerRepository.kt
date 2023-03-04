package com.android.mandi.repository

import com.android.mandi.room.dao.SellerDao
import com.android.mandi.room.entity.SellerEntity
import kotlinx.coroutines.flow.Flow

class SellerRepository(private val sellerDao: SellerDao) {
    val allSellers: Flow<List<SellerEntity>> = sellerDao.getAllSellers()

    suspend fun insertAll(sellers: List<SellerEntity>) {
        sellerDao.insertAll(*sellers.toTypedArray())
    }
}