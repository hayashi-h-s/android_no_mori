package com.haya.android_no_mori.ui.sample.firestore

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.haya.android_no_mori.R
import com.haya.android_no_mori.databinding.ActivityFireStoreSampleBinding

class FireStoreSampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFireStoreSampleBinding

    var nameEt: EditText? = null
    var ageEt: EditText? = null
    var registerbtn: Button? = null
    var db: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFireStoreSampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = FirebaseFirestore.getInstance()
        nameEt = findViewById(R.id.name_et)
        ageEt = findViewById(R.id.age_et)
        registerbtn = findViewById(R.id.register_button)

        // TODO:バリデーション処理は後に行う
        registerbtn?.setOnClickListener {
            val user: MutableMap<String, Any> = HashMap()
            user["name"] = binding.nameEt.text.toString()
            user["Age"] = binding.ageEt.text.toString()
            db!!.collection("user")
                .add(user)
                .addOnSuccessListener {
                    Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
        }
    }
}