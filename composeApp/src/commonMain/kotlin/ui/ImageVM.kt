package ui

import androidx.compose.runtime.collectAsState
import dev.icerock.moko.mvvm.livedata.asLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.repos.ImageRepo
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ImageVM(
    private val repo: ImageRepo
): ViewModel() {
    val count = repo.clickCount
    fun updateCount(currentCount: Int) = viewModelScope.launch {
        repo.updateCount(currentCount + 1)
    }
    fun resetCount() = viewModelScope.launch {
        repo.updateCount(0)
    }
}