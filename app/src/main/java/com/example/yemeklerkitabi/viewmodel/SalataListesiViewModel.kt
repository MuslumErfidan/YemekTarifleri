package com.example.yemeklerkitabi.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.yemeklerkitabi.model.Salata
import com.example.yemeklerkitabi.servis.SalataAPIServis
import com.example.yemeklerkitabi.servis.SalataDatabase
import com.example.yemeklerkitabi.util.OzelSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class SalataListesiViewModel(application: Application) : BaseViewModel(application) {
    val salatalar = MutableLiveData<List<Salata>>()
    val salataHataMesaji = MutableLiveData<Boolean>()
    val salataYukleniyor = MutableLiveData<Boolean>()
    private val guncellemeZamani = 10 * 60 * 1000 * 1000 * 1000L

    private val salataApiServis = SalataAPIServis()
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
        salataYukleniyor.value = true

        launch {
            val salataListesi = SalataDatabase(getApplication()).salataDao().getAllSalata()
            salatalariGoster(salataListesi)
            Toast.makeText(getApplication(),"Salataları Room'dan Aldık", Toast.LENGTH_LONG).show()
        }
    }

    private fun verileriInternettenAl(){
        salataYukleniyor.value = true

        disposable.add(
            salataApiServis.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Salata>>() {
                    override fun onSuccess(t: List<Salata>) {
                        sqliteSakla(t)
                        Toast.makeText(getApplication(),"Salataları İnternet'ten Aldık", Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        salataHataMesaji.value = true
                        salataYukleniyor.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun salatalariGoster(salatalarListesi: List<Salata>){
        salatalar.value = salatalarListesi
        salataHataMesaji.value = false
        salataYukleniyor.value = false
    }

    private fun sqliteSakla(salataListesi: List<Salata>){
        launch {
            val dao = SalataDatabase(getApplication()).salataDao()
            dao.deleteAllSalata()
            val uuidListesi = dao.insertAll(*salataListesi.toTypedArray())
            var i = 0
            while (i < salataListesi.size){
                salataListesi[i].uuid3 = uuidListesi[i].toInt()
                i = i + 1
            }
            salatalariGoster(salataListesi)
        }

        ozelSharedPreferences.zamaniKaydet(System.nanoTime())
    }
}