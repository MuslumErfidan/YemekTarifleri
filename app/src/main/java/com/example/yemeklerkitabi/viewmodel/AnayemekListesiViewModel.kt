package com.example.yemeklerkitabi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeklerkitabi.model.Anayemek

class AnayemekListesiViewModel : ViewModel() {
    val anayemekler = MutableLiveData<List<Anayemek>>()
    val anayemekHataMesaji = MutableLiveData<Boolean>()
    val anayemekYukleniyor = MutableLiveData<Boolean>()

    fun refreshData(){
        val muz = Anayemek("fkdjfdfj","4","fgfgf","fsdf","rerere","tyty")
        val elma = Anayemek("fkdjfdfj","4","fgfgf","fsdf","rerere","tyty")

        val anayemekListesi = arrayListOf<Anayemek>(muz,elma)

        anayemekler.value = anayemekListesi
        anayemekHataMesaji.value = false
        anayemekYukleniyor.value = false
    }
}