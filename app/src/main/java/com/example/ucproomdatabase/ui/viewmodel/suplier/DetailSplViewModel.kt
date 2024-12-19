package com.example.ucproomdatabase.ui.viewmodel.suplier

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucproomdatabase.data.entity.Suplier
import com.example.ucproomdatabase.repository.RepositorySpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class DetailSupViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositorySup: RepositorySpl,
) : ViewModel() {
    private val _suplierId: Int = checkNotNull(savedStateHandle[DestinasiDetail.idSuplier])

    val detailSupUiState: StateFlow<DetailSupUiState> = repositorySup.getSuplier(_suplierId)
        .filterNotNull()
        .map {
            DetailSupUiState(
                detailSupUiEvent = it.toDetailUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailSupUiState(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailSupUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailSupUiState(
                isLoading = true
            ),
        )
}


data class DetailSupUiState(
    val detailSupUiEvent: SuplierEvent = SuplierEvent(),
    val isLoading: Boolean = false,
    val isError : Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty : Boolean
        get() = detailSupUiEvent == SuplierEvent()

    val isUiEventNotEmpty : Boolean
        get() = detailSupUiEvent != SuplierEvent()
}

fun Suplier.toDetailUiEvent () : SuplierEvent{
    return SuplierEvent(
        idsuplier = idsuplier,
        namasuplier = namasuplier,
        kontak = kontak,
        alamat = alamat
    )
}