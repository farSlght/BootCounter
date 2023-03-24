package com.example.bootcounter.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class BaseViewModel protected constructor(
    application: Application
) : AndroidViewModel(application) {

//    protected lateinit var repository: Retrofit

    private fun initRepository(){

    }
}