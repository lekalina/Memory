package ui.common

import androidx.compose.ui.unit.Density

fun Float.dpToPx(displayMetrics: Density): Float {
    return this * displayMetrics.density
}

fun Int.dpToPx(displayMetrics: Density): Int {
    return (this * displayMetrics.density).toInt()
}

fun Float.pxToDp(displayMetrics: Density): Float {
    return this / displayMetrics.density
}

fun Int.pxToDp(displayMetrics: Density): Int {
    return (this / displayMetrics.density).toInt()
}
