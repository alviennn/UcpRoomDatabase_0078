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


}

fun Barang.toUIStateBrg() : BrgUiState = BrgUiState(
    barangEvent = this.toDetailUiEvent(),
)