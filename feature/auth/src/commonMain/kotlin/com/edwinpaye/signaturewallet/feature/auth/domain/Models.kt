package com.edwinpaye.signaturewallet.feature.auth.domain

sealed class AuthEvent {
    data class LoginSuccess(val userId: String) : AuthEvent()
    data class LoginFailure(val error: String) : AuthEvent()
    data class BiometricRequired(val userId: String) : AuthEvent()
    object LogoutSuccess : AuthEvent()
}

data class User(
    val id: String,
    val email: String,
    val name: String,
    val createdAt: Long,
    val isBiometricEnabled: Boolean = false
)
