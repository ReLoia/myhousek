package it.reloia.myhousek.home.ui

import android.content.Intent
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
import it.reloia.myhousek.OtherActivity
import it.reloia.myhousek.R
import it.reloia.myhousek.profile.ui.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(userViewModel: ProfileViewModel, navController: NavController) {
    val user by userViewModel.user.collectAsState()
    val context = navController.context

    TopAppBar(
        title = {
            Text(stringResource(R.string.home))
        },
        actions = {
            IconButton(onClick = {
                val intent = Intent(context, OtherActivity::class.java)
                intent.putExtra("page", "settings")
                context.startActivity(intent)
            }) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "Settings"
                )

            }
            IconButton(
                onClick = {
                    val intent = Intent(context, OtherActivity::class.java)
                    intent.putExtra("page", "profile")
                    context.startActivity(intent)
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_account_circle_24),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                )
            }
        }
    )
}
