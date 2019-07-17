package com.sun.mvvmposts.utils.extension

import android.content.ContextWrapper
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

fun View.getParentActivity(): AppCompatActivity? {
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

fun View.showError(@StringRes errorMessage: Int, action: Pair<Int, View.OnClickListener>?): Snackbar {
    val snackBar = Snackbar.make(this, errorMessage, Snackbar.LENGTH_INDEFINITE)
    if (action != null) {
        val (resId, listener) = action
        snackBar.setAction(resId, listener)
    }
    snackBar.show()
    return snackBar
}

fun View.hideError(snackbar: Snackbar?) {
    snackbar?.dismiss()
}