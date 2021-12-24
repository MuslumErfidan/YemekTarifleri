package com.example.yemeklerkitabi.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.yemeklerkitabi.model.Salata
import com.example.yemeklerkitabi.servis.SalataDatabase
import kotlinx.coroutines.launch

class SalataDetayViewModel(application: Application) : BaseViewModel(application) {

    val salataLiveData = MutableLiveData<Salata>()

    fun roomVerisiniAl(uuid3: Int){
        launch {
            val dao = SalataDatabase(getApplication()).salataDao()
            val salata = dao.getSalata(uuid3)
            salataLiveData.value = salata
        }
    }
}