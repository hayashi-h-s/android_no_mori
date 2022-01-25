package com.haya.android_no_mori.ui.sample.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.haya.android_no_mori.ui.sample.firestore.model.SampleUser
import com.haya.android_no_mori.ui.sample.firestore.model.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class SampleFireStoreRepository {
    private val userCollection = FirebaseFirestore.getInstance().collection("users")

    fun addSampleUser(user: SampleUser) = flow {
        emit(State.loading())
        val userRef = userCollection.add(user).await() //右を追加で表示 implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.3.5'

        val addUser = userRef.get().await().toObject(SampleUser::class.java)

        print(" 【ログ】 ${javaClass.name} = addUser = $addUser")

        emit(State.success(addUser))
    }.catch {
        // If exception is thrown, emit failed state along with message.
        emit(State.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    fun getAllUsers() = flow<State<List<SampleUser>>> {
        emit(State.loading())
        val snapshot = userCollection.get().await()
        val users = snapshot.toObjects(SampleUser::class.java)
        emit(State.success(users))
    }.catch {
        // If exception is thrown, emit failed state along with message.
        emit(State.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)
     

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