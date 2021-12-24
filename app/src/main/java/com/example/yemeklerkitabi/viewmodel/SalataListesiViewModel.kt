package com.example.yemeklerkitabi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeklerkitabi.model.Salata
import com.example.yemeklerkitabi.servis.SalataAPIServis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SalataListesiViewModel : ViewModel() {
    val salatalar = MutableLiveData<List<Salata>>()
    val salataHataMesaji = MutableLiveData<Boolean>()
    val salataYukleniyor = MutableLiveData<Boolean>()

    private val salataApiServis = SalataAPIServis()
    private val disposable = CompositeDisposable()

    fun refreshData(){
        verileriInternettenAl()
    }

    private fun verileriInternettenAl(){
        salataYukleniyor.value = true

        disposable.add(
            salataApiServis.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Salata>>() {
                    override fun onSuccess(t: List<Salata>) {
                        salatalar.value = t
                        salataHataMesaji.value = false
                        salataYukleniyor.value = false
                    }

                    override fun onError(e: Throwable) {
                        salataHataMesaji.value = true
                        salataYukleniyor.value = false
                        e.printStackTrace()
                    }

                })
        )
    }
}