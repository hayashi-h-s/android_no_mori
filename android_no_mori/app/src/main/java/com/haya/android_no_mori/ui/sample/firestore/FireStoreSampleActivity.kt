package com.haya.android_no_mori.ui.sample.firestore

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.haya.android_no_mori.databinding.ActivityFireStoreSampleBinding
import com.haya.android_no_mori.ui.sample.firestore.model.SampleUser


class FireStoreSampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFireStoreSampleBinding
    private var db: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFireStoreSampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = FirebaseFirestore.getInstance()
        binding.registerButton.setOnClickListener {
            if (binding.nameEt.text.toString().isEmpty() || binding.ageEt.text.toString()
                    .isEmpty()
            ) {
                Toast.makeText(this, "名前と年齢を入力してください", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            binding.progressLayout.visibility = View.VISIBLE
            val sampleUser = SampleUser(
                binding.nameEt.text.toString(),
                Integer.parseInt(binding.ageEt.text.toString())
            )
            db!!.collection("user")
                .add(sampleUser)
                .addOnSuccessListener {
                    binding.progressLayout.visibility = View.GONE
                    Toast.makeText(this, "登録成功", Toast.LENGTH_SHORT).show()
                    setSampleUserInformation(it.id)
                }.addOnFailureListener {
                    Toast.makeText(this, "登録失敗", Toast.LENGTH_SHORT).show()
                }
        }
    }

    // Get single data from FireStore
    private fun setSampleUserInformation(documentId: String) {
        val docRef = db!!.collection("user").document(documentId)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val user = document.toObject(SampleUser::class.java) //Commentary: Change the retrieved data to SampleUserModel
                    binding.userName.text = user?.name
                    binding.userAge.text = user?.age.toString()
                } else {
                    Log.d("TAG", "No such document")
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "データ取得失敗", Toast.LENGTH_SHORT).show()
            }
    }
}