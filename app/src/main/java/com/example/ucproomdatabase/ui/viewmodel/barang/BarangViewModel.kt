package com.example.ucproomdatabase.ui.viewmodel.barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ucproomdatabase.data.entity.Barang
import com.example.ucproomdatabase.repository.RepositoryBrg

class BarangViewModel(private val repositoryBrg: RepositoryBrg) : ViewModel() {
    val uiState by mutableStateOf(BrgUiState())

    fun updateUiState(barangEvent: BarangEvent){
        uiState = uiState.copy(
            barangEvent = barangEvent
        )
    }
}

data class BrgUiState(
    val barangEvent: BarangEvent = BarangEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackbarMessage: String? = null
)

data class FormErrorState(
    val idbarang: String? = null,
    val namabarang: String? = null,
    val deskripsi: String? = null,
    val harga: Double? = null,
    val stok: Int? = null,
    val namasuplier: String? = null
){
    fun isValid(): Boolean{
        return idbarang != null && namabarang != null && deskripsi != null &&
                harga != null && stok != null && namasuplier != null
    }
}

data class BarangEvent(
    val idbarang: String = "",
    val namabarang: String = "",
    val deskripsi: String = "",
    val harga: Double = 0.0,
    val stok: Int = 0,
    val namasuplier: String = ""
)

fun BarangEvent.toBarangEntity(): Barang = Barang(
    idbarang = idbarang,
    namabarang = namabarang,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok,
    namasuplier = namasuplier
)