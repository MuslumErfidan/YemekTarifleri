package com.example.yemeklerkitabi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Salata(
    @ColumnInfo(name = "isim")
    @SerializedName("isim")
    val salataIsım: String?,
    @ColumnInfo(name = "sure")
    @SerializedName("sure")
    val salataSure: String?,
    @ColumnInfo(name = "kalori")
    @SerializedName("kalori")
    val salataKalori: String?,
    @ColumnInfo(name = "malzemeler")
    @SerializedName("malzemeler")
    val salataMalzemeler: String?,
    @ColumnInfo(name = "yapilis")
    @SerializedName("yapilis")
    val salataYapilis: String?,
    @ColumnInfo(name = "gorsel")
    @SerializedName("gorsel")
    val salataGorsel: String?
    ) {

    @PrimaryKey(autoGenerate = true)
    var uuid3 : Int = 0
}