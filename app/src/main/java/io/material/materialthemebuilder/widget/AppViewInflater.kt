package io.material.materialthemebuilder.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.theme.MaterialComponentsViewInflater
import io.material.materialthemebuilder.R

class AppViewInflater : MaterialComponentsViewInflater() {

    public override fun createView(context: Context, name: String?, attrs: AttributeSet?): View? {
        return when (name) {
            AppTextInputLayout::class.qualifiedName -> {
                AppTextInputLayout(context, attrs, R.attr.customTextInputStyle)
            }
            else -> {
                super.createView(context, name, attrs)
            }
        }
    }

}