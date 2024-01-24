package ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.theme.StandardToolbarHeight

@Composable
fun BottomNavigation(
    navVM: NavigationVM,
) {
    val bottomNavItems = navVM.bottomNavItems
    val currentNavRoute by navVM.currentScreen.collectAsState()
    Row(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.surface)) {
        bottomNavItems.forEach {
            BottomNavigationItem(
                modifier = Modifier.fillMaxWidth().weight(bottomNavItems.size/100f),
                icon = it.icon,
                title = it.title,
                isActive = it == currentNavRoute
            ) {
                navVM.navigate(it)
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BottomNavigationItem(
    modifier: Modifier,
    icon: String?,
    title: String?,
    isActive: Boolean,
    onClick: () -> Unit,
) {
    val color = if (isActive) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.onSurface
    Column(
        modifier = modifier.clickable { onClick() }.height(StandardToolbarHeight),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        icon?.let {
            Icon(
                painterResource(icon),
                contentDescription = null,
                tint = color
            )
        }
        title?.let {
            Text(
                text = title,
                color = color
            )
        }
    }
}