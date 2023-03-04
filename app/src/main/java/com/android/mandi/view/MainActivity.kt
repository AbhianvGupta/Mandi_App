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
import com.android.mandi.repository.MandiRepository
import com.android.mandi.room.MandiDB
import com.android.mandi.viewModel.MainViewModelFactory
import com.android.mandi.viewModel.MandiViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var viewModel: MandiViewModel? = null
    val db = MandiDB.getInstance(context = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(MandiRepository(db.mandiDao()))
        )[MandiViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel?.price?.observe(this) { price ->
            binding.tvGrossTotal.text = price.toString()
        }

        viewModel?.loyaltyIndex?.observe(this){loyaltyIndex ->
            binding.tvApplied.text = loyaltyIndex.toString()
        }

        viewModel?.loyaltyID?.observe(this){loyaltyID ->
            binding.etLoyalty.setText(loyaltyID)
        }

        viewModel?.sellerName?.observe(this){sellername ->
            binding.etSeller.setText(sellername)
        }

        @BindingAdapter("onTextChanged")
        fun setOnTextChangedListener(editText: EditText, textChangedListener: TextChangedListener?) {
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    textChangedListener?.onTextChanged(s, start, before, count)
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
        }

        @BindingAdapter("onTextChanged")
        fun setOnTextChangedListener(editText: EditText, textChangedListenerS: TextChangedListenerS?) {
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    textChangedListenerS?.onTextChangedS(s, start, before, count)
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
        }

    }
}