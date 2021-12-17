package com.example.yemeklerkitabi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeklerkitabi.model.Yemek
import com.example.yemeklerkitabi.servis.YemekAPIServis
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class YemekSecimListesiViewModel : ViewModel() {
    val yemekler = MutableLiveData<List<Yemek>>()
    val yemekHataMesaji = MutableLiveData<Boolean>()
    val yemekYukleniyor = MutableLiveData<Boolean>()

    private val yemekApiServis = YemekAPIServis()
    private val disposable = CompositeDisposable()

    fun refreshData() {
        verileriInternettenAl()
    }

    private fun verileriInternettenAl(){
        yemekYukleniyor.value = true

        disposable.add(
            yemekApiServis.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Yemek>>() {
                    override fun onSuccess(t: List<Yemek>) {
                        //Başarılı olursa
                        yemekler.value = t
                        yemekHataMesaji.value = false
                        yemekYukleniyor.value = false
                    }

                    override fun onError(e: Throwable) {
                        //Hata alırsak
                        yemekHataMesaji.value = true
                        yemekYukleniyor.value = false
                        e.printStackTrace()
                    }

                })
        )
    }
}