package com.example.yemeklerkitabi.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.yemeklerkitabi.model.Anayemek
import com.example.yemeklerkitabi.servis.AnayemekAPIServis
import com.example.yemeklerkitabi.servis.AnayemekDatabase
import com.example.yemeklerkitabi.util.OzelSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class AnayemekListesiViewModel(application: Application) : BaseViewModel(application) {
    val anayemekler = MutableLiveData<List<Anayemek>>()
    val anayemekHataMesaji = MutableLiveData<Boolean>()
    val anayemekYukleniyor = MutableLiveData<Boolean>()
    private val guncellemeZamani = 10 * 60 * 1000 * 1000 * 1000L

    private val anayemekApiServis = AnayemekAPIServis()
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
        anayemekYukleniyor.value = true

        launch {
            val anayemekListesi = AnayemekDatabase(getApplication()).anayemekDao().getAllAnayemek()
            anayemekleriGoster(anayemekListesi)
            Toast.makeText(getApplication(),"Ana Yemekleri Room'dan Aldık", Toast.LENGTH_LONG).show()
        }
    }

    private fun verileriInternettenAl(){
        anayemekYukleniyor.value = true

        disposable.add(
            anayemekApiServis.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Anayemek>>(){
                    override fun onSuccess(t: List<Anayemek>) {
                        sqliteSakla(t)
                        Toast.makeText(getApplication(),"Ana Yemekleri İnternet'ten Aldık", Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        anayemekHataMesaji.value = true
                        anayemekYukleniyor.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun anayemekleriGoster(anayemeklerListesi: List<Anayemek>){
        anayemekler.value = anayemeklerListesi
        anayemekHataMesaji.value = false
        anayemekYukleniyor.value = false
    }

    private fun sqliteSakla(anayemekListesi: List<Anayemek>){
        launch {
            val dao = AnayemekDatabase(getApplication()).anayemekDao()
            dao.deleteAllAnayemek()
            val uuidListesi = dao.insertAll(*anayemekListesi.toTypedArray())
            var i = 0
            while (i < anayemekListesi.size){
                anayemekListesi[i].uuid4 = uuidListesi[i].toInt()
                i = i + 1
            }
            anayemekleriGoster(anayemekListesi)
        }

        ozelSharedPreferences.zamaniKaydet(System.nanoTime())
    }
}