package com.example.yemeklerkitabi.servis

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.yemeklerkitabi.model.Yemek

@Dao
interface YemekDAO {

    @Insert
    suspend fun insertAll(vararg yemek: Yemek) : List<Long>

    @Query("SELECT * FROM yemek")
    suspend fun getAllYemek() : List<Yemek>

    @Query("SELECT * FROM yemek WHERE uuid = :yemekId")
    suspend fun getYemek(yemekId: Int) : Yemek

    @Query("DELETE FROM yemek")
    suspend fun deleteAllYemek()
}