package com.haya.android_no_mori.ui.sample.firestore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.haya.android_no_mori.ui.sample.firestore.model.SampleUser
import com.haya.android_no_mori.ui.sample.firestore.model.State

class SampleFireStoreRepository  {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val collectionRef: CollectionReference = db.collection("users")

    private val mPostsCollection = FirebaseFirestore.getInstance().collection("users")

    private var sampleUser: MutableLiveData<SampleUser>? = null
    private var state: MutableLiveData<State<SampleUser>>? = null

//    fun getAllSampleUsers() = flow<State<List<SampleUser>>> {

//        // ロード中
//        emit(State.loading())
//
//        val snapshot = mPostsCollection.get().await()
//        val posts = snapshot.toObjects(SampleUser::class.java)
//
//        // Emit success state with data
//        emit(State.success(posts))
//
//    }.catch {
//        // If exception is thrown, emit failed state along with message.
//        emit(State.failed(it.message.toString()))
//    }.flowOn(Dispatchers.IO)


    fun getSampleUser(documentId: String): LiveData<SampleUser> {
        if (sampleUser == null) {
            sampleUser = MutableLiveData()
        }
        val docRef = collectionRef.document(documentId)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val sampleUserData = document.toObject(SampleUser::class.java) //Commentary: Change the retrieved data to SampleUserModel
                    sampleUser!!.value = sampleUserData
                } else {
                    Log.d("TAG", "No such document")
                }
            }
            .addOnFailureListener {
//                Toast.makeText(this, "データ取得失敗", Toast.LENGTH_SHORT).show()
            }

        return sampleUser as MutableLiveData<SampleUser>
    }

    fun addSampleUser(sampleUser: SampleUser) {
        db.collection("users")
            .add(sampleUser)
            .addOnSuccessListener {
                Log.d("TAG", "成功 Logs = ");
                getSampleUser(it.id)
            }.addOnFailureListener {
                Log.d("TAG", "失敗 Logs = ");
                //                    Toast.makeText(this, "登録失敗", Toast.LENGTH_SHORT).show()
            }
    }

    companion object {
        private var mInstance: SampleFireStoreRepository? = null
        val instance: SampleFireStoreRepository?
            get() {
                if (mInstance == null) {
                    mInstance = SampleFireStoreRepository()
                }
                return mInstance
            }
    }
}