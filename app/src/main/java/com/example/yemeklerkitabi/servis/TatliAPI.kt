package com.example.yemeklerkitabi.servis

import com.example.yemeklerkitabi.model.Tatli
import io.reactivex.Single
import retrofit2.http.GET

interface TatliAPI {

    //https://raw.githubusercontent.com/MuslumErfidan/YemekTarifleriData/main/TatlilarData/tatlilar.json

    @GET("MuslumErfidan/YemekTarifleriData/main/TatlilarData/tatlilar.json")
    fun getTatli() : Single<List<Tatli>>
}