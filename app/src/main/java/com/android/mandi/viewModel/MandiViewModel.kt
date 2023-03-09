package com.android.mandi.viewModel

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.mandi.helper.LoyaltyTextChangedListener
import com.android.mandi.helper.SellersTextChangedListener
import com.android.mandi.repository.MandiRepository
import com.android.mandi.view.ResultActivity
import kotlinx.coroutines.launch


// here is the Main Logic class where implementing all the logics for the produce

class MandiViewModel(private val repository: MandiRepository) : ViewModel(),
    SellersTextChangedListener,
    LoyaltyTextChangedListener {

    private val _price = MutableLiveData<String>()
    val price: LiveData<String> = _price

    private val _loyaltyIndex = MutableLiveData<Double>()
    val loyaltyIndex: LiveData<Double> = _loyaltyIndex

    private val _loyaltyID = MutableLiveData<String>()
    val loyaltyID: LiveData<String> = _loyaltyID

    private val _sellerName = MutableLiveData<String>()
    val sellerName: LiveData<String> = _sellerName

    private var loyaltyIDs: String? = null
    private var sellerNames: String? = null

    private var TEXT_WATCHER_SELLER_FLAG: Boolean = false
    private var TEXT_WATCHER_LOYALTY_FLAG: Boolean = false

    private val _tons = MutableLiveData<String>()
    val tons: LiveData<String> = _tons

    // here calculating the price according to seller, price and weight
    fun calculatePrice(
        villageName: String,
        weight: String,
        sellerName: String?,
        context: Context?
    ) {
        viewModelScope.launch {
//            here is the main calculating the price
            val price = repository.getPriceForProduce(villageName, weight.toDouble(), sellerName)
            _price.value = price
//            fetching loyalty index from DB
            val loyaltyIndex = repository.getLoyaltyIndex(sellerName)
            _loyaltyIndex.value = loyaltyIndex

//            for next activity
            Handler(Looper.getMainLooper()).postDelayed({
                val mIntent = Intent(context, ResultActivity::class.java)
                mIntent.putExtra("sellerName", sellerName)
                mIntent.putExtra("price", price)
                mIntent.putExtra("weight", weight)
                context?.startActivity(mIntent)
            }, 5000)

        }
    }

    //    Interface implemented and checking the data according to seller's name
//    if the sellers name matched according the DB then it will set loyalty card id automatically
    override fun onSellersTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        TEXT_WATCHER_SELLER_FLAG = sellerNames.isNullOrEmpty()
        println("Data sellerNames : ${TEXT_WATCHER_SELLER_FLAG}")
        if (TEXT_WATCHER_SELLER_FLAG) {
            if (s.toString() in repository.getSellerName()) {
                loyaltyIDs = repository.getCardID(s.toString())
                println("Data $loyaltyIDs")
                if (loyaltyIDs != null) {
                    _loyaltyID.value = loyaltyIDs!!
                    val loyaltyIndex = repository.getLoyaltyIndex(s.toString())
                    _loyaltyIndex.value = loyaltyIndex
                    println("Passed ${loyaltyIDs}")
                }
            }
        }
    }

    //    Interface implemented and checking the data according to loyalty card ID
//    if the loyalty card ID matched according the DB then it will set sellers name automatically
    override fun onLoyaltyTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        TEXT_WATCHER_LOYALTY_FLAG = loyaltyIDs.isNullOrEmpty()
        println("Data loyaltyIDs : ${TEXT_WATCHER_LOYALTY_FLAG}")
        if (TEXT_WATCHER_LOYALTY_FLAG) {
            if (s.toString() in repository.getLoyaltyId()) {
                sellerNames = repository.getSellerNameById(s.toString())
                println("Data $sellerNames")
                if (sellerNames != null) {
                    _sellerName.value = sellerNames!!
                    println("Passed ${repository.getSellerNameById(s.toString())}")
                }
            }
        }
    }
}