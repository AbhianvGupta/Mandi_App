package com.android.mandi.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.mandi.room.entity.LoyaltyIndexEntity
import com.android.mandi.room.entity.SellerEntity
import com.android.mandi.room.entity.VillageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MandiDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVillage(vararg village: VillageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeller(vararg seller: SellerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLoyaltyCard(vararg card: LoyaltyIndexEntity)

    @Query("SELECT * FROM villages WHERE name = :name")
    fun getVillageByName(name: String): VillageEntity?

    @Query("SELECT * FROM sellers WHERE name = :name")
    fun getSellerByName(name: String): SellerEntity?

    @Query("SELECT * FROM sellers WHERE loyaltyCardId = :cardId")
    fun getSellerByCardId(cardId: String): SellerEntity?

    @Query("SELECT * FROM loyalty_index WHERE cardId = :cardId")
    fun getLoyaltyCardById(cardId: String): LoyaltyIndexEntity?

    @Query("SELECT name FROM sellers")
    fun getAllSellers(): List<String>

    @Query("SELECT loyaltyCardId FROM sellers WHERE name = :sName")
    fun getCardIDByName(sName: String): String

    @Query("SELECT loyaltyCardId FROM sellers")
    fun getAllIds(): List<String>

    @Query("SELECT name FROM sellers WHERE loyaltyCardId = :cardID")
    fun getSellerNameByCardId(cardID: String) : String
}