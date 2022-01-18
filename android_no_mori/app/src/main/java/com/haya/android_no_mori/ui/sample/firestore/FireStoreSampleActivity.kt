package com.haya.android_no_mori.ui.sample.firestore

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.haya.android_no_mori.databinding.ActivityFireStoreSampleBinding
import com.haya.android_no_mori.ui.sample.firestore.model.SampleUser


class FireStoreSampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFireStoreSampleBinding
    private lateinit var viewModel: FireStoreSampleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFireStoreSampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[FireStoreSampleViewModel::class.java]

        viewModel.getSampleUser("test").observe(this) { sampleUser ->
            if (sampleUser != null) {
                binding.userName.text = sampleUser.name
                binding.userAge.text = sampleUser.age.toString()
            }
        }
        binding.registerButton.setOnClickListener {
            if (binding.nameEt.text.toString().isEmpty() || binding.ageEt.text.toString()
                    .isEmpty()
            ) {
                Toast.makeText(this, "名前と年齢を入力してください", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val sampleUser = SampleUser(
                binding.nameEt.text.toString(),
                Integer.parseInt(binding.ageEt.text.toString())
            )
            viewModel.addSampleUser(sampleUser)
        }
    }
}