package com.example.yemeklerkitabi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeklerkitabi.model.Yemek

class YemekSecimListesiViewModel : ViewModel() {
    val yemekler = MutableLiveData<List<Yemek>>()
    val yemekHataMesaji = MutableLiveData<Boolean>()
    val yemekYukleniyor = MutableLiveData<Boolean>()

    fun refreshData() {
        val corba = Yemek("Çorba","20 dakika","70 kalori","fhdhfddsdshdgjjsdjs" +
                "jfkldjfdjklfkldjfkl" +
                "kjdfhdjkhfkdkdf" +
                "fldjfkdjklfjdlfjl","gjdfkgfgkfgmgnmzçglkfgkljfgjgjljsdrıoujrıodjlkffmçfe","www.test.com")
        val kofte = Yemek("Köfte","20 dakika","70 kalori","fhdhfddsdshdgjjsdjs" +
                "jfkldjfdjklfkldjfkl" +
                "kjdfhdjkhfkdkdf" +
                "fldjfkdjklfjdlfjl","gjdfkgfgkfgmgnmzçglkfgkljfgjgjljsdrıoujrıodjlkffmçfe","www.test.com")

        val tavuk = Yemek("Tavuk","20 dakika","70 kalori","fhdhfddsdshdgjjsdjs" +
                "jfkldjfdjklfkldjfkl" +
                "kjdfhdjkhfkdkdf" +
                "fldjfkdjklfjdlfjl","gjdfkgfgkfgmgnmzçglkfgkljfgjgjljsdrıoujrıodjlkffmçfe","www.test.com")

        val pilav = Yemek("Pilav","20 dakika","70 kalori","fhdhfddsdshdgjjsdjs" +
                "jfkldjfdjklfkldjfkl" +
                "kjdfhdjkhfkdkdf" +
                "fldjfkdjklfjdlfjl","gjdfkgfgkfgmgnmzçglkfgkljfgjgjljsdrıoujrıodjlkffmçfe","www.test.com")

        val makarna = Yemek("makarna","20 dakika","70 kalori","fhdhfddsdshdgjjsdjs" +
                "jfkldjfdjklfkldjfkl" +
                "kjdfhdjkhfkdkdf" +
                "fldjfkdjklfjdlfjl","gjdfkgfgkfgmgnmzçglkfgkljfgjgjljsdrıoujrıodjlkffmçfe","www.test.com")

        val yemekListesi = arrayListOf<Yemek>(corba,kofte,tavuk,pilav,makarna)

        yemekler.value = yemekListesi
        yemekHataMesaji.value = false
        yemekYukleniyor.value = false
    }
}