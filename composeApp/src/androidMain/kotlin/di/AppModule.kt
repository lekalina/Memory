package di

import android.content.Context

fun appModule(context: Context) = listOf(
    platformModule(context),
    sharedModule()
)