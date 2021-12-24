package com.example.yemeklerkitabi.servis

import com.example.yemeklerkitabi.model.Anayemek
import com.example.yemeklerkitabi.model.Salata
import com.example.yemeklerkitabi.model.Tatli
import com.example.yemeklerkitabi.model.Yemek
import io.reactivex.Single
import retrofit2.http.GET

interface YemekAPI {

    //https://raw.githubusercontent.com/MuslumErfidan/YemekTarifleriData/main/CorbalarData/corbalar.json

    @GET("MuslumErfidan/YemekTarifleriData/main/CorbalarData/corbalar.json")
    fun getYemek() : Single<List<Yemek>>
}

interface TatliAPI {

    //https://raw.githubusercontent.com/MuslumErfidan/YemekTarifleriData/main/TatlilarData/tatlilar.json

    @GET("MuslumErfidan/YemekTarifleriData/main/TatlilarData/tatlilar.json")
    fun getTatli() : Single<List<Tatli>>
}

interface SalataAPI {

    //https://raw.githubusercontent.com/MuslumErfidan/YemekTarifleriData/main/SalatalarData/salatalar.json

    @GET("MuslumErfidan/YemekTarifleriData/main/SalatalarData/salatalar.json")
    fun getSalata() : Single<List<Salata>>
}

interface AnayemekAPI {

    //https://raw.githubusercontent.com/MuslumErfidan/YemekTarifleriData/main/AnayemeklerData/anayemekler.json

    @GET("MuslumErfidan/YemekTarifleriData/main/AnayemeklerData/anayemekler.json")
    fun getAnayemek() : Single<List<Anayemek>>
}