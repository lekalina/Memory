import androidx.compose.ui.window.ComposeUIViewController
import di.appModule
import org.koin.compose.KoinApplication

fun MainViewController() = ComposeUIViewController {
    KoinApplication(application = { modules(appModule()) }) {
        App()
    }
}
