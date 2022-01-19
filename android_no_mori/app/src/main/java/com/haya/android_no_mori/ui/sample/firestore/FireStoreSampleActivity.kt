package com.haya.android_no_mori.ui.sample.firestore

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.haya.android_no_mori.databinding.ActivityFireStoreSampleBinding
import com.haya.android_no_mori.ui.sample.firestore.model.SampleUser
import com.haya.android_no_mori.ui.sample.firestore.model.State
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

        binding.registerButton.setOnClickListener {
            if (binding.nameEt.text.toString().isEmpty() || binding.ageEt.text.toString()
                    .isEmpty()
            ) {
                Toast.makeText(this, "名前と年齢を入力してください", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//            val sampleUser = SampleUser(
//                binding.nameEt.text.toString(),
//                Integer.parseInt(binding.ageEt.text.toString())
//            )
////            viewModel.addUser(sampleUser)
            lifecycleScope.launch {
                val sampleUser = SampleUser(
                    binding.nameEt.text.toString(),
                    Integer.parseInt(binding.ageEt.text.toString())
                )
                addUser(sampleUser)
            }
        }

//        viewModel.countDownFlow.collectAs
    }

    private suspend fun addUser(sampleUser:SampleUser) {
        viewModel.addUser(sampleUser)?.collect { state ->
            when (state) {
                is State.Loading -> {

                    Log.d("TAG","Logs = Loading");

                    binding.textView2.text  =  "Loading"

                }

                is State.Success -> {

                    Log.d("TAG","is State.Success -> {");
                    showToast("Posted")
                    binding.textView2.text  =  "Success"
                }

                is State.Failed -> {

                    Log.d("TAG","Logs = is State.Failed -> {");

                    print(" 【ログ】 ${javaClass.name} = ")

                    binding.textView2.text  =  "Failed"
                }
            }
        }
    }

    fun showToast(text :String) {
        Log.d("TAG","Logs = fun showToast(text :String) { ");

        Toast.makeText(this, text, Toast.LENGTH_LONG)
    }
}