package com.example.bootcounter.viewModel

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import com.example.bootcounter.domain.data.BootTimeStorage
import com.example.bootcounter.domain.data.model.BootTimeModel

class CounterViewModel(application: Application) : BaseViewModel(application) {

    val bootTimeModelLiveData = MutableLiveData<List<BootTimeModel>>()
    private val storage = BootTimeStorage

    fun onCreate(owner: LifecycleOwner) {
        bootTimeModelLiveData.value = storage.getSavedBootTimes()
        observeBootTimes(owner)
    }

    fun updateBootTimes() {
        bootTimeModelLiveData.value = storage.getSavedBootTimes()
    }

    private fun observeBootTimes(owner: LifecycleOwner) {
        storage.bootTimes.observe(owner) {
            bootTimeModelLiveData.value = it
        }
    }

}