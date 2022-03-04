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
        emit(State.success(addUser))
    }.catch {
        emit(State.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    fun getAllUsers() = flow<State<List<SampleUser>>> {
        emit(State.loading())
        val snapshot = userCollection.get().await()
        val users = snapshot.toObjects(SampleUser::class.java)
        emit(State.success(users))
    }.catch {
        emit(State.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    companion object {
        private var newInstance: SampleFireStoreRepository? = null
        val instance: SampleFireStoreRepository?
            get() {
                if (newInstance == null) {
                    newInstance = SampleFireStoreRepository()
                }
                return newInstance
            }
    }
}