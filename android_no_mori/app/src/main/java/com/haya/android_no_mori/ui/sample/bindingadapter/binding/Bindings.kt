package com.haya.android_no_mori.ui.sample.bindingadapter.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

object Bindings {

    @JvmStatic
    @BindingAdapter("personImage")
    fun setPersonImage(view: ImageView, image: Int) {
        view.setImageResource(image)
    }

    @JvmStatic
    @BindingAdapter("personName")
    fun setPersonName(view: TextView, name: String) {
        view.text = name
    }
}