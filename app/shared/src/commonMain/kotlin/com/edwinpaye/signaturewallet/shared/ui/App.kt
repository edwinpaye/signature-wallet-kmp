package com.edwinpaye.signaturewallet.shared.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.edwinpaye.signaturewallet.core.designsystem.SignatureWalletTheme

@Composable
fun App(modifier: Modifier = Modifier) {
    SignatureWalletTheme {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Signature Wallet",
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }
    }
}
