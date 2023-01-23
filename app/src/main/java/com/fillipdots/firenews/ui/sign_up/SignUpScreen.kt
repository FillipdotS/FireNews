package com.fillipdots.firenews.ui.sign_up

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fillipdots.firenews.ui.Route

@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    navController: NavController
) {
    val signUpUiState by signUpViewModel.uiState

    SignUpForm(
        email = signUpUiState.email,
        emailOnChange = { email -> signUpViewModel.onEmailChange(email) },
        password = signUpUiState.password,
        passwordOnChange = { password -> signUpViewModel.onPasswordChange(password) },
        isLoading = signUpUiState.inProgress,
        onSubmit = { signUpViewModel.onSignUpClick { navController.navigate(Route.Account.routeString) }},
        error = signUpUiState.error,
    )
}