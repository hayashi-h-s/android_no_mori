package com.haya.android_no_mori.ui.sample.firestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haya.android_no_mori.ui.sample.firestore.model.SampleUser
import kotlinx.coroutines.launch

class FireStoreSampleViewModel : ViewModel() {
    private var repository: SampleFireStoreRepository? = null

    var sampleUser: MutableLiveData<SampleUser> = MutableLiveData()

    init {
        repository = SampleFireStoreRepository.instance
    }

    fun getSampleUser(documentId: String): LiveData<SampleUser> {
//    fun getSampleUser(documentId: String) {

        repository!!.getSampleUser(documentId)

//        sampleUser = repository!!.getSampleUser(documentId) as MutableLiveData<SampleUser>
        return repository!!.getSampleUser(documentId)
    }

//    fun onUndoDeleteClick(documentId: String): LiveData<SampleUser> = viewModelScope.launch {
//        return repository!!.getSampleUser(documentId)
//    }

    fun addSampleUser(sampleUser: SampleUser) {
//        sampleUserData = sampleUser
        viewModelScope.launch {
            repository?.addSampleUser(sampleUser)
        }
    }
}
