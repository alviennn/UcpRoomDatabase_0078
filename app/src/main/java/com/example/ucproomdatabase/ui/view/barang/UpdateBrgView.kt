package com.example.ucproomdatabase.ui.view.barang

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucproomdatabase.ui.customwidget.TopAppBar
import com.example.ucproomdatabase.ui.viewmodel.PenyediaTokoViewModel
import com.example.ucproomdatabase.ui.viewmodel.barang.UpdateBrgViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun UpdateBrgView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateBrgViewModel = viewModel(factory = PenyediaTokoViewModel.Factory)
){
    val BrgUiState = viewModel.UpdateBrgUiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(
        BrgUiState.snackbarMessage
    ) {
        println("LaunchedEffect triggered")
        BrgUiState.snackbarMessage?.let { message ->
            println("Launhing coroutine for snackbar")
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Long,
            )
            viewModel.resetSnackbarMessage()
        }
    }

    Scaffold (
        modifier = modifier,
        snackbarHost = {SnackbarHost(hostState = snackbarHostState)},
    ) { padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ){
            TopAppBar(
                judul = "Edit Barang",
                showBackButton = true,
                onBack = onBack,
            )
            InsertBodyBrg(
                uiState = BrgUiState,
                onValueChange = { updatedEvent ->
                    viewModel.updateState(updatedEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        if (viewModel.validateFields()){
                            viewModel.updateData()
                            delay(600)
                            withContext(Dispatchers.Main) {
                                onNavigate()
                            }
                        }
                    }
                }
            )
        }
    }
}