package com.example.simanappchallenge.ui.screens.login.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.simanappchallenge.R
import com.example.simanappchallenge.ui.screens.login.viewmodel.LoginViewModel
import com.example.simanappchallenge.ui.theme.SimanAppChallengeTheme

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigationAction: () -> Unit,
    onNavigateHome: () -> Unit
) {
    Column {
        LoginScreenContent( onNavigationAction = {
            onNavigationAction()
        }, onLoginAction = { email, pass ->
            viewModel.login(email,pass)
        })
        ShowMessage(uiState = viewModel.uiState) {
            onNavigateHome()
        }
    }
}

@Composable
fun LoginScreenContent(
    onNavigationAction: () -> Unit,
    onLoginAction: (String, String) -> Unit
){
    Column(
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {

        Text(text = "Login", style = MaterialTheme.typography.titleLarge,
            fontFamily = FontFamily(Font(R.font.heading_now_bold)),
            modifier = Modifier.padding(bottom = 20.dp, top = 50.dp)
        )

        var email by remember {
            mutableStateOf("")
        }
        TextField(value = email, onValueChange = {
            email = it
        }, Modifier.padding(bottom = 10.dp))

        var password by remember {
            mutableStateOf("")
        }

        TextField(value = password, onValueChange = {
            password = it
        },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.padding(bottom = 20.dp))

        Button(onClick = {
            onLoginAction(email,password)
        }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = ShapeDefaults.ExtraSmall,
            modifier = Modifier.width(280.dp)) {
            Text(text = "INGRESAR", style = MaterialTheme.typography.titleSmall,
                fontFamily = FontFamily(Font(R.font.heading_now_bold)))
        }

        Button(onClick = {
            onNavigationAction()
        },colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = ShapeDefaults.ExtraSmall,
            modifier = Modifier.width(280.dp)) {
            Text(text = "REGISTRARSE", style = MaterialTheme.typography.titleSmall,
                fontFamily = FontFamily(Font(R.font.heading_now_bold)))
        }
    }
}

@Composable
fun ShowMessage(
    uiState: LoginViewModel.UiState,
    onNavigateHome: () -> Unit
){
    if (uiState.isLoading){
        CircularProgressIndicator()
    }
    if (uiState.error.isNotEmpty()){
        Text(text = uiState.error)
    }
    if (uiState.loginData != null){
        Text(text = "Login Success")
        onNavigateHome()
    }
}

@Preview(apiLevel = 33)
@Composable
fun LoginScreenPreview() {
    SimanAppChallengeTheme {
        LoginScreenContent(onNavigationAction = {}, onLoginAction = { _, _ -> })
    }

}