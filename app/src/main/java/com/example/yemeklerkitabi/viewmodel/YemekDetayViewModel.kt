package com.example.yemeklerkitabi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeklerkitabi.model.Yemek

class YemekDetayViewModel : ViewModel() {
    val yemekLiveData = MutableLiveData<Yemek>()

    fun roomVerisiniAl(){
        val corba = Yemek("Çorba","20 dakika","70 kalori","fhdhfddsdshdgjjsdjs" +
                "jfkldjfdjklfkldjfkl" +
                "kjdfhdjkhfkdkdf" +
                "fldjfkdjklfjdlfjl","gjdfkgfgkfgmgnmzçglkfgkljfgjgjljsdrıoujrıodjlkffmçfe","www.test.com")
        yemekLiveData.value = corba
    }
}