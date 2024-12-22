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
    var SplUiState by mutableStateOf(SuplierUiState())

    fun updateUiState(suplierEvent: SuplierEvent) {
        SplUiState = SplUiState.copy(
            suplierEvent = suplierEvent
        )
    }

    private fun validateFields(): Boolean {
        val event = SplUiState.suplierEvent
        val errorState = FormSplErrorState(
            idsuplier = if (event.idsuplier.isEmpty()) "Id suplier tidak boleh kosong" else null,
            namasuplier = if (event.namasuplier.isEmpty()) "Nama suplier tidak boleh kosong" else null,
            kontak = if (event.kontak.isEmpty()) "Kontak suplier tidak boleh kosong" else null,
            alamat = if (event.alamat.isEmpty()) "Alamat suplier tidak boleh kosong" else null
        )
        SplUiState = SplUiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData() {
        val currentEvent = SplUiState.suplierEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositorySup.insertSuplier(currentEvent.toSuplierEntity())
                    SplUiState = SplUiState.copy(
                        snackbarMessage = "Data Berhasil Disimpan",
                        suplierEvent = SuplierEvent(),
                        isEntryValid = FormSplErrorState()
                    )
                } catch (e: Exception) {
                    SplUiState = SplUiState.copy(
                        snackbarMessage = "Data Gagal Disimpan"
                    )
                }
            }
        } else {
            SplUiState = SplUiState.copy(
                snackbarMessage = "Input tidak valid. Periksa kembali data Anda"
            )
        }
    }
    fun resetSnackbarMessage() {
        SplUiState = SplUiState.copy(
            snackbarMessage = null
        )
    }
}

data class SuplierUiState(
    val suplierEvent: SuplierEvent = SuplierEvent(),
    val isEntryValid: FormSplErrorState = FormSplErrorState(),
    val snackbarMessage: String? = null
)

data class FormSplErrorState(
    val idsuplier: String? = null,
    val namasuplier: String? = null,
    val kontak: String? = null,
    val alamat: String? = null
){
    fun isValid(): Boolean{
        return idsuplier == null && namasuplier == null && kontak == null && alamat == null
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