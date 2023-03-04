package com.android.mandi.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sellers")
data class SellerEntity(
    @PrimaryKey val name: String,
    val loyaltyCardId: String?
)