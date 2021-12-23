package com.example.yemeklerkitabi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeklerkitabi.model.Salata

class SalataListesiViewModel : ViewModel() {
    val salatalar = MutableLiveData<List<Salata>>()
    val salataHataMesaji = MutableLiveData<Boolean>()
    val salataYukleniyor = MutableLiveData<Boolean>()

    fun refreshData(){
        val muz = Salata("fkdjfdfj","4","fgfgf","fsdf","rerere","tyty")
        val elma = Salata("fkdjfdfj","4","fgfgf","fsdf","rerere","tyty")

        val salataListesi = arrayListOf<Salata>(muz,elma)

        salatalar.value = salataListesi
        salataHataMesaji.value = false
        salataYukleniyor.value = false
    }
}