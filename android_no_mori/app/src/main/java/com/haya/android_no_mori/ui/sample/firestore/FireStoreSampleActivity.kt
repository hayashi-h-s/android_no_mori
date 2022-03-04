package com.haya.android_no_mori.ui.sample.firestore

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
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
        setRecyclerView()
        lifecycleScope.launch {
            getAllUsers()
        }
        binding.registerButton.setOnClickListener {
            if (binding.userNameEt.text.toString().isEmpty()
                || binding.userFavoriteNumEt.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "名前と年齢を入力してください", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val name = binding.userNameEt.text.toString()
            val userId = binding.userFavoriteNumEt.text.toString()
            val sampleUser = SampleUser(name, userId)
            lifecycleScope.launch {
                addUser(sampleUser)
            }
        }
    }

    private fun setRecyclerView() {

    }

    private suspend fun addUser(sampleUser: SampleUser) {
        viewModel.addUser(sampleUser)?.collect { state ->
            when (state) {
                is State.Loading -> {
                    binding.progressLayout.isVisible = true
                }
                is State.Success -> {
                    binding.progressLayout.isVisible = false
                    binding.userNameTv.text = state.data?.name
                    binding.userIdTv.text = state.data?.favoriteNum
                    showToast("登録が成功しました")
                }
                is State.Failed -> {
                    binding.progressLayout.isVisible = false
                    AlertDialog.Builder(this@FireStoreSampleActivity)
                        .setTitle("投稿に失敗しました")
                        .setMessage("通信状況を確認してください")
                        .setPositiveButton("OK") { _, _ -> }
                        .show()
                }
            }
        }
    }

    private suspend fun getAllUsers() {
        viewModel.getAllUsers()?.collect { state ->
            when (state) {
                is State.Loading -> {
                    binding.progressLayout.isVisible = true
                }
                is State.Success -> {
                    binding.recyclerView.adapter = FireStoreSampleUserListAdapter(state.data)
                    binding.recyclerView.layoutManager = LinearLayoutManager(this@FireStoreSampleActivity)
                    binding.progressLayout.isVisible = false
                }
                is State.Failed -> {
                    AlertDialog.Builder(this@FireStoreSampleActivity)
                        .setTitle("投稿に失敗しました")
                        .setMessage("通信状況を確認してください")
                        .setPositiveButton("OK") { _, _ ->
                            // TODO:Yesが押された時の挙動
                        }
                    binding.progressLayout.isVisible = false
                }
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(this@FireStoreSampleActivity, text, Toast.LENGTH_LONG).show()
    }
}