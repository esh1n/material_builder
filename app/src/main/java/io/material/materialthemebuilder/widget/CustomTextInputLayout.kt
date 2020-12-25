package io.material.materialthemebuilder.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import io.material.materialthemebuilder.R
import io.material.materialthemebuilder.ui.doAfterTextChanged
import io.material.materialthemebuilder.ui.getColorStateListFromAttribute

class AppTextInputLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextInputLayout(context, attrs, defStyleAttr) {

    private val inactiveColor = context.getColorStateListFromAttribute(R.attr.boxColorList)
    private val activeColor = context.getColorStateListFromAttribute(R.attr.boxColorListActive)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        editText?.doAfterTextChanged { prev, current -> getColorList(prev, current)?.let(::setBoxStrokeColorStateList) }
    }

    private fun getColorList(prev: String?, current: String) = when {
        !prev.isNullOrBlank() && current.isNotBlank() -> null
        prev.isNullOrBlank() && current.isNotBlank() -> activeColor
        else -> inactiveColor
    }

    override fun setErrorEnabled(enabled: Boolean) {
        super.setErrorEnabled(enabled)
        if (!enabled) {
            return
        }
        findViewById<TextView>(R.id.textinput_error)?.apply {
            textAlignment = View.TEXT_ALIGNMENT_VIEW_END
        }
    }
}