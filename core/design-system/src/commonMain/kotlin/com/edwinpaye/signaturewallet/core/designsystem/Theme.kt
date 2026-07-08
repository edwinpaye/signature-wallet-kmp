package com.edwinpaye.signaturewallet.core.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val PrimaryColor = Color(0xFF2E7D32)
private val SecondaryColor = Color(0xFF1976D2)
private val TertiaryColor = Color(0xFFF57C00)
private val BackgroundColor = Color(0xFFFAFAFA)
private val SurfaceColor = Color(0xFFFFFFFF)
private val ErrorColor = Color(0xFFD32F2F)

private val LightColors = lightColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    tertiary = TertiaryColor,
    background = BackgroundColor,
    surface = SurfaceColor,
    error = ErrorColor,
)

private val DarkColors = darkColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    tertiary = TertiaryColor,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    error = ErrorColor,
)

@Composable
fun SignatureWalletTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
