package com.android.mandi.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "loyalty_index")
data class LoyaltyIndexEntity(
    @PrimaryKey val cardId: String,
    val loyaltyIndex: Double
)