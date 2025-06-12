package com.example.nathan.ui.screen.home.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nathan.ui.screen.home.AppCategory
import com.example.nathan.ui.screen.home.AppIconData


@Composable
fun CategoriesTabContent(
    categories: List<AppIconData>,
    onCategoryClick: (AppCategory) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4), // Ubah menjadi 4 kolom
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            CategoryItem(
                iconData = category,
                onClick = {
                    val appCategory = when(category.name) {
                        "Aksi" -> AppCategory.ACTION
                        "Arcade" -> AppCategory.ARCADE
                        "Balapan" -> AppCategory.BALPAN
                        "Edukasi" -> AppCategory.EDUCATION
                        "Kartu" -> AppCategory.KARTU
                        "Kasino" -> AppCategory.CASINO
                        "Kasual" -> AppCategory.CASUAL
                        else -> AppCategory.GAME
                    }
                    onCategoryClick(appCategory)
                }
            )
        }
    }
}

@Composable
fun CategoryItem(
    iconData: AppIconData,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(56.dp) // Sesuaikan ukuran
                .background(iconData.backgroundColor.copy(alpha = 0.1f), shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.foundation.Image(
                painter = androidx.compose.ui.res.painterResource(id = iconData.icon), // Gunakan properti icon baru
                contentDescription = iconData.name,
                modifier = Modifier.size(32.dp) // Sesuaikan ukuran ikon
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = iconData.name,
            fontSize = 12.sp, // Kecilkan ukuran font
            fontWeight = FontWeight.Medium,
            color = Color.Black // Ubah warna teks menjadi hitam
        )
    }
}