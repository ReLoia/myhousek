package it.reloia.myhousek.profile.ui

import android.app.Activity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import it.reloia.myhousek.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileAppBar(
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel,
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    TopAppBar(
        title = {
            Text(stringResource(R.string.profile))
        },
        actions = {
            IconButton(onClick = {
//                    coroutineScope.launch {
//                    }
            }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_logout_24),
                    contentDescription = "Logout"
                )
            }

        },
        navigationIcon = {
            IconButton(onClick = {
                (context as? Activity)?.finish()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    )
}