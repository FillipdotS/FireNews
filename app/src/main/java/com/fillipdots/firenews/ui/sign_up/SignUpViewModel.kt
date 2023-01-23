package com.fillipdots.firenews.ui.sign_up

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fillipdots.firenews.data.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountRepository: AccountRepository
): ViewModel() {
    var uiState = mutableStateOf(SignUpUiState())
        private set

    fun onEmailChange(email: String) {
        uiState.value = uiState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        uiState.value = uiState.value.copy(password = password)
    }

    fun onSignUpClick(onSuccess: () -> Unit) {
        uiState.value = uiState.value.copy(inProgress = true)

        viewModelScope.launch {
            try {
                accountRepository.linkAccount(uiState.value.email, uiState.value.password)
                uiState.value = uiState.value.copy(inProgress = false, error = null)
                onSuccess()
            } catch (ex: Exception) {
                uiState.value = uiState.value.copy(inProgress = false, error = ex.localizedMessage)
            }
        }
    }
}

data class SignUpUiState(
    val email: String = "",
    val password: String = "",
    val inProgress: Boolean = false,
    val error: String? = null,
)