package com.example.yemeklerkitabi.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeklerkitabi.model.Tatli
import com.example.yemeklerkitabi.servis.TatliDatabase
import kotlinx.coroutines.launch

class TatliDetayViewModel(application: Application) : BaseViewModel(application) {

    val tatliLiveData = MutableLiveData<Tatli>()

    fun roomVerisiniAl(uuid2: Int){
        launch {
            val dao = TatliDatabase(getApplication()).tatliDao()
            val tatli = dao.getTatli(uuid2)
            tatliLiveData.value = tatli
        }
    }
}