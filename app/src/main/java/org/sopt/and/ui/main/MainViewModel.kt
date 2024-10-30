package org.sopt.and.ui.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val _bottomBarVisible = MutableStateFlow(false)
    val bottomBarVisible = _bottomBarVisible.asStateFlow()

    fun setBarVisible(isVisible: Boolean) {
        _bottomBarVisible.value = isVisible
    }
}
