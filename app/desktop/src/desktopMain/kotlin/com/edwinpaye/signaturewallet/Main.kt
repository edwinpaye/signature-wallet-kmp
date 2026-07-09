package com.edwinpaye.signaturewallet

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.edwinpaye.signaturewallet.shared.ui.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Signature Wallet"
    ) {
        App()
    }
}
