package ui.navigation

sealed class NavRoute(val route: String, val title: String? = null, val icon: String? = null) {
    data object Characters: NavRoute(route = "characters", title = "Characters", icon = "ic_characters.xml")
    data object SampleImages: NavRoute(route = "sample_images", title = "Images", icon = "ic_image.xml")
    data object Home: NavRoute(route = "home", title = "Home", icon = "andy.xml")
    data object Memory: NavRoute(route = "memory", title = "Memory", icon = "ic_memory.xml")
    data object Settings: NavRoute(route = "settings", title = "Settings", icon = "ic_settings.xml")
    data object GameThemes: NavRoute(route = "game_themes")
}