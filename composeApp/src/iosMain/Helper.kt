import data.di.appModule
import di.platformModule
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(appModule())
    }
}