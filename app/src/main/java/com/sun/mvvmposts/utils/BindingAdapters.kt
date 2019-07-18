package com.sun.mvvmposts.utils

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.sun.mvvmposts.utils.extension.getParentActivity

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Boolean>?) {
    val mParentActivity: AppCompatActivity? = view.getParentActivity()
    if (mParentActivity != null && visibility != null) {
        visibility.observe(mParentActivity, Observer {
                value -> view.visibility = if (value) View.VISIBLE else View.GONE
        })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val mParentActivity: AppCompatActivity? = view.getParentActivity()
    if (mParentActivity != null && text != null) {
        text.observe(mParentActivity, Observer {
                value -> view.text = value ?: ""
        })
    }
}

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}
