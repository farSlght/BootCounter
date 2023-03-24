package com.example.bootcounter.viewModel

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import androidx.lifecycle.viewModelScope
import com.example.bootcounter.domain.data.BootTimeStorage
import com.example.bootcounter.domain.data.model.BootTimeModel
import kotlinx.coroutines.launch

class CounterViewModel(application: Application) : BaseViewModel(application) {

    val bootTimeModelLiveData = MutableLiveData<List<BootTimeModel>>()
    private val storage = BootTimeStorage

    fun onCreate(owner: LifecycleOwner) {
        observeStorage(owner)
        initRepository()
    }

    private fun observeStorage(owner: LifecycleOwner) {
        storage.bootTimes.observe(owner) {
            bootTimeModelLiveData.value = it
        }
    }

    private fun initRepository() {
        // todo: implement
    }

}