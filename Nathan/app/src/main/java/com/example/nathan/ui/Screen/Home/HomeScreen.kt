package com.example.nathan.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nathan.R
import com.example.nathan.ui.screen.home.AppIconData
import com.example.nathan.ui.screen.home.AppSquareData
import com.example.nathan.ui.screen.home.HomeViewModel
import com.example.nathan.ui.screen.home.componets.ForYouTabContent
import com.example.nathan.ui.screen.home.componets.TopChartsTabContent
import com.example.nathan.ui.screen.home.componets.CategoriesTabContent
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

// Colors (consider moving to a separate colors file)
private val GreenColor = Color(0xFF4CAF50)
private val WhiteColor = Color.White
private val GrayColor = Color.Gray
private val LightGrayColor = Color(0xFFEEEEEE)


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onAppClick: (String) -> Unit = {}

) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WhiteColor)
    ) {
        TopAppBar()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            TabMenuSection(
                selectedTab = state.selectedTab,
                onTabSelected = { viewModel.updateSelectedTab(it) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            when (state.selectedTab) {
                0 -> ForYouTabContent(
                    recommendedApps = state.recommendedApps,
                    newAndUpdatedApps = state.newAndUpdatedApps,
                    onAppClick = onAppClick
                )
                1 -> TopChartsTabContent(
                    sponsoredApps = state.sponsoredApps,
                    popularApps = state.popularApps,
                    onAppClick = onAppClick
                )
                2 -> CategoriesTabContent(
                    categories = state.categoryList,
                    onCategoryClick = { category ->
                        viewModel.selectedCategory(category)
                        // Tambahkan navigasi atau filter apps berdasarkan kategori
                    }
                )
                else -> ForYouTabContent(
                    recommendedApps = state.recommendedApps,
                    newAndUpdatedApps = state.newAndUpdatedApps,
                    onAppClick = onAppClick
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Google ",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 22.sp
                    )
                )
                Text(
                    text = "Play",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 22.sp,
                        color = GreenColor
                    )
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { /* TODO */ }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = { /* TODO */ }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = WhiteColor
        )
    )
}

@Composable
fun TabMenuSection(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    val tabs = listOf("For You", "Top Charts", "Categories", "Editor's Choice", "Family", "Early Access")

    ScrollableTabRow(
        selectedTabIndex = selectedTab,
        edgePadding = 0.dp,
        containerColor = WhiteColor,
        contentColor = GreenColor,
        indicator = { tabPositions ->
            if (selectedTab < tabPositions.size) {
                Box(
                    Modifier
                        .wrapContentSize(Alignment.BottomStart)
                        .offset(x = tabPositions[selectedTab].left)
                        .width(tabPositions[selectedTab].width)
                        .padding(horizontal = 4.dp)
                        .height(3.dp)
                        .background(
                            color = GreenColor,
                            shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                        )
                )
            }
        },
        divider = { Divider(color = LightGrayColor, thickness = 1.dp) }
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                text = {
                    Text(
                        text = title,
                        fontSize = 14.sp,
                        fontWeight = if (selectedTab == index) FontWeight.Medium else FontWeight.Normal,
                        color = if (selectedTab == index) GreenColor else GrayColor
                    )
                }
            )
        }
    }
}





// ... (rest of the previous implementation's Composables remain the same)
// Note: You would copy over the other Composables like TopAppBar, TabMenuSection,
// AppSquareItem, FeaturedAppBanner, TopChartsTabContent, etc.
// from the previous implementation, making sure to update their signatures
// to work with the new ViewModel-based approach
