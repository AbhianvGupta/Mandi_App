package com.android.mandi.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.mandi.R
import com.android.mandi.databinding.ActivityMainBinding
import com.android.mandi.helper.LoyaltyTextChangedListener
import com.android.mandi.helper.SellersTextChangedListener
import com.android.mandi.repository.MandiRepository
import com.android.mandi.room.MandiDB
import com.android.mandi.viewModel.MainViewModelFactory
import com.android.mandi.viewModel.MandiViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mandiViewModel: MandiViewModel? = null
    val db = MandiDB.getInstance(context = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mandiViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(MandiRepository(db.mandiDao()))
        )[MandiViewModel::class.java]

        binding.mandiViewModel = mandiViewModel
        binding.lifecycleOwner = this

        // GrossTotal for all the calculation where weight * loyaltyIndex * price
        mandiViewModel?.price?.observe(this) { price ->
            binding.tvGrossTotal.text = price
        }

        // loyalty index according to seller name
        mandiViewModel?.loyaltyIndex?.observe(this) { loyaltyIndex ->
            binding.tvApplied.text =
                resources.getString(R.string.tvApplied) + " " + loyaltyIndex.toString()
        }

        // setting text here for loyalty ID
        mandiViewModel?.loyaltyID?.observe(this) { loyaltyID ->
            binding.etLoyalty.setText(loyaltyID)
        }

        // setting text here for seller name
        mandiViewModel?.sellerName?.observe(this) { sellerName ->
            binding.etSeller.setText(sellerName)
        }

        // setting text here for weight
        mandiViewModel?.tons?.observe(this) { ton ->
            binding.etGrossWeight.setText(ton.toString())
        }

        // here binding is happening where SellersTextChangedListener is using
        // for checking data according to Seller Name
        @BindingAdapter("onTextChanged")
        fun setOnSellersTextChangedListener(
            editText: EditText,
            sellersTextChangedListener: SellersTextChangedListener?
        ) {
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    sellersTextChangedListener?.onSellersTextChanged(s, start, before, count)
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
        }

        // here binding adapter where LoyaltyTextChangedListener is using
        // for checking data according to Loyalty ID
        @BindingAdapter("onTextChanged")
        fun setOnLoyaltyTextChangedListener(
            editText: EditText,
            loyaltyTextChangedListener: LoyaltyTextChangedListener?
        ) {
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    loyaltyTextChangedListener?.onLoyaltyTextChanged(s, start, before, count)
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
        }
    }
}
