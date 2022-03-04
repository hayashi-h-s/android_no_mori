package com.haya.android_no_mori.ui.sample.bindingadapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.haya.android_no_mori.R
import com.haya.android_no_mori.databinding.ActivitySampleBindingAdapterBinding
import com.haya.android_no_mori.ui.sample.bindingadapter.model.Person

class SampleBindingAdapterActivity : AppCompatActivity() {
    // 参考サイト https://www.youtube.com/watch?v=EtkAyiKYjCw&list=PLcbl7mspGnCJxqv_QVptknmbmMQ0Y2r36&index=7&t=64s

    private lateinit var binding: ActivitySampleBindingAdapterBinding

    var person = Person("名無 太郎", R.drawable.ic_user)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleBindingAdapterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // UIに反映される
        binding.person = person

        binding.personChangeButton.setOnClickListener {
            if (binding.userNameText.text == "名無 太郎") {
                binding.person = Person("猫田 ニャン助", R.drawable.cat)
            } else {
                binding.person = Person("名無 太郎", R.drawable.ic_user)
            }

        }
    }
}