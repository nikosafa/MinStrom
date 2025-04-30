package com.example.minstrom.utils

import android.util.Log
import com.example.minstrom.ui.viewModel.NotificationViewModel  // ✅ Use the correct ViewModel
import com.google.firebase.messaging.FirebaseMessaging

// Function to fetch Firebase Token and add it to the NotificationViewModel
fun fetchFirebaseToken(viewModel: NotificationViewModel) {
    FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
        if (!task.isSuccessful) {
            Log.w("FCM", "Fetching FCM registration token failed", task.exception)
            return@addOnCompleteListener
        }
        val token = task.result
        viewModel.tokens.add(token)  // ✅ This now works because the correct ViewModel is used
        Log.d("FCM", "Device token: $token")
    }
}
