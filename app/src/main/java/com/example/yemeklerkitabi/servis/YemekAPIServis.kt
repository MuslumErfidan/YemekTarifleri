package com.example.yemeklerkitabi.servis

import com.example.yemeklerkitabi.model.Anayemek
import com.example.yemeklerkitabi.model.Salata
import com.example.yemeklerkitabi.model.Tatli
import com.example.yemeklerkitabi.model.Yemek
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class YemekAPIServis {

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(YemekAPI::class.java)

    fun getData() :Single<List<Yemek>> {
        return api.getYemek()
    }
}

class TatliAPIServis {

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(TatliAPI::class.java)

    fun getData() : Single<List<Tatli>> {
        return api.getTatli()
    }
}

class SalataAPIServis {
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(SalataAPI::class.java)

    fun getData() : Single<List<Salata>>{
        return api.getSalata()
    }
}

class AnayemekAPIServis {
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(AnayemekAPI::class.java)

    fun getData() : Single<List<Anayemek>>{
        return api.getAnayemek()
    }
}