package com.edwinpaye.signaturewallet.feature.auth.domain

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User>
    suspend fun signup(email: String, password: String, name: String): Result<User>
    suspend fun logout(): Result<Unit>
    suspend fun getCurrentUser(): Result<User?>
    suspend fun enableBiometric(userId: String): Result<Unit>
    suspend fun verifyBiometric(userId: String): Result<Boolean>
}
