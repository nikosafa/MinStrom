package com.example.minstrom.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.minstrom.navigation.AppNavigation
import com.example.minstrom.ui.theme.MinStromTheme
import com.example.minstrom.utils.fetchFirebaseToken
import com.example.minstrom.ui.viewModel.NotificationViewModel
import com.google.firebase.FirebaseApp  // ✅ Add Firebase imports
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase BEFORE using it
        FirebaseApp.initializeApp(this)

        val viewModel: NotificationViewModel by viewModels()

        // Call token fetch here, BEFORE Composables run
        fetchFirebaseToken(viewModel)

        setContent {
            MinStromTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        AppNavigation(navController, viewModel)
                    }
                }
            }
        }
    }
}
