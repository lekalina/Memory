package ui.navigation

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class NavigationVM: ViewModel() {

    private val _currentScreen = MutableStateFlow<NavRoute>(NavRoute.Memory)
    val currentScreen = _currentScreen
    private val routeBackStack = mutableListOf<NavRoute>(NavRoute.Memory)
    val bottomNavItems = listOf(NavRoute.Memory, NavRoute.Settings)

    fun navigate(route: NavRoute) {
        if (currentScreen.value != route) {
            currentScreen.update { route }
            updateBackStack(route)
        }
    }

    fun back() {
        routeBackStack.remove(currentScreen.value)
        currentScreen.update { routeBackStack.last() }
    }

    private fun updateBackStack(route: NavRoute) {
        routeBackStack.remove(route)
        routeBackStack.add(route)
    }
}