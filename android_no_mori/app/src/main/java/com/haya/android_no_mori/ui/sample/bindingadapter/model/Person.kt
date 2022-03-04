package com.haya.android_no_mori.ui.sample.bindingadapter.model

import androidx.annotation.DrawableRes

data class Person(
    val name:String,
    @DrawableRes val image:Int,
)