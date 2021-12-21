package com.example.yemeklerkitabi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeklerkitabi.model.Tatli

class TatliDetayViewModel : ViewModel() {
    val tatliLiveData = MutableLiveData<Tatli>()

    fun roomVerisiniAl(){
        val muz = Tatli("muz","15","20","fgfddfdf","ujyjyj","www.test.com")
        tatliLiveData.value = muz
    }
}