package com.edwinpaye.signaturewallet.feature.auth.domain

class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        require(email.isNotBlank()) { "Email cannot be empty" }
        require(password.isNotBlank()) { "Password cannot be empty" }
        return repository.login(email, password)
    }
}

class SignupUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(
        email: String,
        password: String,
        name: String
    ): Result<User> {
        require(email.isNotBlank()) { "Email cannot be empty" }
        require(password.length >= 8) { "Password must be at least 8 characters" }
        require(name.isNotBlank()) { "Name cannot be empty" }
        return repository.signup(email, password, name)
    }
}

class LogoutUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(): Result<Unit> {
        return repository.logout()
    }
}

class GetCurrentUserUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(): Result<User?> {
        return repository.getCurrentUser()
    }
}
