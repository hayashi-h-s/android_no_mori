package com.haya.android_no_mori.ui.sample.firestore

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
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
            if (binding.nameEt.text.toString().isEmpty()
                || binding.userId.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "名前と年齢を入力してください", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val name = binding.nameEt.text.toString()
            val userId = binding.userId.text.toString()
            val sampleUser = SampleUser(name, userId)
            lifecycleScope.launch {
                addUser(sampleUser)
            }
        }
    }

    private suspend fun addUser(sampleUser: SampleUser) {
        viewModel.addUser(sampleUser)?.collect { state ->
            when (state) {
                is State.Loading -> {
                    binding.progressLayout.isVisible = true
                }
                is State.Success -> {
                    binding.progressLayout.isVisible = false

                    print(" 【ログ】 ${javaClass.name} = state.data = ${state.data}")

                    showToast("データ追加完了 ")
                }

                is State.Failed -> {
                    binding.progressLayout.isVisible = false
                    AlertDialog.Builder(this@FireStoreSampleActivity)
                        .setTitle("投稿に失敗しました")
                        .setMessage("通信状況を確認してください")
                        .setPositiveButton("OK") { dialog, which ->
                            // TODO:Yesが押された時の挙動
                        }
                        .show()
                }
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(this@FireStoreSampleActivity, text, Toast.LENGTH_LONG).show()
    }
}