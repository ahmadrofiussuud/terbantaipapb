package com.example.modul2

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri

@Composable
fun HalamanUtama() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Memanggil komponen ProfilMahasiswa
        ProfilMahasiswa()

        Spacer(modifier = Modifier.height(24.dp))

        // Tombol Intent WhatsApp
        Button(
            onClick = {
                val url = "https://wa.me/6281234567890" // Ganti dengan nomor tujuan bebas
                val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                context.startActivity(intent)
            },
        ) {
            Text("Hubungi via WhatsApp")
        }
    }
}