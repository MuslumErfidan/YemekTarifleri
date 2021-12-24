package com.example.yemeklerkitabi.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.yemeklerkitabi.model.Anayemek
import com.example.yemeklerkitabi.servis.AnayemekDatabase
import kotlinx.coroutines.launch

class AnayemekDetayViewModel(application: Application) : BaseViewModel(application) {

    val anayemekLiveData = MutableLiveData<Anayemek>()

    fun roomVerisiniAl(uuid4: Int){
        launch {
            val dao = AnayemekDatabase(getApplication()).anayemekDao()
            val anayemek = dao.getAnayemek(uuid4)
            anayemekLiveData.value = anayemek
        }
    }
}