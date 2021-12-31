package com.haya.android_no_mori.ui.sample.gridview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haya.android_no_mori.R
import com.haya.android_no_mori.databinding.ActivityGridBinding
import com.haya.android_no_mori.ui.sample.gridview.model.Animal

class GridActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGridBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animalList = listOf(
            Animal(R.drawable.cat, "ネコ"),
            Animal(R.drawable.dog, "イヌ"),
            Animal(R.drawable.gorilla, "ゴリラ"),
            Animal(R.drawable.horse, "ウマ"),
            Animal(R.drawable.lion, "ライオン"),
            Animal(R.drawable.cat, "ネコ"),
            Animal(R.drawable.dog, "イヌ"),
            Animal(R.drawable.gorilla, "ゴリラ"),
            Animal(R.drawable.horse, "ウマ"),
            Animal(R.drawable.lion, "ライオン")
        )

        binding.recyclerView.adapter = GridAdapter(animalList)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
    }
}