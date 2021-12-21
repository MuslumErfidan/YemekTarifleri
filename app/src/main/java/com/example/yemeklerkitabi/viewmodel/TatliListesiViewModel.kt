package com.example.yemeklerkitabi.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeklerkitabi.model.Tatli
import com.example.yemeklerkitabi.servis.TatliAPIServis
import com.example.yemeklerkitabi.servis.TatliDatabase
import com.example.yemeklerkitabi.util.OzelSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class TatliListesiViewModel(application: Application) : BaseViewModel(application) {
    val tatlilar = MutableLiveData<List<Tatli>>()
    val tatliHataMesaji = MutableLiveData<Boolean>()
    val tatliYukleniyor = MutableLiveData<Boolean>()
    private val guncellemeZamani = 10 * 60 * 1000 * 1000 * 1000L

    private val tatliApiServis = TatliAPIServis()
    private val disposable = CompositeDisposable()
    private val ozelSharedPreferences = OzelSharedPreferences(getApplication())

    fun refreshData(){
        val kaydedilmeZamani = ozelSharedPreferences.zamaniAl()
        if (kaydedilmeZamani != null && kaydedilmeZamani != 0L && System.nanoTime() - kaydedilmeZamani < guncellemeZamani){
            verileriSQLiteTanAl()
        } else {
            verileriInternettenAl()
        }
    }

    fun refreshFromInternet(){
        verileriInternettenAl()
    }

    private fun verileriSQLiteTanAl(){
        tatliYukleniyor.value = true

        launch {
            val tatliListesi = TatliDatabase(getApplication()).tatliDao().getAllTatli()
            tatlilariGoster(tatliListesi)
            Toast.makeText(getApplication(),"Tatlıları Room'dan Aldık", Toast.LENGTH_LONG).show()
        }
    }

    private fun verileriInternettenAl(){
        tatliYukleniyor.value = true

        disposable.add(
            tatliApiServis.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Tatli>>(){
                    override fun onSuccess(t: List<Tatli>) {
                        sqliteSakla(t)
                        Toast.makeText(getApplication(),"Tatlıları İnternet'ten Aldık",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        tatliHataMesaji.value = true
                        tatliYukleniyor.value = false
                        e.printStackTrace()
                    }

                })
        )
    }
    private fun tatlilariGoster(tatlilarListesi: List<Tatli>){
        tatlilar.value = tatlilarListesi
        tatliHataMesaji.value = false
        tatliYukleniyor.value = false
    }

    private fun sqliteSakla(tatliListesi: List<Tatli>){
        launch {
            val dao = TatliDatabase(getApplication()).tatliDao()
            dao.deleteAllTatli()
            val uuidListesi = dao.insertAll(*tatliListesi.toTypedArray())
            var i = 0
            while (i < tatliListesi.size){
                tatliListesi[i].uuid2 = uuidListesi[i].toInt()
                i = i + 1
            }
            tatlilariGoster(tatliListesi)
        }

        ozelSharedPreferences.zamaniKaydet(System.nanoTime())
    }
}