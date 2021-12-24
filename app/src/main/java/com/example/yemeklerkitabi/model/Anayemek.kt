package com.example.yemeklerkitabi.model

import com.google.gson.annotations.SerializedName

data class Anayemek(
    @SerializedName("isim")
    val anayemekIsım: String?,
    @SerializedName("sure")
    val anayemekSure: String?,
    @SerializedName("kalori")
    val anayemekKalori: String?,
    @SerializedName("malzemeler")
    val anayemekMalzemeler: String?,
    @SerializedName("yapilis")
    val anayemekYapilis: String?,
    @SerializedName("gorsel")
    val anayemekGorsel: String?
    ) {
}