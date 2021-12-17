package com.example.yemeklerkitabi.servis

import com.example.yemeklerkitabi.model.Yemek
import io.reactivex.Single
import retrofit2.http.GET

interface YemekAPI {

    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json
    //https://raw.githubusercontent.com/MuslumErfidan/OrnekJSON/main/ornek.json

    @GET("MuslumErfidan/OrnekJSON/main/ornek.json")
    fun getYemek() : Single<List<Yemek>>
}