package com.android.mandi.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.android.mandi.room.dao.MandiDao
import com.android.mandi.room.entity.LoyaltyIndexEntity
import com.android.mandi.room.entity.SellerEntity
import com.android.mandi.room.entity.VillageEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MandiRepository(private val dao: MandiDao) {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVillage(village: VillageEntity) {
        withContext(Dispatchers.IO) {
            dao.insertVillage(village)
        }
    }

    suspend fun insertSeller(seller: SellerEntity) {
        withContext(Dispatchers.IO) {
            dao.insertSeller(seller)
        }
    }

    suspend fun insertLoyaltyCard(card: LoyaltyIndexEntity) {
        withContext(Dispatchers.IO) {
            dao.insertLoyaltyCard(card)
        }
    }

    fun getSellerName():String{
        return dao.getAllSellers().toString()
    }

    fun getLoyaltyId():String{
        return dao.getAllIds().toString()
    }


    fun getSellerNameById(sCardId: String?):String{
        return dao.getSellerNameByCardId(sCardId!!)
    }

    fun getCardID(sellerName: String?): String{
        return dao.getCardIDByName(sellerName!!)
    }

    fun getLoyaltyIndex(sellerName: String?) : Double{
        val loyaltyCardId = dao.getSellerByName(sellerName ?: "")?.loyaltyCardId ?: ""
        val loyaltyCard = dao.getLoyaltyCardById(loyaltyCardId)
        val loyaltyIndex = loyaltyCard?.loyaltyIndex ?: 0.98
        return loyaltyIndex
    }

    fun getPriceForProduce(villageName: String, weight: Double, sellerName: String?): Double {
        val village = dao.getVillageByName(villageName)
        val loyaltyCardId = dao.getSellerByName(sellerName ?: "")?.loyaltyCardId ?: ""
        val loyaltyCard = dao.getLoyaltyCardById(loyaltyCardId)
        val loyaltyIndex = loyaltyCard?.loyaltyIndex ?: 0.98
        val price : Double = village?.price?: 0.0

        return weight * loyaltyIndex * price
    }
}