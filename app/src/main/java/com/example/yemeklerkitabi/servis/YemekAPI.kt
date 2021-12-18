package com.example.yemeklerkitabi.servis

import com.example.yemeklerkitabi.model.Yemek
import io.reactivex.Single
import retrofit2.http.GET

interface YemekAPI {

    //https://raw.githubusercontent.com/MuslumErfidan/OrnekJSON/main/corbalar.json

    @GET("MuslumErfidan/OrnekJSON/main/corbalar.json")
    fun getYemek() : Single<List<Yemek>>
}