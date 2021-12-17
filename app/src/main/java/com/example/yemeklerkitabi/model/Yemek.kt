package com.example.yemeklerkitabi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Yemek(
    @ColumnInfo(name = "isim")
    @SerializedName("isim")
    val yemekIsim: String?,
    @ColumnInfo(name = "sure")
    @SerializedName("sure")
    val yemekSure: String?,
    @ColumnInfo(name = "kalori")
    @SerializedName("kalori")
    val yemekKalori: String?,
    @ColumnInfo(name = "malzemeler")
    @SerializedName("malzemeler")
    val yemekMalzemeler: String?,
    @ColumnInfo(name = "yapilis")
    @SerializedName("yapilis")
    val yemekYapilis: String?,
    @ColumnInfo(name = "gorsel")
    @SerializedName("gorsel")
    val yemekGorsel: String?
    ) {

    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}