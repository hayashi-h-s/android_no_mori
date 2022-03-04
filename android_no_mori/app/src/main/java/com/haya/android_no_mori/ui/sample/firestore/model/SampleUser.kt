package com.haya.android_no_mori.ui.sample.firestore.model

data class SampleUser(
    val name: String = "", // ""などで初期化しないと不具合になる
    val favoriteNum: String = ""
)