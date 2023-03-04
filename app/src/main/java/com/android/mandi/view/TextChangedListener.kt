package com.android.mandi.view

interface TextChangedListener {
    fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
}
