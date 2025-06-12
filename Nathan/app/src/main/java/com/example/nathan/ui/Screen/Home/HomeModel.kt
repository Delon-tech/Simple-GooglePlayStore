package com.example.nathan.ui.screen.home

import androidx.compose.ui.graphics.Color


enum class AppCategory {
    GAME,
    SOCIAL_MEDIA,
    FINANCE,
    PRODUCTIVITY,
    ENTERTAINMENT,
    ARCADE,
    EDUCATION,
    ACTION,
    BALPAN,
    KARTU,
    CASINO,
    CASUAL
}

// Simplified data class to use AppSquareData for all app representations
data class AppSquareData(
    val iconRes: Int,
    val name: String,
    val rating: String,
    val category: String = "",
    val size: String = "",
    val developer: String = "",
    val categoryType: AppCategory? = null
)

data class AppIconData(
    val iconRes: Int,
    val name: String,
    val backgroundColor: Color,
    val icon: Int
)



//data class AppItem(
    //val iconRes: Int,
    //val name: String,
    //val category: String,
    //val rating: String,
    //val size: String
//)

data class RecommendedAppData(
    val iconRes: Int,
    val name: String,
    val developer: String,
    val size: String,
    val rating: String
)