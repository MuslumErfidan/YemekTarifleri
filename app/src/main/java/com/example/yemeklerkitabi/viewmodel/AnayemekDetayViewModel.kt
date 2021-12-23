package com.example.yemeklerkitabi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeklerkitabi.model.Anayemek

class AnayemekDetayViewModel : ViewModel() {

    val anayemekLiveData = MutableLiveData<Anayemek>()

    fun roomVerisiniAl(){
        val muz = Anayemek("fkdjfdfj","4","fgfgf","fsdf","rerere","tyty")
        anayemekLiveData.value = muz
    }
}