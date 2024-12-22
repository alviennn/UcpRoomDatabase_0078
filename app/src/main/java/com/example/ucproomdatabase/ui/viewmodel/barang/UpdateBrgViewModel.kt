package com.example.ucproomdatabase.ui.viewmodel.barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucproomdatabase.data.entity.Barang
import com.example.ucproomdatabase.repository.RepositoryBrg
import com.example.ucproomdatabase.ui.navigation.DestinasiUpdate
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateBrgViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryBrg: RepositoryBrg,
) : ViewModel() {
    var UpdateBrgUiState by mutableStateOf(BrgUiState())
        private set
    private val barangId: String = checkNotNull(savedStateHandle[DestinasiUpdate.idBarang])
    init {
        viewModelScope.launch {
            UpdateBrgUiState = repositoryBrg.getBarang(barangId)
                .filterNotNull()
                .first()
                .toUIStateBrg()
        }
    }

    fun updateState(barangEvent: BarangEvent){
        UpdateBrgUiState = UpdateBrgUiState.copy(
            barangEvent = barangEvent,
        )
    }

    fun validateFields(): Boolean{
        val event = UpdateBrgUiState.barangEvent
        val errorState = FormBrgErrorState(
            idbarang = if (event.idbarang.isNotEmpty()) null else "Id barang tidak boleh kosong",
            namabarang = if (event.namabarang.isNotEmpty()) null else "Nama barang tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Deskripsi barang tidak boleh kosong",
            harga = if (event.harga <= 0) "Harga barang tidak boleh kosong" else null,
            stok = if (event.stok <= 0) null else "Stok barang tidak boleh kosong",
            namasuplier = if (event.namasuplier.isNotEmpty()) null else "Nama suplier tidak boleh kosong"
        )
        UpdateBrgUiState = UpdateBrgUiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }
    fun updateData(){
        val currentEvent = UpdateBrgUiState.barangEvent

        if (validateFields()){
            viewModelScope.launch {
                try {
                    repositoryBrg.updateBarang(currentEvent.toBarangEntity())
                    UpdateBrgUiState = UpdateBrgUiState.copy(
                        snackbarMessage = "Data Berhasil Diupdate",
                        barangEvent = BarangEvent(),
                        isEntryValid = FormBrgErrorState()
                    )
                    println("SnackBarMessageDiatur: ${UpdateBrgUiState.snackbarMessage}")
                } catch (e: Exception){
                    UpdateBrgUiState = UpdateBrgUiState.copy(
                        snackbarMessage = "Data Gagal Diupdate"
                    )
                }
            }
        }else {
            UpdateBrgUiState = UpdateBrgUiState.copy(
                snackbarMessage = "Data gagal Diupdate"
            )
        }
    }
    fun resetSnackbarMessage(){
        UpdateBrgUiState = UpdateBrgUiState.copy(
            snackbarMessage = null
        )
    }
}

fun Barang.toUIStateBrg() : BrgUiState = BrgUiState(
    barangEvent = this.toDetailUiEvent(),
)