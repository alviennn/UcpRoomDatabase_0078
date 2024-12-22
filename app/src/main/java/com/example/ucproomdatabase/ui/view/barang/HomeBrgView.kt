package com.example.ucproomdatabase.ui.view.barang

import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import com.example.ucproomdatabase.data.entity.Barang
import com.example.ucproomdatabase.ui.customwidget.TopAppBar
import com.example.ucproomdatabase.ui.viewmodel.PenyediaTokoViewModel
import com.example.ucproomdatabase.ui.viewmodel.barang.HomeBrgUiState
import com.example.ucproomdatabase.ui.viewmodel.barang.HomeBrgViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@Composable
fun HomeBrgView(
    modifier: Modifier = Modifier,
    viewModel: HomeBrgViewModel = viewModel(factory = PenyediaTokoViewModel.Factory),
    onAddBrgClick: () -> Unit = { },
    onDetailBrgClick: (String) -> Unit = { },
    onBack: () -> Unit = { },
) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                TopAppBar(
                    judul = "List Barang",
                    showBackButton = true,
                    onBack = onBack,
                )
            }
        }
    ) { innerPadding ->
        val detailBrgUiState by viewModel.HomeBrgUiState.collectAsState()

        BodyHomeBrgView(
            homeUiState = detailBrgUiState,
            onClick = {
                onDetailBrgClick(it)
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomeBrgView (
    homeUiState : HomeBrgUiState,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when {
        homeUiState.isLoading -> {
            //menampilkan indikator loading
            Box (
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        homeUiState.isError -> {
            LaunchedEffect(homeUiState.errorMessage) {
                homeUiState.errorMessage?.let{ message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }

        homeUiState.listBrg.isEmpty() -> {
            Box (
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text (
                    text = "Tidak ada data Barang. ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        else -> {
            ListBarang(
                listBrg = homeUiState.listBrg,
                onClick = {
                    onClick(it)
                    println(it)
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListBarang (
    listBrg : List<Barang>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    LazyColumn (
        modifier = modifier
    ) {
        items(
            items = listBrg,
            itemContent = { brg ->
                CardBarang(
                    brg = brg,
                    onClick = { onClick(brg.idbarang)}
                )
            }
        )
    }
}


@Composable
fun CardBarang(
    brg: Barang,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    val backgroundColor = when (brg.stok) {
        0 -> Color.Gray.copy(alpha = 0.1f)
        in 1..10 -> Color.Red.copy(alpha = 0.2f)
        else -> Color.Green.copy(alpha = 0.2f)
    }
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .padding(8.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = brg.namabarang,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Deskripsi",
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = brg.deskripsi,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Harga",
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Harga : Rp." + brg.harga.toString(),
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}