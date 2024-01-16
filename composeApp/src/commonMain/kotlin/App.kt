
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import ui.navigation.BottomNavigation
import ui.navigation.NavRoute
import ui.navigation.NavigationVM
import ui.screens.CharacterScreen
import ui.screens.HomeScreen
import ui.screens.ImageScreen
import ui.screens.memory.MemoryGameScreen
import ui.screens.settings.ChangeGameThemeScreen
import ui.screens.settings.SettingsScreen
import ui.screens.simon.SimonScreen
import ui.theme.MemoryTheme

@Composable
fun App() {
    val navigationVM = getViewModel(Unit, viewModelFactory { NavigationVM() })
    MemoryTheme {
        BaseContent(navigationVM)
    }
}

@Composable
fun BaseContent(
    navVM: NavigationVM
) {
    val currentScreen by navVM.currentScreen.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        when(currentScreen) {
            NavRoute.Characters -> CharacterScreen()
            NavRoute.SampleImages -> ImageScreen()
            NavRoute.Home -> HomeScreen()
            NavRoute.Memory -> MemoryGameScreen()
            NavRoute.Settings -> SettingsScreen(nav = navVM)
            NavRoute.GameThemes -> ChangeGameThemeScreen(nav = navVM)
            NavRoute.Simon -> SimonScreen()
        }
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            BottomNavigation(navVM)
        }
    }
}
