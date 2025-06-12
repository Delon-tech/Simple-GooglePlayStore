package com.example.nathan.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.lifecycle.ViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModelProvider


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailScreen(
    navController: NavController,
    appName: String,
    viewModel: AppDetailViewModel = viewModel(factory = AppDetailViewModel.Factory)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val app = state.app

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: Implement share */ }) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share"
                        )
                    }
                    IconButton(onClick = { /* TODO: Implement more options */ }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            app?.let {
                AppHeader(app = it)
                Divider(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                    color = Color.LightGray.copy(alpha = 0.5f)
                )
                AppRatingSection(
                    rating = state.rating,
                    downloadCount = state.downloadCount,
                    size = state.size,
                    contentRating = state.contentRating
                )
                Spacer(modifier = Modifier.height(16.dp))
                InstallButton(isInstalled = state.isInstalled)
                Spacer(modifier = Modifier.height(16.dp))
                ScreenshotsSection(screenshots = state.screenshots)
                Spacer(modifier = Modifier.height(16.dp))
                DescriptionSection(app = it)
            } ?: run {
                // Tampilkan loading atau error jika app null
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

// Sisa kode komponen lainnya tetap sama seperti sebelumnya

@Composable
fun AppHeader(app: AppSquareData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = app.iconRes),
            contentDescription = app.name,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = app.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = app.developer,
                fontSize = 14.sp,
                color = Color.Gray
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Button(
                    onClick = { /* Handle click */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFEEEEEE),
                        contentColor = Color.Gray
                    ),
                    modifier = Modifier.height(24.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = app.category,
                        fontSize = 12.sp
                    )
                }

                if (app.category.contains("Game", ignoreCase = true)) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { /* Handle click */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFEEEEEE),
                            contentColor = Color.Gray
                        ),
                        modifier = Modifier.height(24.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Berisi iklan",
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { /* Handle click */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFEEEEEE),
                            contentColor = Color.Gray
                        ),
                        modifier = Modifier.height(24.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Pembelian dalam apl",
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AppRatingSection(
    rating: Float,
    downloadCount: String,
    size: String,
    contentRating: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = rating.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(5) { index ->
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = if (index < rating.toInt()) Color(0xFFFFA000) else Color.LightGray
                    )
                }
            }
            Text(
                text = downloadCount,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = size,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Ukuran",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = contentRating,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Konten",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun InstallButton(isInstalled: Boolean) {
    Button(
        onClick = { /* Handle install/open */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF1A73E8)
        )
    ) {
        Text(
            text = if (isInstalled) "Buka" else "Instal",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ScreenshotsSection(screenshots: List<Int>) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(screenshots) { screenshot ->
                Image(
                    painter = painterResource(id = screenshot),
                    contentDescription = "Screenshot",
                    modifier = Modifier
                        .width(250.dp)
                        .height(160.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun DescriptionSection(app: AppSquareData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Tentang Aplikasi ini",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        val description = when (app.name) {
            "Plants vs. Zombies™" -> "Habisi Zombi dalam 50 Level Petualangan! Koleksi 49 tanaman zombie-zapping. Kunjungi Zen Garden Anda yang dapat disesuaikan."
            "Mobile Legends" -> "Mobile Legends adalah game MOBA yang dirancang untuk ponsel. Kedua tim lawan berjuang untuk mencapai dan menghancurkan markas musuh sambil mempertahankan markas mereka sendiri."
            else -> "Aplikasi ${app.name} adalah aplikasi ${app.category} yang dikembangkan oleh ${app.developer}. Nikmati fitur-fitur menarik yang tersedia di aplikasi ini."
        }

        Text(
            text = description,
            fontSize = 14.sp,
            color = Color.DarkGray,
            lineHeight = 20.sp
        )
    }
}

class AppDetailViewModelFactory(private val appName: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppDetailViewModel::class.java)) {
            val savedStateHandle = SavedStateHandle().apply {
                set("appName", appName)
            }
            return AppDetailViewModel(savedStateHandle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}