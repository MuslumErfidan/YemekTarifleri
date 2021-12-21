package com.example.yemeklerkitabi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Tatli(
    @ColumnInfo(name = "isim")
    @SerializedName("isim")
    val tatliIsim: String?,
    @ColumnInfo(name = "sure")
    @SerializedName("sure")
    val tatliSure: String?,
    @ColumnInfo(name = "kalori")
    @SerializedName("kalori")
    val tatliKalori: String?,
    @ColumnInfo(name = "malzemeler")
    @SerializedName("malzemeler")
    val tatliMalzemeler: String?,
    @ColumnInfo(name = "yapilis")
    @SerializedName("yapilis")
    val tatliYapilis: String?,
    @ColumnInfo(name = "gorsel")
    @SerializedName("gorsel")
    val tatliGorsel: String?
    ) {

    @PrimaryKey(autoGenerate = true)
    var uuid2 : Int = 0
}