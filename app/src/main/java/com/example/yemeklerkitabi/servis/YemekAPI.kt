package com.example.yemeklerkitabi.servis

import com.example.yemeklerkitabi.model.Yemek
import io.reactivex.Single
import retrofit2.http.GET

interface YemekAPI {

    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun getYemek() : Single<List<Yemek>>
}