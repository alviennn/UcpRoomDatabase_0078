package com.example.ucproomdatabase.repository

import com.example.ucproomdatabase.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

interface RepositorySpl {
    suspend fun insertSuplier(suplier: Suplier)
    fun getAllSuplier(): Flow<List<Suplier>>
    fun getSuplier(id: Int): Flow<Suplier>
}