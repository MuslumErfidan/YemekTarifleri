package com.example.yemeklerkitabi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeklerkitabi.model.Anayemek
import com.example.yemeklerkitabi.servis.AnayemekAPIServis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class AnayemekListesiViewModel : ViewModel() {
    val anayemekler = MutableLiveData<List<Anayemek>>()
    val anayemekHataMesaji = MutableLiveData<Boolean>()
    val anayemekYukleniyor = MutableLiveData<Boolean>()

    private val anayemekApiServis = AnayemekAPIServis()
    private val disposable = CompositeDisposable()

    fun refreshData(){
        verileriInternettenAl()
    }

    private fun verileriInternettenAl(){
        anayemekYukleniyor.value = true

        disposable.add(
            anayemekApiServis.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Anayemek>>(){
                    override fun onSuccess(t: List<Anayemek>) {
                        anayemekler.value = t
                        anayemekHataMesaji.value = false
                        anayemekYukleniyor.value = false
                    }

                    override fun onError(e: Throwable) {
                        anayemekHataMesaji.value = true
                        anayemekYukleniyor.value = false
                        e.printStackTrace()
                    }

                })
        )
    }
}