package com.example.nathan.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nathan.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import androidx.compose.ui.graphics.Color

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

data class HomeScreenState(
    val selectedTab: Int = 0,
    val recommendedApps: List<AppSquareData> = emptyList(),
    val newAndUpdatedApps: List<AppSquareData> = emptyList(),
    val sponsoredApps: List<AppSquareData> = emptyList(),
    val popularApps: List<AppSquareData> = emptyList(),
    val loadingState: Result<Unit> = Result.Loading,
    val categoryList: List<AppIconData> = emptyList(),
    val selectedCategory: AppCategory? = null
)

class HomeViewModel : ViewModel() {
    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state.asStateFlow()

    init {
        loadInitialData()
        _state.update { it.copy(categoryList = getCategoryList()) }
    }
    fun reloadData() {
        _state.update { it.copy(loadingState = Result.Loading) }
        loadInitialData()
    }

    fun getAppsByCategory(category: String): List<AppSquareData> {
        return when(category) {
            "Game" -> getPopularApps().filter { it.category.contains("Game", ignoreCase = true) }
            "Social Media" -> getPopularApps().filter { it.category.contains("Sosial Media", ignoreCase = true) }
            "Aksi" -> getPopularApps().filter { it.category.contains("Aksi", ignoreCase = true) }
            "Arcade" -> getPopularApps().filter { it.category.contains("Arcade", ignoreCase = true) }
            "Balapan" -> getPopularApps().filter { it.category.contains("Balapan", ignoreCase = true) }
            "Edukasi" -> getPopularApps().filter { it.category.contains("Edukasi", ignoreCase = true) }
            "Kartu" -> getPopularApps().filter { it.category.contains("Kartu", ignoreCase = true) }
            "Kasino" -> getPopularApps().filter { it.category.contains("Kasino", ignoreCase = true) }
            "Kasual" -> getPopularApps().filter { it.category.contains("Kasual", ignoreCase = true) }
            else -> getPopularApps()
        }
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            try {
                // Simulated data loading
                _state.update { currentState ->
                    currentState.copy(
                        recommendedApps = getRecommendedApps(),
                        newAndUpdatedApps = getNewAndUpdatedApps(),
                        sponsoredApps = getSponsoredApps(),
                        popularApps = getPopularApps(),
                        loadingState = Result.Success(Unit)
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(loadingState = Result.Error(e))
                }
            }
        }
    }

    fun updateSelectedTab(tabIndex: Int) {
        _state.update { it.copy(selectedTab = tabIndex) }
    }
    fun selectedCategory(category: AppCategory) {
        _state.update { it.copy(selectedCategory = category) }
    }

    private fun getRecommendedApps(): List<AppSquareData> {
        return listOf(
            AppSquareData(
                iconRes = R.drawable.ml,
                name = "Mobile Legends",
                rating = "4.9",
                category = "Game"
            ),
            AppSquareData(
                iconRes = R.drawable.tiktok,
                name = "Tiktok",
                rating = "4.2",
                category = "Sosial Media"
            ),
            AppSquareData(
                iconRes = R.drawable.google_home,
                name = "Google Home",
                rating = "4.6",
                category = "Widget"
            )
        )
    }

    private fun getNewAndUpdatedApps(): List<AppSquareData> {
        return listOf(
            AppSquareData(
                iconRes = R.drawable.investing,
                name = "Investing.com",
                rating = "4.8",
                category = "Investasi"
            ),
            AppSquareData(
                iconRes = R.drawable.coc,
                name = "Clash Of Clans",
                rating = "4.5",
                category = "Game"
            ),
            AppSquareData(
                iconRes = R.drawable.x,
                name = "X",
                rating = "4.1",
                category = "Sosial Media"
            )
        )
    }

    private fun getSponsoredApps(): List<AppSquareData> {
        return listOf(
            AppSquareData(
                iconRes = R.drawable.shopee,
                name = "Shopee Big Bang",
                category = "Belanja • online",
                rating = "4.7",
                size = "81 MB"
            ),
            AppSquareData(
                iconRes = R.drawable.investing,
                name = "Investing",
                category = "Keuangan • Pinjaman",
                rating = "4.5",
                size = "27 MB"
            ),
            AppSquareData(
                iconRes = R.drawable.x,
                name = "X",
                rating = "4.1",
                category = "Sosial Media",
                size = "12 MB"

            ),
            AppSquareData(
                iconRes = R.drawable.google_home,
                name = "Google Home",
                rating = "4.6",
                category = "Widget",
                size = "10 MB"

            )
        )
    }

    private fun getPopularApps(): List<AppSquareData> {
        return listOf(
            AppSquareData(
                iconRes = R.drawable.youtube,
                name = "YouTube",
                category = "Video • Streaming",
                rating = "4.4"
            ),
            AppSquareData(
                iconRes = R.drawable.ic_facebook,
                name = "Facebook",
                category = "Sosial Media",
                rating = "4.1"
            ),
            AppSquareData(
                iconRes = R.drawable.ic_instagram,
                name = "Instagram",
                category = "Foto & Video",
                rating = "4.5",
            ),
            AppSquareData(
                iconRes = R.drawable.tiktok,
                name = "TikTok",
                category = "Video Pendek",
                rating = "4.5"
            ),
            AppSquareData(
                iconRes = R.drawable.coc,
                name = "Clash of Clans",
                category = "Strategi • Game",
                rating = "4.2"
            ),
            AppSquareData(
                iconRes = R.drawable.google_home,
                name = "Google Home",
                rating = "4.6",
                category = "Widget"
            )
        )
    }

    fun getCategoryList(): List<AppIconData> {
        return listOf(
            AppIconData(
                iconRes = R.drawable.gameicon,
                name = "Aksi",
                backgroundColor = Color(0xFF2196F3),
                icon = R.drawable.gameicon // Tambahkan ikon sesuai gambar
            ),
            AppIconData(
                iconRes = R.drawable.iconedukasi,
                name = "Arcade",
                backgroundColor = Color(0xFFF44336),
                icon = R.drawable.iconedukasi
            ),
            AppIconData(
                iconRes = R.drawable.iconedukasi,
                name = "Balapan",
                backgroundColor = Color(0xFF4CAF50),
                icon = R.drawable.iconedukasi
            ),
            AppIconData(
                iconRes = R.drawable.musikicon,
                name = "Edukasi",
                backgroundColor = Color(0xFFFF9800),
                icon = R.drawable.musikicon
            ),
            AppIconData(
                iconRes = R.drawable.iconedukasi,
                name = "Kartu",
                backgroundColor = Color(0xFF9C27B0),
                icon = R.drawable.iconedukasi
            ),
            AppIconData(
                iconRes = R.drawable.musikicon,
                name = "Kasino",
                backgroundColor = Color(0xFF673AB7),
                icon = R.drawable.musikicon
            ),
            AppIconData(
                iconRes = R.drawable.iconedukasi,
                name = "Kasual",
                backgroundColor = Color(0xFF3F51B5),
                icon = R.drawable.iconedukasi
            )
        )
    }
}