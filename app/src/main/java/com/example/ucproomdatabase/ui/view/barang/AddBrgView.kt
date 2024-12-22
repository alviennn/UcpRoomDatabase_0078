package com.example.ucproomdatabase.ui.view.barang

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucproomdatabase.data.NamaSpl
import com.example.ucproomdatabase.ui.customwidget.DropDownSpl
import com.example.ucproomdatabase.ui.customwidget.TopAppBar
import com.example.ucproomdatabase.ui.viewmodel.PenyediaTokoViewModel
import com.example.ucproomdatabase.ui.viewmodel.barang.BarangEvent
import com.example.ucproomdatabase.ui.viewmodel.barang.BarangViewModel
import com.example.ucproomdatabase.ui.viewmodel.barang.BrgUiState
import com.example.ucproomdatabase.ui.viewmodel.barang.FormBrgErrorState
import kotlinx.coroutines.launch

@Composable
fun InsertBrgView(
    onBack: ()-> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BarangViewModel = viewModel(factory = PenyediaTokoViewModel.Factory)
) {
    val uiState = viewModel.BrgUiState
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
        Modifier, snackbarHost = {SnackbarHost(hostState = snackbarHostState)}
    ) { padding ->
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(16.dp)
        ) {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Barang"
            )
            InsertBodyBrg(
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
fun InsertBodyBrg(
    modifier: Modifier = Modifier,
    onValueChange: (BarangEvent) -> Unit,
    uiState: BrgUiState,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            BarangForm(
                barangEvent = uiState.barangEvent,
                onValueChange = onValueChange,
                errorState = uiState.isEntryValid,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Button(
            onClick = onClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = "Simpan",
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun BarangForm(
    barangEvent: BarangEvent = BarangEvent(),
    onValueChange: (BarangEvent) -> Unit = { },
    errorState: FormBrgErrorState = FormBrgErrorState(),
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.idbarang,
            onValueChange = {
                onValueChange(barangEvent.copy(idbarang = it))
            },
            label = { Text(text = "Id Barang") },
            isError = errorState.idbarang != null,
            placeholder = { Text(text = "Masukkan Id Barang") }
        )
        Text(
            text = errorState.idbarang ?: "",
            color = Color.Red
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.namabarang,
            onValueChange = {
                onValueChange(barangEvent.copy(namabarang = it))
            },
            label = { Text(text = "Nama Barang") },
            isError = errorState.namabarang != null,
            placeholder = { Text(text = "Masukkan Nama Barang") }
        )
        Text(
            text = errorState.namabarang ?: "",
            color = Color.Red
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.deskripsi,
            onValueChange = {
                onValueChange(barangEvent.copy(deskripsi = it))
            },
            label = { Text(text = "Deskripsi") },
            isError = errorState.deskripsi != null,
            placeholder = { Text(text = "Masukkan Deskripsi") }
        )
        Text(
            text = errorState.deskripsi ?: "",
            color = Color.Red
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.harga.toString(),
            onValueChange = {
                onValueChange(barangEvent.copy(harga = it.toInt()))
            },
            label = { Text(text = "Harga") },
            isError = errorState.harga != null,
            placeholder = { Text(text = "Masukkan Harga") }
        )
        Text(
            text = errorState.harga ?: "",
            color = Color.Red
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.stok.toString(),
            onValueChange = {
                onValueChange(barangEvent.copy(stok = it.toInt()))
            },
            label = { Text(text = "Stok") },
            isError = errorState.stok != null,
            placeholder = { Text(text = "Masukkan Stok") }
        )
        Text(
            text = errorState.stok ?: "",
            color = Color.Red
        )
        Spacer(modifier = Modifier.padding(8.dp))
        DropDownSpl(
            selectedValue = barangEvent.namasuplier,
            options = NamaSpl.options(),
            label = "Nama Suplier",
            onValueChangedEvent = { selectedSupplier ->
                onValueChange(barangEvent.copy(namasuplier = selectedSupplier))
            }
        )
        if (errorState.namasuplier != null) {
            Text(
                text = errorState.namasuplier,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp, top = 2.dp)
            )
        }
    }
}