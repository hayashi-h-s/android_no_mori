package com.haya.android_no_mori.ui.sample.firestore

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.haya.android_no_mori.ui.sample.firestore.model.SampleUser
import com.haya.android_no_mori.ui.sample.firestore.model.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class SampleFireStoreRepository {
//    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
//    private val collectionRef: CollectionReference = db.collection("users")

    private val mPostsCollection = FirebaseFirestore.getInstance().collection("users")

    private var sampleUser: MutableLiveData<SampleUser>? = null
//    private var state: MutableLiveData<State<SampleUser>>? = null

    fun addSampleUser(user: SampleUser) = flow<State<DocumentReference>> {

        Log.d("TAG","Logs = fun addSampleUser(user: SampleUser) = flow<State<DocumentReference>> {");

        // Emit loading stat
        emit(State.loading())

        val postRef = mPostsCollection.add(user).await() //右を追加で表示 implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.3.5'

        // Emit success state with post reference
        emit(State.success(postRef))

    }.catch {
        // If exception is thrown, emit failed state along with message.
        emit(State.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)


//    fun getSampleUser(documentId: String): LiveData<SampleUser> {
//        if (sampleUser == null) {
//            sampleUser = MutableLiveData()
//        }
//        val docRef = collectionRef.document(documentId)
//        docRef.get()
//            .addOnSuccessListener { document ->
//                if (document != null) {
//                    val sampleUserData =
//                        document.toObject(SampleUser::class.java) //Commentary: Change the retrieved data to SampleUserModel
//                    sampleUser!!.value = sampleUserData
//                } else {
//                    Log.d("TAG", "No such document")
//                }
//            }
//            .addOnFailureListener {
////                Toast.makeText(this, "データ取得失敗", Toast.LENGTH_SHORT).show()
//            }
//
//        return sampleUser as MutableLiveData<SampleUser>
//    }

//    fun addSampleUser(sampleUser: SampleUser, listener: TestListener): SampleUser? {
//        var isSuccess = false
//        db.collection("users")
//            .add(sampleUser)
//            .addOnSuccessListener {
//                Log.d("TAG", "成功 Logs = ");
////                getSampleUser(it.id)
//                isSuccess = true
//                listener.onSuccess()
//            }.addOnFailureListener {
//                Log.d("TAG", "失敗 Logs = ");
//                //                    Toast.makeText(this, "登録失敗", Toast.LENGTH_SHORT).show()
//                listener.onSuccess()
//            }
//        return if (isSuccess) sampleUser else null
//    }
//
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