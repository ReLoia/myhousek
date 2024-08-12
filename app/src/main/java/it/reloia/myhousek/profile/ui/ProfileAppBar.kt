package it.reloia.myhousek.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import it.reloia.myhousek.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileAppBar(userViewModel: ProfileViewModel, navController: NavController) {
    val user by userViewModel.user.collectAsState()

    TopAppBar(
        title = {
            Text(stringResource(R.string.home))
        },
        actions = {
            IconButton(onClick = {
                /*todo*/
                /*navController.navigate("settings")*/
            }) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "Settings"
                )

            }
            IconButton(
                onClick = {
                    /*todo*/
                    /*navController.navigate("profile")*/
                }
            ) {
                Image(
                    painter = if (user?.profileImageUrl != null) {
                        /*todo*/
                        painterResource(id = R.drawable.baseline_account_circle_24)
                        /*painterResource(id = R.drawable.user_profile_image) // Replace with actual image*/
                    } else {
                        painterResource(id = R.drawable.baseline_account_circle_24) // Replace with default image
                    },
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                )
            }
        }
    )
}
