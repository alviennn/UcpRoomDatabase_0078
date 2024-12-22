package com.example.ucproomdatabase.ui.view.suplier

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucproomdatabase.ui.customwidget.TopAppBar
import com.example.ucproomdatabase.ui.viewmodel.PenyediaTokoViewModel
import com.example.ucproomdatabase.ui.viewmodel.suplier.FormSplErrorState
import com.example.ucproomdatabase.ui.viewmodel.suplier.SuplierEvent
import com.example.ucproomdatabase.ui.viewmodel.suplier.SuplierUiState
import com.example.ucproomdatabase.ui.viewmodel.suplier.SuplierViewModel
import kotlinx.coroutines.launch

@Composable
fun AddSplView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SuplierViewModel = viewModel(factory = PenyediaTokoViewModel.Factory)
) {
    val uiState = viewModel.SplUiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackbarMessage) {
        uiState.snackbarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackbarMessage()
            }
        }
    }

    Scaffold(
        Modifier, snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Suplier"
            )
            InsertBodySpl(
                uiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateUiState(updateEvent)
                },
                onClick = {
                    viewModel.saveData()
                    onNavigate()
                }
            )
        }
    }
}

@Composable
fun InsertBodySpl(
    modifier: Modifier = Modifier,
    onValueChange: (SuplierEvent) -> Unit,
    uiState: SuplierUiState,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormSuplier(
            suplierEvent = uiState.suplierEvent, // Use currentSuplierEvent
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(text = "Simpan")
        }
    }
}

@Composable
fun FormSuplier(
    suplierEvent: SuplierEvent,
    onValueChange: (SuplierEvent) -> Unit = {},
    errorState: FormSplErrorState = FormSplErrorState(),
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.idsuplier,
            onValueChange = { onValueChange(suplierEvent.copy(idsuplier = it)) },
            label = { Text(text = "Id Suplier") },
            isError = errorState.idsuplier != null,
            placeholder = { Text(text = "Masukkan Id Suplier") }
        )
        Text(
            text = errorState.idsuplier ?: "",
            color = Color.Red
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.namasuplier,
            onValueChange = { onValueChange(suplierEvent.copy(namasuplier = it)) },
            label = { Text(text = "Nama Suplier") },
            isError = errorState.namasuplier != null,
            placeholder = { Text(text = "Masukkan Nama Suplier") }
        )
        Text(
            text = errorState.namasuplier ?: "",
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.kontak,
            onValueChange = { onValueChange(suplierEvent.copy(kontak = it)) },
            label = { Text(text = "Kontak") },
            isError = errorState.kontak != null,
        )
        Text(
            text = errorState.kontak ?: "",
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.alamat,
            onValueChange = { onValueChange(suplierEvent.copy(alamat = it)) },
            label = { Text(text = "Alamat") },
            isError = errorState.alamat != null,
        )
        Text(
            text = errorState.alamat ?: "",
            color = Color.Red
        )
    }
}