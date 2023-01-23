package com.fillipdots.firenews.ui.account

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fillipdots.firenews.ui.Route

@Composable
fun AccountScreen(
    accountViewModel: AccountViewModel = hiltViewModel(),
    navController: NavController,
) {
    val accountUiState by accountViewModel.uiState

    AccountDetail(
        isAccountAnonymous = accountUiState.isAccountAnonymous,
        email = accountUiState.email,
        accountId = accountUiState.accountUid,
        onLoginClick = { navController.navigate(Route.Login.routeString) },
        onSignUpClick = { navController.navigate(Route.SignUp.routeString) },
        onLogoutClick = { accountViewModel.signOut() }
    )
}