package com.example.yemeklerkitabi.servis

import com.example.yemeklerkitabi.model.Yemek
import io.reactivex.Single
import retrofit2.http.GET

interface YemekAPI {

    //https://raw.githubusercontent.com/MuslumErfidan/YemekTarifleriData/main/CorbalarData/corbalar.json

    @GET("MuslumErfidan/YemekTarifleriData/main/CorbalarData/corbalar.json")
    fun getYemek() : Single<List<Yemek>>
}