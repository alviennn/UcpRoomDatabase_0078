package com.example.ucproomdatabase.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "suplier")
data class Suplier(
    @PrimaryKey
    val idsuplier: String,
    val namasuplier: String,
    val kontak: String,
    val alamat: String,
)
