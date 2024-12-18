package com.example.ucproomdatabase.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucproomdatabase.data.dao.BarangDao
import com.example.ucproomdatabase.data.dao.SuplierDao
import com.example.ucproomdatabase.data.entity.Barang
import com.example.ucproomdatabase.data.entity.Suplier

@Database(entities = [Barang::class, Suplier::class], version = 1, exportSchema = false)
abstract class TokoDatabase : RoomDatabase(){
    abstract fun barangDao(): BarangDao
    abstract fun suplierDao(): SuplierDao

    companion object {
        @Volatile
        private var INSTANCE: TokoDatabase? = null

        fun getDatabase(context: Context): TokoDatabase {
            return (INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    TokoDatabase::class.java,
                    "tokoDatabase"
                )
                    .build()
                    .also { INSTANCE = it }
            })
        }
    }
}