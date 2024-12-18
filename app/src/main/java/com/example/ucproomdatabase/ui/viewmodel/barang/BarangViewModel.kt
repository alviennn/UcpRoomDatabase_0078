package com.example.ucproomdatabase.ui.viewmodel.barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucproomdatabase.data.entity.Barang
import com.example.ucproomdatabase.repository.RepositoryBrg
import kotlinx.coroutines.launch

class BarangViewModel(private val repositoryBrg: RepositoryBrg) : ViewModel() {
    var uiState by mutableStateOf(BrgUiState())

    fun updateUiState(barangEvent: BarangEvent){
        uiState = uiState.copy(
            barangEvent = barangEvent
        )
    }

    private fun validateFields(): Boolean{
        val event = uiState.barangEvent
        val errorState = FormErrorState(
            idbarang = if (event.idbarang.isBlank()) "Id barang tidak boleh kosong" else null,
            namabarang = if (event.namabarang.isBlank()) "Nama barang tidak boleh kosong" else null,
            deskripsi = if (event.deskripsi.isBlank()) "Deskripsi barang tidak boleh kosong" else null,
            harga = if (event.harga == 0.0) "Harga barang tidak boleh kosong" else null,
            stok = if (event.stok == 0) "Stok barang tidak boleh kosong" else null,
            namasuplier = if (event.namasuplier.isBlank()) "Nama suplier tidak boleh kosong" else null
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }
    fun saveData(){
        val currentEvent = uiState.barangEvent

        if (validateFields()){
            viewModelScope.launch {
                try {
                    repositoryBrg.insertBarang(currentEvent.toBarangEntity())
                    uiState = uiState.copy(
                        snackbarMessage = "Data Berhasil Disimpan",
                        barangEvent = BarangEvent(),
                        isEntryValid = FormErrorState()
                    )
                }catch (e: Exception){
                    uiState = uiState.copy(
                        snackbarMessage = "Data Gagal Disimpan"
                    )
                }
            }
        }else{
            uiState = uiState.copy(
                snackbarMessage = "Input tidak valid. Periksa kembali data Anda"
            )
        }
    }
    fun resetSnackbarMessage(){
        uiState = uiState.copy(
            snackbarMessage = null
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
    val harga: String? = null,
    val stok: String? = null,
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