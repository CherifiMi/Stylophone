package com.example.stylophone.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val mutableNum = MutableLiveData<Float>()
    val num: LiveData<Float> get()= mutableNum

    fun setNum(num: Float){
        mutableNum.value = num

    }
}