package com.example.ucproomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucproomdatabase.data.entity.Barang
import kotlinx.coroutines.flow.Flow

@Dao
interface BarangDao {
    @Insert
    suspend fun insertBarang(barang: Barang)
    @Update
    suspend fun updateBarang(barang: Barang)
    @Delete
    suspend fun deleteBarang(barang: Barang)
    @Query("SELECT * FROM barang ORDER BY namabarang ASC")
    fun getAllBarang(): Flow<List<Barang>>
    @Query("SELECT * FROM barang WHERE idbarang = :id")
    fun getBarang(id: Int): Flow<Barang>
}