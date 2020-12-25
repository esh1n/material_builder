package io.material.materialthemebuilder.ui

import android.content.Context
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.widget.AutoCompleteTextView
import android.widget.TextView

fun Context.getColorStateListFromAttribute(attrId: Int): ColorStateList? {
    val typedArray = theme.obtainStyledAttributes(intArrayOf(attrId))
    return typedArray.getColorStateList(0).apply { typedArray.recycle() }
}

fun TextView.doAfterTextChanged(callback: (String?, String) -> Unit): (() -> Unit) {
    var previousValue: String? = null
    val textWatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
            if (this@doAfterTextChanged !is AutoCompleteTextView || !isPerformingCompletion) {
                callback(previousValue, text.toString())
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            previousValue = s?.toString()
        }

        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) = Unit
    }
    addTextChangedListener(textWatcher)

    return {
        removeTextChangedListener(textWatcher)
    }
}