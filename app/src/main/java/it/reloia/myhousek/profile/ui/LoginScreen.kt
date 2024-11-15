package it.reloia.myhousek.profile.ui

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.reloia.myhousek.MainActivity
import it.reloia.myhousek.R


@Composable
fun LoginScreen(modifier: Modifier = Modifier, profileViewModel: ProfileViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Text(
            "Login",
            fontSize = 44.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
        )
        Text(
            text = "to access the app",
            fontSize = 26.sp,
        )
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by rememberSaveable { mutableStateOf(false) }

        Spacer(modifier = Modifier.height(90.dp))

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(stringResource(R.string.username)) },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                val description =
                    if (passwordVisible) stringResource(R.string.hide_password)
                    else stringResource(R.string.show_password)

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description)
                }
            }
        )

        Spacer(modifier = Modifier.height(40.dp))

        val ctx: Context = LocalContext.current

        Button(onClick = {
            if (username.isEmpty() || password.isEmpty()) {
                return@Button
            }
            profileViewModel.login(username, password)
        }, modifier = Modifier.width(200.dp)) {
            Text(stringResource(R.string.login))
        }

        val user = profileViewModel.user.collectAsState().value
        LaunchedEffect(user) {
            user?.let {
                ctx.getSharedPreferences("it.reloia.myhousek.PREFERENCE_FILE_KEY", Context.MODE_PRIVATE)
                    .edit()
                    .putString("token", it.accessToken)
                    .putBoolean("loggedIn", true)
                    .apply()

                val intent = Intent(ctx, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                ctx.startActivity(intent)
            }
        }

        val errorMessage by profileViewModel.errorMessage.collectAsState()
        LaunchedEffect(errorMessage) {
            errorMessage?.let {
//                TODO: remove this and make it a snackbar
                if (errorMessage == "Login failed: Not Found") {
                    profileViewModel.register(username, password)
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}