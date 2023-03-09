package com.android.mandi.repository

import com.android.mandi.room.dao.MandiDao

class MandiRepository(private val dao: MandiDao) {

    // getting all sellers name
    fun getSellerName(): String {
        return dao.getAllSellers().toString()
    }

    // getting all loyalty card Id's
    fun getLoyaltyId(): String {
        return dao.getAllIds().toString()
    }


    // getting seller's name according to card ID
    fun getSellerNameById(sCardId: String?): String {
        return dao.getSellerNameByCardId(sCardId!!)
    }

    // getting card Id according to sellers name
    fun getCardID(sellerName: String?): String {
        return dao.getCardIDByName(sellerName!!)
    }

    // getting loyalty Index, if the seller is registered else 0.98
    fun getLoyaltyIndex(sellerName: String?): Double {
        val loyaltyCardId = dao.getSellerByName(sellerName ?: "")?.loyaltyCardId ?: ""
        val loyaltyCard = dao.getLoyaltyCardById(loyaltyCardId)
        return loyaltyCard?.loyaltyIndex ?: 0.98
    }

    // calculating the price according to weight(in kgs), getting loyalty index(by seller is registered or not)
    // and price according to village name
    fun getPriceForProduce(villageName: String, weight: Double, sellerName: String?): String {
        val village = dao.getVillageByName(villageName)
        val loyaltyCardId = dao.getSellerByName(sellerName ?: "")?.loyaltyCardId ?: ""
        val loyaltyCard = dao.getLoyaltyCardById(loyaltyCardId)
        val loyaltyIndex = loyaltyCard?.loyaltyIndex ?: 0.98
        val price: Double = village?.price ?: 0.0

        // price is set to two decimal precision points.
        return String.format("%,.2f", weight * loyaltyIndex * price) + " INR"
    }
}