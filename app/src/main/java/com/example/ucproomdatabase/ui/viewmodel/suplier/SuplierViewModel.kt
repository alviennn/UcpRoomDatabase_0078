package com.example.ucproomdatabase.ui.viewmodel.suplier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucproomdatabase.data.entity.Suplier
import com.example.ucproomdatabase.repository.RepositorySpl
import kotlinx.coroutines.launch

class SuplierViewModel(private val repositorySup: RepositorySpl) : ViewModel() {
    var uiState by mutableStateOf(SuplierUiState())

    fun updateUiState(suplierEvent: SuplierEvent){
        uiState = uiState.copy(
            suplierEvent = suplierEvent
        )
    }

    private fun validateFields(): Boolean{
        val event = uiState.suplierEvent
        val errorState = FormErrorState(
            idsuplier = if (event.idsuplier.isNotEmpty()) "Id suplier tidak boleh kosong" else null,
            namasuplier = if (event.namasuplier.isNotEmpty()) "Nama suplier tidak boleh kosong" else null,
            kontak = if (event.kontak.isNotEmpty()) "Kontak suplier tidak boleh kosong" else null,
            alamat = if (event.alamat.isNotEmpty()) "Alamat suplier tidak boleh kosong" else null
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }
    fun saveData(){
        val currentEvent = uiState.suplierEvent

        if (validateFields()){
            viewModelScope.launch {
                try {
                    repositorySup.insertSuplier(currentEvent.toSuplierEntity())
                    uiState = uiState.copy(
                        snackbarMessage = "Data Berhasil Disimpan",
                        suplierEvent = SuplierEvent(),
                        isEntryValid = FormErrorState()
                    )
                }catch (e: Exception){
                    uiState = uiState.copy(
                        snackbarMessage = "Data Gagal Disimpan"
                    )
                }
            }
        }else {
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

data class SuplierUiState(
    val suplierEvent: SuplierEvent = SuplierEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackbarMessage: String? = null
)

data class FormErrorState(
    val idsuplier: String? = null,
    val namasuplier: String? = null,
    val kontak: String? = null,
    val alamat: String? = null
){
    fun isValid(): Boolean{
        return idsuplier != null && namasuplier != null && kontak != null && alamat != null
    }
}

data class SuplierEvent(
    val idsuplier: String = "",
    val namasuplier: String = "",
    val kontak: String = "",
    val alamat: String = ""
)

fun SuplierEvent.toSuplierEntity(): Suplier = Suplier(
    idsuplier = idsuplier,
    namasuplier = namasuplier,
    kontak = kontak,
    alamat = alamat
)