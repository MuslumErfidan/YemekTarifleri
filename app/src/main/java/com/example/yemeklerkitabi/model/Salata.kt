package com.example.yemeklerkitabi.model

import com.google.gson.annotations.SerializedName

data class Salata(
    @SerializedName("isim")
    val salataIsım: String?,
    @SerializedName("sure")
    val salataSure: String?,
    @SerializedName("kalori")
    val salataKalori: String?,
    @SerializedName("malzemeler")
    val salataMalzemeler: String?,
    @SerializedName("yapilis")
    val salataYapilis: String?,
    @SerializedName("gorsel")
    val salataGorsel: String?
    ) {
}