package com.sun.mvvmposts.utils.extension

import android.content.ContextWrapper
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sun.mvvmposts.R
import java.net.UnknownHostException

fun View.getParentActivity(): AppCompatActivity? {
    var mContext = this.context
    while (mContext is ContextWrapper) {
        if (mContext is AppCompatActivity) {
            return mContext
        }
        mContext = mContext.baseContext
    }
    return null
}

fun View.showError(error: Throwable, action: Pair<Int, View.OnClickListener>?) {
    val mErrorString = when(error) {
        is UnknownHostException -> resources.getString(R.string.post_error)
        else -> error.localizedMessage
    }
    val mSnackBar = Snackbar.make(this, mErrorString, Snackbar.LENGTH_INDEFINITE)
    if (action != null) {
        val (resId, listener) = action
        mSnackBar.setAction(resId, listener)
    }
    mSnackBar.show()
}
