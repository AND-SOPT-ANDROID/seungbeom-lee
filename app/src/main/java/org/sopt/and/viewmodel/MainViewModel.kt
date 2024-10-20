package org.sopt.and.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val _bottomBarVisible = MutableStateFlow(BarStatus())
    val bottomBarVisible = _bottomBarVisible.asStateFlow()

    fun setBarVisible(isVisible: Boolean) {
        _bottomBarVisible.update {
            it.copy(
                bottomBarVisible = isVisible
            )
        }
    }


}

data class BarStatus(
    val bottomBarVisible: Boolean = false
)