package com.android.mandi.helper

interface LoyaltyTextChangedListener {
    fun onLoyaltyTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
}