package com.example.yemeklerkitabi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Anayemek(
    @ColumnInfo(name = "isim")
    @SerializedName("isim")
    val anayemekIsım: String?,
    @ColumnInfo(name = "sure")
    @SerializedName("sure")
    val anayemekSure: String?,
    @ColumnInfo(name = "kalori")
    @SerializedName("kalori")
    val anayemekKalori: String?,
    @ColumnInfo(name = "malzemeler")
    @SerializedName("malzemeler")
    val anayemekMalzemeler: String?,
    @ColumnInfo(name = "yapilis")
    @SerializedName("yapilis")
    val anayemekYapilis: String?,
    @ColumnInfo(name = "gorsel")
    @SerializedName("gorsel")
    val anayemekGorsel: String?
    ) {

    @PrimaryKey(autoGenerate = true)
    var uuid4 : Int = 0
}