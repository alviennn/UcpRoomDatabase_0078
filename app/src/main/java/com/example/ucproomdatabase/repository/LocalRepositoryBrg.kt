package com.example.ucproomdatabase.repository

import com.example.ucproomdatabase.data.dao.BarangDao
import com.example.ucproomdatabase.data.entity.Barang
import kotlinx.coroutines.flow.Flow

class LocalRepositoryBrg(
    private val barangDao: BarangDao
) : RepositoryBrg {
    override suspend fun insertBarang(barang: Barang) {
        barangDao.insertBarang(barang)
    }
    override suspend fun deleteBarang(barang: Barang) {
        barangDao.deleteBarang(barang)
    }
    override suspend fun updateBarang(barang: Barang) {
        barangDao.updateBarang(barang)
    }
    override fun getAllBarang(): Flow<List<Barang>> =
        barangDao.getAllBarang()

    override fun getBarang(id: String): Flow<Barang> =
        barangDao.getBarang(id)

}