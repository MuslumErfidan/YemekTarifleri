package com.example.yemeklerkitabi.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeklerkitabi.model.Yemek
import com.example.yemeklerkitabi.servis.YemekDatabase
import kotlinx.coroutines.launch

class YemekDetayViewModel(application: Application) : BaseViewModel(application) {

    val yemekLiveData = MutableLiveData<Yemek>()

    fun roomVerisiniAl(uuid: Int){
        launch {
            val dao = YemekDatabase(getApplication()).yemekDao()
            val yemek = dao.getYemek(uuid)
            yemekLiveData.value = yemek
        }
    }
}