package di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun platformModule(context: Context) = module {
    single {
        dataStore(context)
    }
}