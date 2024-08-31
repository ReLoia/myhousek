package it.reloia.myhousek.profile.ui

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.LocalContext
import it.reloia.myhousek.profile.data.remote.ProfileApiService
import it.reloia.myhousek.profile.data.remote.RemoteProfileRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    val profileViewModel: ProfileViewModel = ProfileViewModel(
        RemoteProfileRepositoryImpl(
            Retrofit.Builder()
                .baseUrl("https://myhousek-api.onrender.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProfileApiService::class.java),
            LocalContext.current as Application
        ),
        LocalContext.current as Application
    )



    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Text("Login",
            fontSize = 44.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
        )
        Text(text = "to access the app",
            fontSize = 26.sp,
        )
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Spacer(modifier = Modifier.height(90.dp))

        TextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
        Spacer(modifier = Modifier.height(10.dp))
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password") })

        Spacer(modifier = Modifier.height(40.dp))

        Button(onClick = {
            val result = profileViewModel.login(username, password)
        }, modifier = Modifier.width(200.dp)) {
            Text("Login")
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}