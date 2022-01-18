package com.haya.android_no_mori.ui.sample.firestore

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.haya.android_no_mori.databinding.ActivityFireStoreSampleBinding
import com.haya.android_no_mori.ui.sample.firestore.model.SampleUser
import com.haya.android_no_mori.ui.sample.firestore.model.State
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class FireStoreSampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFireStoreSampleBinding
    private lateinit var viewModel: FireStoreSampleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFireStoreSampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[FireStoreSampleViewModel::class.java]

//        viewModel.sampleUserData.observe(this) { sampleUser ->
//            if (sampleUser != null) {
//                binding.userName.text = sampleUser.name
//                binding.userAge.text = sampleUser.age.toString()
//            }
//        }
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
//            viewModel.addUser(sampleUser)
            GlobalScope.launch { addUser() }
        }


//        viewModel.countDownFlow.collectAs

        binding.textView2


    }

    private suspend fun addUser() {
        val sampleUser = SampleUser(
            binding.nameEt.text.toString(),
            Integer.parseInt(binding.ageEt.text.toString())
        )
        viewModel.addUser(sampleUser)?.collect { state ->
            when (state) {
                is State.Loading -> {

                    Toast.makeText(this, "投稿", Toast.LENGTH_LONG)

//                    showToast("Loading")
//                    binding.buttonAdd.isEnabled = false
                }

                is State.Success -> {
//                    Toast.makeText(this, "投稿", Toast.LENGTH_LONG)
//                    showToast("Posted")
//                    binding.fieldPostContent.setText("")
//                    binding.buttonAdd.isEnabled = true
                }

                is State.Failed -> {
//                    Toast.makeText(this, "失敗", Toast.LENGTH_LONG)
//                    showToast("Failed! ${state.message}")
//                    binding.buttonAdd.isEnabled = true
                }
            }
        }
    }


}