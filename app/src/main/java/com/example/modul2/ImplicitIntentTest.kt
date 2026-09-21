package com.example.modul2

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ImplicitIntentTest(context: Context) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        // Buka Browser
        Button(onClick = {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://www.google.com")
            }
            context.startActivity(intent)
        }) {
            Text("Buka Browser")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Buka Dial Telepon
        Button(onClick = {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:08123456789")
            }
            context.startActivity(intent)
        }) {
            Text("Buka Telepon")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Kirim Email (dengan chooser)
        Button(onClick = {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:test@gmail.com")
            }
            val chooser = Intent.createChooser(intent, "Pilih aplikasi email")
            context.startActivity(chooser)
        }) {
            Text("Kirim Email")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Buka Maps
        Button(onClick = {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("geo:-7.250445,112.768845")
            }
            context.startActivity(intent)
        }) {
            Text("Buka Maps")
        }
    }
}