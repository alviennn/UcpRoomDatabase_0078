package com.example.ucproomdatabase.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barang")
data class Barang(
    @PrimaryKey
    val idbarang: Int,
    val nama: String,
    val deskripsi: String,
    val harga: Double,
    val stok: Int,
    val suplierId: Int
)
