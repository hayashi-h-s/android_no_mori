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

//    fun getSampleUser(documentId: String): LiveData<SampleUser> {
////    fun getSampleUser(documentId: String) {
//        repository!!.getSampleUser(documentId)
//
////        sampleUser = repository!!.getSampleUser(documentId) as MutableLiveData<SampleUser>
//        return repository!!.getSampleUser(documentId)
//    }

//    fun onUndoDeleteClick(documentId: String): LiveData<SampleUser> = viewModelScope.launch {
//        return repository!!.getSampleUser(documentId)
//    }

//    fun addSampleUser(sampleUser: SampleUser) {
////        sampleUserData = sampleUser
//        val testListener = object : TestListener {
//            override fun onSuccess() {
//                Log.d("TAG", "Logs = override fun onSuccess() {");
//            }
//        }
//////
////        viewModelScope.launch {
////            repository?.addSampleUser(sampleUser, testListener)
////            sampleUserData.postValue(sampleUser)
//////            fun addFlowUser(user: SampleUser) = repository!!.addFlowUser(user)
////        }
////
//////        fun addFlowUser(user: SampleUser) = repository!!.addFlowUser(user)
//    }

//    fun addSampleUser(sampleUser: SampleUser) = repository!!.addFlowUser(sampleUser)


}