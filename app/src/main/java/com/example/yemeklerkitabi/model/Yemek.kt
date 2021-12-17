package com.example.yemeklerkitabi.model

import com.google.gson.annotations.SerializedName

data class Yemek(
    @SerializedName("isim")
    val yemekIsim: String?,
    @SerializedName("sure")
    val yemekSure: String?,
    @SerializedName("kalori")
    val yemekKalori: String?,
    @SerializedName("malzemeler")
    val yemekMalzemeler: String?,
    @SerializedName("yapilis")
    val yemekYapilis: String?,
    @SerializedName("gorsel")
    val yemekGorsel: String?
    ) {
}