package com.example.yemeklerkitabi.servis

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.yemeklerkitabi.model.Tatli

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