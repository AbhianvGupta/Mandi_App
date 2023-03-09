package com.android.mandi.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.mandi.R
import com.android.mandi.databinding.ActivityResultBinding
import com.android.mandi.viewModel.ResultViewModel

class ResultActivity : AppCompatActivity() {

    lateinit var resultBinding: ActivityResultBinding
    private var resultViewModel: ResultViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultBinding = DataBindingUtil.setContentView(this, R.layout.activity_result)


        // getting data for seller name
        resultBinding.tvThankYouMessage.text =
            getString(R.string.thank_you_message, intent.getStringExtra("sellerName"))

        // getting data for price and weight
        resultBinding.tvThankYouPrice.text =
            getString(
                R.string.thank_you_price, intent.getStringExtra("price"),
                intent.getStringExtra("weight")
            )

        // finishing the activity
        resultBinding.btSellMore.setOnClickListener {
            finish()
        }
    }
}