package com.example.bootcounter.domain.data

import androidx.lifecycle.MutableLiveData
import com.example.bootcounter.domain.data.model.BootTimeModel

object BootTimeStorage {   // TODO: implement Realm

    val bootTimes = MutableLiveData<List<BootTimeModel>>()

    fun saveNewBootTime() {
        val now = System.currentTimeMillis()
        // TODO: implement Realm query
        bootTimes.postValue(getSavedBootTimes())
    }

    fun getSavedBootTimes(): List<BootTimeModel> {
        // TODO: implement Realm query
        return emptyList()
    }
}