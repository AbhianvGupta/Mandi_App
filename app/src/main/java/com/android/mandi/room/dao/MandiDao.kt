package com.android.mandi.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.mandi.room.entity.LoyaltyIndexEntity
import com.android.mandi.room.entity.SellerEntity
import com.android.mandi.room.entity.VillageEntity

@Dao
interface MandiDao {

    // inserting data of villages name
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVillage(vararg village: VillageEntity)

    // inserting data of Seller name
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeller(vararg seller: SellerEntity)

    // inserting data of Loyalty card id
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLoyaltyCard(vararg card: LoyaltyIndexEntity)

    // query to get all villages name
    @Query("SELECT * FROM villages WHERE name = :name")
    fun getVillageByName(name: String): VillageEntity?

    // query to get all Seller name
    @Query("SELECT * FROM sellers WHERE name = :name")
    fun getSellerByName(name: String): SellerEntity?

    // query to get all Loyalty Card ID
    @Query("SELECT * FROM sellers WHERE loyaltyCardId = :cardId")
    fun getSellerByCardId(cardId: String): SellerEntity?

    @Query("SELECT * FROM loyalty_index WHERE cardId = :cardId")
    fun getLoyaltyCardById(cardId: String): LoyaltyIndexEntity?

    //    query to get all Seller name
    @Query("SELECT name FROM sellers")
    fun getAllSellers(): List<String>

    //    query to get card id by seller's name
    @Query("SELECT loyaltyCardId FROM sellers WHERE name = :sName")
    fun getCardIDByName(sName: String): String

    // query to get all loyalty card id's
    @Query("SELECT loyaltyCardId FROM sellers")
    fun getAllIds(): List<String>

    //    query to get all seller name by card id
    @Query("SELECT name FROM sellers WHERE loyaltyCardId = :cardID")
    fun getSellerNameByCardId(cardID: String): String

}