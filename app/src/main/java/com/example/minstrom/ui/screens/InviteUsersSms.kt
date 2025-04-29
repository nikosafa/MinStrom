package com.example.minstrom.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.minstrom.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InviteUsersSms(navController: NavController) {
    var phoneNumber by remember { mutableStateOf("") }
    val isPhoneNumberValid = isValidPhoneNumber(phoneNumber)
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Indtast nummer", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Tilbage"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Menu handling */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = "Menu",
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(35.dp))

            Text(
                text = "Indtast telefonnummeret på brugeren du ønsker at tilføje til planlægningen",
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(35.dp))

            Image(
                painter = painterResource(id = R.drawable.invite),
                contentDescription = "Telefon illustration",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(35.dp))

            TextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Telefonnummer") },
                placeholder = { Text("12 34 56 78") },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.flag_denmark),
                        contentDescription = "Flag",
                        modifier = Modifier.size(30.dp),
                    )
                },
                trailingIcon = {
                    if (isPhoneNumberValid) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_verified),
                            contentDescription = "Verified",
                            modifier = Modifier.size(45.dp)
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF2F2F2),
                    unfocusedContainerColor = Color(0xFFF2F2F2),
                    disabledContainerColor = Color(0xFFF2F2F2),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    showDialog = true
                },
                modifier = Modifier
                    .width(220.dp)
                    .padding(horizontal = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0A7EFD)
                )
            ) {
                Text(text = "Fortsæt", fontSize = 18.sp)
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        Button(
                            onClick = { showDialog = false },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF0A7EFD)
                            )
                        ) {
                            Text("OK")
                        }
                    },
                    title = {
                        Text(text = "Invitation sendt!")
                    },
                    text = {
                        Text(text = "Den inviterede vil modtage et link på SMS og blive tilføjet til planlægningsfunktionen i Min Strøm⚡.")
                    }
                )
            }
        }
    }
}

fun isValidPhoneNumber(number: String): Boolean {
    val digitsOnly = number.filter { it.isDigit() }
    return digitsOnly.length >= 8
}

@Preview(showBackground = true)
@Composable
fun InviteUsersSmsPreview() {
    InviteUsersSms(navController = rememberNavController())
}
