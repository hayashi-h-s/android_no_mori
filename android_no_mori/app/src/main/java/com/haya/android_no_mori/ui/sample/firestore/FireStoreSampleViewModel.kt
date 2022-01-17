package com.haya.android_no_mori.ui.sample.firestore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.haya.android_no_mori.ui.sample.firestore.model.SampleUser

//class MainViewModel(private val repository: SampleFireStoreRepository) : ViewModel() {
//
//    fun getAllPosts() = repository.getAllPosts()
//
//    fun addPost(post: Post) = repository.add(post)
//}


class FireStoreSampleViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: SampleFireStoreRepository? = null

    var sampleUser: MutableLiveData<SampleUser> = MutableLiveData()

    init {
        repository = SampleFireStoreRepository.instance
    }

    fun getSampleUser(documentId: String): LiveData<SampleUser> {
//    fun getSampleUser(documentId: String) {

//        sampleUser = repository!!.getSampleUser(documentId) as MutableLiveData<SampleUser>

        return repository!!.getSampleUser(documentId)
    }

//    fun onUndoDeleteClick(documentId: String): LiveData<SampleUser> = viewModelScope.launch {
//        return repository!!.getSampleUser(documentId)
//    }

    fun addSampleUser(sampleUser: SampleUser) {
//        sampleUserData = sampleUser

        repository?.addSampleUser(sampleUser)
    }
}
