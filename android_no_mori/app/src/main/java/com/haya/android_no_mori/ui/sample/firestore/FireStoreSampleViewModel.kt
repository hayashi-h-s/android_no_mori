package com.haya.android_no_mori.ui.sample.firestore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haya.android_no_mori.ui.sample.firestore.model.SampleUser

class FireStoreSampleViewModel() : ViewModel() {
    private var repository: SampleFireStoreRepository? = null

    var sampleUserData: MutableLiveData<SampleUser> = MutableLiveData()

    init {
        repository = SampleFireStoreRepository.instance
    }

    fun addUser(user: SampleUser) = repository?.addSampleUser(user)

    fun getAllUsers() = repository?.getAllUsers()
}