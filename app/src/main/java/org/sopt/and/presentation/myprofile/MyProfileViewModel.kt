package org.sopt.and.presentation.myprofile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyProfileViewModel:ViewModel() {
    private val _profileStatus = MutableStateFlow(ProfileState())
    val profileState = _profileStatus.asStateFlow()
}
data class ProfileState(
    val hobby: String = "",
)