package com.fillipdots.firenews.ui.account

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fillipdots.firenews.data.repository.AccountRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository
): ViewModel() {
    var uiState = mutableStateOf(AccountUiState())
        private set

    init {
        refreshAccountState()
    }

    fun signOut() {
        viewModelScope.launch {
            accountRepository.signOut()
            refreshAccountState()
        }
    }

    fun anonSignIn() {
        viewModelScope.launch {
            accountRepository.signInAnonymously()
            refreshAccountState()
        }
    }

    private fun refreshAccountState() {
        val firebaseAccount = Firebase.auth.currentUser

        if (firebaseAccount != null) {
            uiState.value = uiState.value.copy(isAccountAnonymous = firebaseAccount.isAnonymous, email = firebaseAccount.email, accountUid = accountRepository.currentUserId)
        }
    }
}

data class AccountUiState(
    val email: String? = null,
    val isAccountAnonymous: Boolean = true,
    val accountUid: String = "",
    val errorMessages: List<String> = emptyList(),
)