package com.example.modul2

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale

@Composable
fun TicketIcon(modifier: Modifier = Modifier, tint: Color = Color.White) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(w, 0f)
            lineTo(w, h * 0.35f)
            arcTo(
                rect = Rect(w - h * 0.2f, h * 0.35f, w + h * 0.2f, h * 0.65f),
                startAngleDegrees = -90f,
                sweepAngleDegrees = -180f,
                forceMoveTo = false
            )
            lineTo(w, h)
            lineTo(0f, h)
            lineTo(0f, h * 0.65f)
            arcTo(
                rect = Rect(-h * 0.2f, h * 0.35f, h * 0.2f, h * 0.65f),
                startAngleDegrees = 90f,
                sweepAngleDegrees = -180f,
                forceMoveTo = false
            )
            close()
        }
        drawPath(path = path, color = tint)
    }
}

@Composable
fun PemesananTiketScreen() {
    var jumlahTiket by remember { mutableIntStateOf(1) }
    val hargaPerTiket = 25000
    val totalBayar = jumlahTiket * hargaPerTiket

    fun formatRupiah(number: Int): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        formatter.maximumFractionDigits = 0
        return formatter.format(number).replace("Rp", "Rp")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0066FF))
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TicketIcon(
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Pemesanan Tiket",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Pesan tiket dengan mudah!",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp
                )
            }

            // Body Container (Card Putih)
            Card(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F9FA))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Card 1: Harga Tiket
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(20.dp)
                            ) {
                                Text(
                                    text = "Harga Tiket",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.DarkGray
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = formatRupiah(hargaPerTiket),
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF0066FF)
                                )
                                Text(
                                    text = "per tiket",
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }
                        }

                        // Card 2: Jumlah Tiket
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(20.dp)
                            ) {
                                Text(
                                    text = "Jumlah Tiket",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.DarkGray
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    // Tombol Minus (-)
                                    IconButton(
                                        onClick = { if (jumlahTiket > 1) jumlahTiket-- },
                                        modifier = Modifier
                                            .size(48.dp)
                                            .background(Color(0xFF0066FF), CircleShape)
                                    ) {
                                        Text(
                                            text = "-",
                                            color = Color.White,
                                            fontSize = 28.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }

                                    // Display Angka Jumlah
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(48.dp)
                                            .padding(horizontal = 16.dp)
                                            .background(Color(0xFFF0F2F5), RoundedCornerShape(12.dp)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "$jumlahTiket",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black
                                        )
                                    }

                                    // Tombol Plus (+)
                                    IconButton(
                                        onClick = { jumlahTiket++ },
                                        modifier = Modifier
                                            .size(48.dp)
                                            .background(Color(0xFF0066FF), CircleShape)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Add,
                                            contentDescription = "Tambah Tiket",
                                            tint = Color.White
                                        )
                                    }
                                }
                            }
                        }

                        // Card 3: Total
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(20.dp)
                            ) {
                                Text(
                                    text = "Total",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.DarkGray
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = formatRupiah(totalBayar),
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF00875A)
                                )
                            }
                        }
                    }

                    // Tombol RESET
                    Button(
                        onClick = { jumlahTiket = 1 },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Reset",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "RESET",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PemesananTiketScreenPreview() {
    PemesananTiketScreen()
}