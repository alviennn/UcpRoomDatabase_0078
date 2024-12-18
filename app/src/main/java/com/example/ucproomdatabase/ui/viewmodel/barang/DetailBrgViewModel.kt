package com.example.ucproomdatabase.ui.viewmodel.barang

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucproomdatabase.data.entity.Barang
import com.example.ucproomdatabase.repository.RepositoryBrg
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailBrgViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryBrg: RepositoryBrg,
) : ViewModel() {
    private val barangId: Int = checkNotNull(savedStateHandle[DestinasiDetail.idBarang])

    val detailBrgUiState: StateFlow<DetailBrgUiState> = repositoryBrg.getBarang(barangId)
        .filterNotNull()
        .map{
            DetailBrgUiState(
                detailBrgUiEvent = it.toDetailUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailBrgUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                DetailBrgUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailBrgUiState(
                isLoading = true
            )
        )
    fun deleteBrg(){
        detailBrgUiState.value.detailBrgUiEvent.toBarangEntity().let {
            viewModelScope.launch {
                repositoryBrg.deleteBarang(it)
            }
        }
    }
}

data class DetailBrgUiState(
    val detailBrgUiEvent: BarangEvent = BarangEvent(),
    val isLoading: Boolean = false,
    val isError : Boolean = false,
    val errorMessage: String = ""
)

fun Barang.toDetailUiEvent () : BarangEvent{
    return BarangEvent(
        idbarang = idbarang,
        namabarang = namabarang,
        deskripsi = deskripsi,
        harga = harga,
        stok = stok,
        namasuplier = namasuplier
    )
}