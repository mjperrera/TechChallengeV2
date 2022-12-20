package com.example.mjperrera.cardbinidentifier_kotlin.Base

import android.widget.EditText

object Utils {
    fun editTextSetContentMemorizeSelection(editText: EditText, charSequence: CharSequence) {
        var selectionStart = editText.selectionStart
        var selectionEnd = editText.selectionEnd
        editText.setText(charSequence.toString())
        if (selectionStart > charSequence.toString().length) {
            selectionStart = charSequence.toString().length
        }
        if (selectionStart < 0) {
            selectionStart = 0
        }
        if (selectionEnd > charSequence.toString().length) {
            selectionEnd = charSequence.toString().length
        }
        if (selectionEnd < 0) {
            selectionEnd = 0
        }
        editText.setSelection(selectionStart, selectionEnd)
    }
}