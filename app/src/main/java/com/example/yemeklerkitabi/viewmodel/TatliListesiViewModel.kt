package com.example.yemeklerkitabi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeklerkitabi.model.Tatli
import com.example.yemeklerkitabi.servis.TatliAPIServis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class TatliListesiViewModel : ViewModel() {
    val tatlilar = MutableLiveData<List<Tatli>>()
    val tatliHataMesaji = MutableLiveData<Boolean>()
    val tatliYukleniyor = MutableLiveData<Boolean>()

    private val tatliApiServis = TatliAPIServis()
    private val disposable = CompositeDisposable()

    fun refreshData(){
        verileriInternettenAl()
    }

    private fun verileriInternettenAl(){
        tatliYukleniyor.value = true

        disposable.add(
            tatliApiServis.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Tatli>>(){
                    override fun onSuccess(t: List<Tatli>) {
                        tatlilar.value = t
                        tatliHataMesaji.value = false
                        tatliYukleniyor.value = false
                    }

                    override fun onError(e: Throwable) {
                        tatliHataMesaji.value = true
                        tatliYukleniyor.value = false
                        e.printStackTrace()
                    }

                })
        )
    }
}