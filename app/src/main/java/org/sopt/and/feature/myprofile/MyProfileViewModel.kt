package org.sopt.and.feature.myprofile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyProfileViewModel:ViewModel() {
    private val _profileStatus = MutableStateFlow(ProfileState())
    val profileState = _profileStatus.asStateFlow()

    fun setEmail(email: String) {
        if(email.isNotBlank()) {
            _profileStatus.update {
                it.copy(
                    email = email
                )
            }
        }
    }

    fun setPassword(password: String) {
        if(password.isNotBlank()) {
            _profileStatus.update {
                it.copy(
                    password = password
                )
            }
        }
    }

}
data class ProfileState(
    val email: String = "",
    val password: String = ""
)