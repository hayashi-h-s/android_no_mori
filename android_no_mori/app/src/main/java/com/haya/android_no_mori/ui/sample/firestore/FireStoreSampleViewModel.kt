package com.haya.android_no_mori.ui.sample.firestore

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haya.android_no_mori.ui.sample.firestore.model.SampleUser
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class FireStoreSampleViewModel : ViewModel() {
    private var repository: SampleFireStoreRepository? = null

    var sampleUserData: MutableLiveData<SampleUser> = MutableLiveData()

    init {
        repository = SampleFireStoreRepository.instance
    }


    fun addUser(user: SampleUser) = repository?.addSampleUser(user)

















    val countDownFlow = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue
        emit(startingValue)
        while (currentValue > 0) {
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }


    private fun collectFlow() {
        viewModelScope.launch {
            countDownFlow.collect { time ->
//                delay(1000L)
                Log.d("TAG", "Logs = time + $time");
            }
        }
    }

//

//        liveData(Dispatchers.ID) {
//            try {
//
//            } catch () {
//
//            }
//        }

//
//    fun getSampleUser(documentId: String): LiveData<SampleUser> {
////    fun getSampleUser(documentId: String) {
//
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

// interface 設定
interface TestListener {
    fun onSuccess()
}
