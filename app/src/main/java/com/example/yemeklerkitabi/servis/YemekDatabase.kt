package com.example.yemeklerkitabi.servis

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.yemeklerkitabi.model.Anayemek
import com.example.yemeklerkitabi.model.Salata
import com.example.yemeklerkitabi.model.Tatli
import com.example.yemeklerkitabi.model.Yemek

@Database(entities = arrayOf(Yemek::class), version = 1)
abstract class YemekDatabase : RoomDatabase() {

    abstract fun yemekDao() : YemekDAO

    companion object{

        @Volatile private var instance : YemekDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: databaseOlustur(context).also {
                instance = it
            }
        }

        private fun databaseOlustur(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            YemekDatabase::class.java,"yemekdatabase").build()
    }
}

@Database(entities = arrayOf(Tatli::class), version = 1)
abstract class TatliDatabase : RoomDatabase(){

    abstract fun tatliDao() : TatliDAO

    companion object{

        @Volatile private var instance : TatliDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: databaseOlustur(context).also {
                instance = it
            }
        }

        private fun databaseOlustur(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            TatliDatabase::class.java,"tatlidatabase").build()
    }
}

@Database(entities = arrayOf(Salata::class), version = 1)
abstract class SalataDatabase : RoomDatabase(){

    abstract fun salataDao() : SalataDAO

    companion object{

        @Volatile private var instance : SalataDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: databaseOlustur(context).also {
                instance = it
            }
        }

        private fun databaseOlustur(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            SalataDatabase::class.java,"salatadatabase").build()
    }
}

@Database(entities = arrayOf(Anayemek::class), version = 1)
abstract class AnayemekDatabase : RoomDatabase(){

    abstract fun anayemekDao() : AnayemekDAO

    companion object{

        @Volatile private var instance : AnayemekDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: databaseOlustur(context).also {
                instance = it
            }
        }

        private fun databaseOlustur(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AnayemekDatabase::class.java,"anayemekdatabase").build()
    }
}