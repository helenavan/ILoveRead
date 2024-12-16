package com.toulousehvl.iloveread.presentation.screens.register

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.AuthResult
import com.toulousehvl.iloveread.data.APIResult
import com.toulousehvl.iloveread.presentation.navigation.NavigationGraph
import com.toulousehvl.iloveread.presentation.screens.component.CircularProgressCustom
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navHostController: NavHostController,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val hostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = hostState) },
        topBar = {
            TopAppBar(
                title = { Text(text = "Register") },
                navigationIcon = {
                    IconButton(
                        onClick = { navHostController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back button"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Content(
            paddingValues = paddingValues,
            registerFlowState = registerViewModel.registerFlow,
            onNavigateToLogin = { navHostController.popBackStack() },
            onRegister = { email, password -> registerViewModel.register(email, password) },
            registerSuccess = { navHostController.navigate(NavigationGraph.HomeScreen.route) },
            registerError = { scope.launch { hostState.showSnackbar("Oops! something went wrong, check your connection and try again") } }
        )
    }
}

@Composable
fun Content(
    paddingValues: PaddingValues,
    registerFlowState: MutableSharedFlow<APIResult<AuthResult>>,
    onRegister: (String, String) -> Unit,
    onNavigateToLogin: () -> Unit,
    registerSuccess: () -> Unit,
    registerError: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        val emailText = remember {
            mutableStateOf("")
        }
        val passwordText = remember {
            mutableStateOf("")
        }

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 20.dp, end = 20.dp, top = 5.dp),
            value = emailText.value,
            onValueChange = { text -> emailText.value = text },
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = { Icon(Icons.Filled.Email, "email") },
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(20.dp),
            value = passwordText.value,
            onValueChange = { text -> passwordText.value = text },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            leadingIcon = { Icon(Icons.Filled.Lock, "password") },
        )
        Button(
            onClick = { onRegister(emailText.value, passwordText.value) },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp),
            content = { Text(text = "Register") }
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = buildAnnotatedString {
                append("Already have an account?")
                withStyle(style = SpanStyle(MaterialTheme.colorScheme.primary)) { append(" Login") }
            },
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .align(alignment = Alignment.CenterHorizontally)
                .padding(20.dp)
                .clickable { onNavigateToLogin() }
        )
    }
    RegisterState(
        registerFlowState = registerFlowState,
        onSuccess = { registerSuccess() },
        onError = { registerError() }
    )
}

@Composable
fun RegisterState(
    registerFlowState: MutableSharedFlow<APIResult<AuthResult>>,
    onSuccess: () -> Unit,
    onError: () -> Unit
) {
    val isLoading = remember { mutableStateOf(false) }
    if (isLoading.value) CircularProgressCustom()
    LaunchedEffect(Unit) {
        registerFlowState.collect {
            when (it) {
                is APIResult.Loading -> {
                    Log.i("Register state -> ", "Loading")
                    isLoading.value = true
                }

                is APIResult.Error -> {
                    Log.e("Register state -> ", it.exception)
                    isLoading.value = false
                    onError()
                }

                is APIResult.Success -> {
                    Log.i("Register state -> ", "Success")
                    isLoading.value = false
                    onSuccess()
                }
            }
        }
    }
}
