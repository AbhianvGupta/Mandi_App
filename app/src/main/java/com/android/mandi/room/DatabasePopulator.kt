package com.android.mandi.room

import android.content.Context
import com.android.mandi.room.MandiDB
import com.android.mandi.room.entity.LoyaltyIndexEntity
import com.android.mandi.room.entity.SellerEntity
import com.android.mandi.room.entity.VillageEntity

class DatabasePopulator {

    suspend fun populateDatabase(context: Context) {
        val db = MandiDB.getInstance(context)

        // Populate villages
        val village1 = VillageEntity("Ramnagar", 120.08)
        val village2 = VillageEntity("Munnar", 105.5)
        val village3 = VillageEntity("Kodaikanal", 95.75)
        db.mandiDao().insertVillage(village1, village2, village3)

        // Populate sellers
        val seller1 = SellerEntity("Ramu Kaka", "S18972")
        val seller2 = SellerEntity("Lakshmi Bai", "S19031")
        val seller3 = SellerEntity("John Doe", "")
        db.mandiDao().insertSeller(seller1, seller2, seller3)

        // Populate loyalty index
        val index1 = LoyaltyIndexEntity("S18972", 1.12)
        val index2 = LoyaltyIndexEntity("S19031", 1.05)
        db.mandiDao().insertLoyaltyCard(index1, index2)
    }

}