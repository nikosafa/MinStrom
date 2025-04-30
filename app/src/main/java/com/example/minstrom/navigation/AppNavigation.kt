package com.example.minstrom.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.minstrom.ui.screens.InviteUsersEmail
import com.example.minstrom.ui.screens.InviteUsersScreen
import com.example.minstrom.ui.screens.InviteUsersSms
import com.example.minstrom.ui.screens.NotificationTestScreen
import com.example.minstrom.ui.viewModel.NotificationViewModel  // ✅ Import your custom ViewModel

@Composable
fun AppNavigation(navController: NavHostController, viewModel: NotificationViewModel) {
    NavHost(
        navController = navController,
        startDestination = "invite_users",
    ) {
        composable("invite_users") {
            InviteUsersScreen(navController)
        }
        composable("invite_sms") {
            InviteUsersSms(navController)
        }
        composable("invite_email") {
            InviteUsersEmail(navController)
        }
        composable("notification_test") {
            NotificationTestScreen(viewModel)
        }
    }
}
