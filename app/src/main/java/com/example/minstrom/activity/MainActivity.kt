package com.example.minstrom.activity


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import com.example.minstrom.ui.theme.MinStromTheme
import com.example.minstrom.navigation.AppNavigation // Import the AppNavigation
import com.example.minstrom.utils.fetchFirebaseToken // Import the Firebase token fetching function

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MinStromTheme {
                val navController = rememberNavController()
                val viewModel: ViewModel by viewModels()
                val context = LocalContext.current

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        AppNavigation(navController, viewModel)
                    }
                }

                // Fetch and print the FCM token
                fetchFirebaseToken(viewModel)

            }
        }
    }
}