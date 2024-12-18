package com.example.ucproomdatabase.ui.viewmodel.barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucproomdatabase.data.entity.Barang
import com.example.ucproomdatabase.repository.RepositoryBrg
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class updateBrgViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryBrg: RepositoryBrg,
) : ViewModel() {

    var updateBrgUiState by mutableStateOf(BrgUiState())
        private set
    private val _barangId: String = checkNotNull(savedStateHandle[DestinasiUpdate.idBarang])

    init {
        viewModelScope.launch {
            updateBrgUiState = repositoryBrg.getBarang(_barangId)
                .filterNotNull()
                .first()
                .toUIStateBrg()
        }
    }

    fun updateState(barangEvent: BarangEvent){
        updateBrgUiState = updateBrgUiState.copy(
            barangEvent = barangEvent,
        )
    }

    fun validateFields(): Boolean{
        val event = updateBrgUiState.barangEvent
        val errorState = FormErrorState(
            idbarang = if (event.idbarang.isNotEmpty()) null else "Id barang tidak boleh kosong",
            namabarang = if (event.namabarang.isNotEmpty()) null else "Nama barang tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Deskripsi barang tidak boleh kosong",
            harga = if (event.harga != 0.0) null else "Harga barang tidak boleh kosong",
            stok = if (event.stok != 0) null else "Stok barang tidak boleh kosong",
            namasuplier = if (event.namasuplier.isNotEmpty()) null else "Nama suplier tidak boleh kosong"
        )
        updateBrgUiState = updateBrgUiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }
    fun updateData(){
        val currentEvent = updateBrgUiState.barangEvent

        if (validateFields()){
            viewModelScope.launch {
                try {
                    repositoryBrg.updateBarang(currentEvent.toBarangEntity())
                    updateBrgUiState = updateBrgUiState.copy(
                        snackbarMessage = "Data Berhasil Diupdate",
                        barangEvent = BarangEvent(),
                        isEntryValid = FormErrorState()
                    )
                    println("SnackBarMessageDiatur: ${updateBrgUiState.snackbarMessage}")
                } catch (e: Exception){
                    updateBrgUiState = updateBrgUiState.copy(
                        snackbarMessage = "Data Gagal Diupdate"
                    )
                }
            }
        }else {
            updateBrgUiState = updateBrgUiState.copy(
                snackbarMessage = "Data gagal Diupdate"
            )
        }
    }
    fun resetSnackbarMessage(){
        updateBrgUiState = updateBrgUiState.copy(
            snackbarMessage = null
        )
    }
}

fun Barang.toUIStateBrg() : BrgUiState = BrgUiState(
    barangEvent = this.toDetailUiEvent(),
)