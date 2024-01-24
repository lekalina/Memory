package ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val darkColorScheme = darkColors(
    primary = PrimaryDark,
    onPrimary = White,
    secondary = PrimaryLight,
    primaryVariant = MemoryLightBlue,
    surface = SurfaceDark,
    onSurface = White,
    secondaryVariant = MemoryBlue,
    error = ChristmasRed,
    onError = White,
    background = BackgroundDark,
    onBackground = White,
)

private val lightColorScheme = lightColors(
    primary = PrimaryLight,
    onPrimary = White,
    secondary = PrimaryDark,
    primaryVariant = MemoryBlue,
    surface = SurfaceLight,
    onSurface = Black,
    secondaryVariant = MemoryLightBlue,
    error = ChristmasRed,
    onError = White,
    background = BackgroundLight,
    onBackground = Black,
)

@Composable
fun MemoryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }

    MaterialTheme(
        colors = colorScheme,
        content = content
    )
}
