package com.example.yemeklerkitabi.servis

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.yemeklerkitabi.model.Anayemek
import com.example.yemeklerkitabi.model.Salata
import com.example.yemeklerkitabi.model.Tatli
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

@Dao
interface TatliDAO {

    @Insert
    suspend fun insertAll(vararg tatli: Tatli) : List<Long>

    @Query("SELECT * FROM tatli")
    suspend fun getAllTatli() : List<Tatli>

    @Query("SELECT * FROM tatli WHERE uuid2 = :tatliId")
    suspend fun getTatli(tatliId: Int) : Tatli

    @Query("DELETE FROM tatli")
    suspend fun deleteAllTatli()
}

@Dao
interface SalataDAO {

    @Insert
    suspend fun insertAll(vararg salata: Salata) : List<Long>

    @Query("SELECT * FROM salata")
    suspend fun getAllSalata() : List<Salata>

    @Query("SELECT * FROM salata WHERE uuid3 = :salataId")
    suspend fun getSalata(salataId: Int) : Salata

    @Query("DELETE FROM salata")
    suspend fun deleteAllSalata()
}

@Dao
interface AnayemekDAO {

    @Insert
    suspend fun insertAll(vararg anayemek: Anayemek) : List<Long>

    @Query("SELECT * FROM anayemek")
    suspend fun getAllAnayemek() : List<Anayemek>

    @Query("SELECT * FROM anayemek WHERE uuid4 = :anayemekId")
    suspend fun getAnayemek(anayemekId: Int) : Anayemek

    @Query("DELETE FROM anayemek")
    suspend fun deleteAllAnayemek()
}