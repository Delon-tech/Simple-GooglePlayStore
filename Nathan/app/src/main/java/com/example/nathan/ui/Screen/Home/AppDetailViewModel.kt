package com.example.nathan.ui.screen.home

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.nathan.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory


data class AppDetailState(
    val app: AppSquareData? = null,
    val screenshots: List<Int> = listOf(
        R.drawable.ml, R.drawable.tiktok, R.drawable.youtube
    ),
    val rating: Float = 4.3f,
    val downloadCount: String = "5 jt ulasan",
    val size: String = "105 MB",
    val contentRating: String = "Rating 7+",
    val isInstalled: Boolean = false
)

class AppDetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(AppDetailState())
    val state: StateFlow<AppDetailState> = _state.asStateFlow()

    init {
        // Ambil app ID dari savedStateHandle
        val appName = savedStateHandle.get<String>("appName") ?: ""

        // Cari app berdasarkan nama
        loadAppDetail(appName)
    }

    private fun loadAppDetail(appName: String) {
        // Dalam kasus nyata, Anda akan mengambil data dari repository
        // Untuk contoh, kita hanya mencari dari data statis
        val app = getSampleApps().find { it.name == appName }
        _state.value = _state.value.copy(app = app)
    }

    private fun getSampleApps(): List<AppSquareData> {
        return listOf(
            AppSquareData(
                iconRes = R.drawable.ml,
                name = "Mobile Legends",
                rating = "4.9",
                category = "Game",
                size = "105 MB",
                developer = "MONTOON"
            ),
            AppSquareData(
                iconRes = R.drawable.tiktok,
                name = "Tiktok",
                rating = "4.2",
                category = "Sosial Media",
                size = "86 MB",
                developer = "ByteDance"
            ),
            AppSquareData(
                iconRes = R.drawable.google_home,
                name = "Google Home",
                rating = "4.6",
                category = "Widget",
                size = "45 MB",
                developer = "Google LLC"
            ),
            AppSquareData(
                iconRes = R.drawable.investing,
                name = "Investing.com",
                rating = "4.8",
                category = "Investasi",
                size = "27 MB",
                developer = "Investing.com"
            ),
            AppSquareData(
                iconRes = R.drawable.coc,
                name = "Clash Of Clans",
                rating = "4.5",
                category = "Game",
                size = "173 MB",
                developer = "Supercell"
            ),
            AppSquareData(
                iconRes = R.drawable.x,
                name = "X",
                rating = "4.1",
                category = "Sosial Media",
                size = "12 MB",
                developer = "X Corp."
            ),
            AppSquareData(
                iconRes = R.drawable.shopee,
                name = "Shopee Big Bang",
                rating = "4.7",
                category = "Belanja • Marketplace online",
                size = "81 MB",
                developer = "Shopee"
            ),
            AppSquareData(
                iconRes = R.drawable.youtube,
                name = "YouTube",
                rating = "4.4",
                category = "Video • Streaming",
                size = "56 MB",
                developer = "Google LLC"
            ),
            AppSquareData(
                iconRes = R.drawable.ic_facebook,
                name = "Facebook",
                rating = "4.1",
                category = "Sosial Media",
                size = "38 MB",
                developer = "Meta"
            ),
            AppSquareData(
                iconRes = R.drawable.ic_instagram,
                name = "Instagram",
                rating = "4.5",
                category = "Foto & Video",
                size = "42 MB",
                developer = "Meta"
            )
        )
    }
    companion object {
        val Factory = viewModelFactory {
            initializer {
                val savedStateHandle = SavedStateHandle()
                savedStateHandle["appName"] = "Mobile Legends"
                AppDetailViewModel(savedStateHandle)
            }
        }
    }
}