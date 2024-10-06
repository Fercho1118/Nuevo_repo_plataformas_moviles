package com.uvg.rueda.lab08.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.uvg.rueda.lab08.R

@Composable
fun LoginScreen(onNavigateToCharacters: () -> Unit) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logorickandmorty),
                contentDescription = "Rick and Morty Logo",
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .aspectRatio(2f)
                    .padding(bottom = 24.dp),
                contentScale = ContentScale.Fit,
            )
            Spacer(modifier = Modifier.height(200.dp))
            Button(onClick = onNavigateToCharacters) {
                Text(text = "Entrar")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Fernando Rueda - #23748",
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}
