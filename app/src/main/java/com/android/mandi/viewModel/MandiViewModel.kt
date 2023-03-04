package com.android.mandi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.mandi.Constants
import com.android.mandi.repository.MandiRepository
import com.android.mandi.view.TextChangedListener
import com.android.mandi.view.TextChangedListenerS
import kotlinx.coroutines.launch

class MandiViewModel(private val repository: MandiRepository) : ViewModel(), TextChangedListener,
    TextChangedListenerS {

    private val _price = MutableLiveData<Double>()
    val price: LiveData<Double> = _price

    private val _loyaltyIndex = MutableLiveData<Double>()
    val loyaltyIndex: LiveData<Double> = _loyaltyIndex

    private val _loyaltyID = MutableLiveData<String>()
    val loyaltyID: LiveData<String> = _loyaltyID

    private val _sellerName = MutableLiveData<String>()
    val sellerName: LiveData<String> = _sellerName

    fun calculatePrice(villageName: String, weight: String, sellerName: String?) {
        viewModelScope.launch {
            val price = repository.getPriceForProduce(villageName, weight.toDouble(), sellerName)
            _price.value = price
            val loyaltyIndex = repository.getLoyaltyIndex(sellerName)
            _loyaltyIndex.value = loyaltyIndex
        }
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        println("onTextChanged ${repository.getSellerName().contains(s.toString())}")
        Constants.TEXT_WATCHER_SELLER_FLAG = true
        if (!Constants.TEXT_WATCHER_LOYALTY_FLAG) {
            if (repository.getSellerName().contains(s.toString())) {
                val loyaltyID = repository.getCardID(s.toString())
                _loyaltyID.value = loyaltyID
                println("Passed ${repository.getCardID(s.toString())}")
            }
        }
    }

    override fun onTextChangedS(s: CharSequence?, start: Int, before: Int, count: Int) {
        println("onTextChangedS ${repository.getLoyaltyId()}")
        Constants.TEXT_WATCHER_LOYALTY_FLAG = true
        if (!Constants.TEXT_WATCHER_SELLER_FLAG) {
            if (repository.getLoyaltyId().contains(s.toString())) {
                val sellerName = repository.getSellerNameById(s.toString())
                _sellerName.value = sellerName
                println("Passed ${repository.getSellerNameById(s.toString())}")
            }
        }
    }

}