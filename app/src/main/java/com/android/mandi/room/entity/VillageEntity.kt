package com.android.mandi.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "villages")
data class VillageEntity(
    @PrimaryKey val name: String,
    val price: Double
)