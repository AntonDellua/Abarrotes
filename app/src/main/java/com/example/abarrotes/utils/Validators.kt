package com.example.abarrotes.utils

import android.util.Patterns
import android.text.TextUtils

fun isValidEmail(target: CharSequence): Boolean {
    return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
}

fun isEmptyInput(text: String): Boolean {
    return text == ""
}
