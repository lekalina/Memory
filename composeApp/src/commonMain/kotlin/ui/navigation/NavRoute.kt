package ui.navigation

sealed class NavRoute(val route: String, val title: String? = null, val icon: String? = null) {
    data object Home: NavRoute(route = "home", title = "Home", icon = "andy.xml")
    data object Simon: NavRoute(route = "simon", title = "Simon", icon = "ic_simon.xml")
    data object Memory: NavRoute(route = "memory", title = "Memory", icon = "ic_memory.xml")
    data object Settings: NavRoute(route = "settings", title = "Settings", icon = "ic_settings.xml")
    data object GameThemes: NavRoute(route = "game_themes")
}