package com.example.ucproomdatabase.ui.customwidget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopAppBar(
    onBack: () -> Unit,
    showBackButton: Boolean = true,
    judul: String,
) {
    val blueText = Color(0xFF1E88E5) // Custom blue color

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        if (showBackButton) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = onBack,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = blueText // Set blue color for the back button text
                    )
                ) {
                    Text(
                        text = "Kembali",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = blueText // Set blue color for the back button text
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Text(
            text = judul,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = blueText,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

