package com.example.ucproomdatabase.repository

import com.example.ucproomdatabase.data.dao.SuplierDao
import com.example.ucproomdatabase.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

class LocalRepositorySpl(
    private val suplierDao: SuplierDao
) : RepositorySpl {
    override suspend fun insertSuplier(suplier: Suplier) {
        suplierDao.insertSuplier(suplier)
    }
    override fun getAllSuplier(): Flow<List<Suplier>> =
        suplierDao.getAllSuplier()

    override fun getSuplier(id: Int): Flow<Suplier> =
        suplierDao.getSuplier(id)
}