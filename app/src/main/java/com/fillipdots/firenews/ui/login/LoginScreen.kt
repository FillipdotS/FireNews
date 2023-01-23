package com.fillipdots.firenews.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fillipdots.firenews.ui.Route
import com.fillipdots.firenews.ui.login.LoginForm
import com.fillipdots.firenews.ui.login.LoginViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    val loginUiState by loginViewModel.uiState

    LoginForm(
        email = loginUiState.email,
        emailOnChange = { email -> loginViewModel.onEmailChange(email) },
        password = loginUiState.password,
        passwordOnChange = { password -> loginViewModel.onPasswordChange(password) },
        isLoading = loginUiState.inProgress,
        onSubmit = { loginViewModel.onLoginClick { navController.navigate(Route.Account.routeString) }},
        error = loginUiState.error,
    )
}