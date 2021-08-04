package com.zxf.basic.expand

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * EditText的扩展
 *
 * @author Jack 2021-05-01 18:27
 */
fun EditText.afterTextChanged(afterTextChanged: (text: String) -> Unit) {
    addTextChangedListener(object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
            afterTextChanged(p0.toString())
        }

    })
}