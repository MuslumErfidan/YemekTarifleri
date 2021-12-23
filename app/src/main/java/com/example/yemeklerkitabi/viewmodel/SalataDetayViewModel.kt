package com.example.yemeklerkitabi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeklerkitabi.model.Anayemek
import com.example.yemeklerkitabi.model.Salata

class SalataDetayViewModel : ViewModel() {

    val salataLiveData = MutableLiveData<Salata>()

    fun roomVerisiniAl(){
        val muz = Salata("fkdjfdfj","4","fgfgf","fsdf","rerere","tyty")
        salataLiveData.value = muz
    }
}