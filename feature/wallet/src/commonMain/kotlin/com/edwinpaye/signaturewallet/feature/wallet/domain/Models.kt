package com.edwinpaye.signaturewallet.feature.wallet.domain

import kotlinx.serialization.Serializable

@Serializable
data class Wallet(
    val id: String,
    val userId: String,
    val address: String,
    val balance: Double,
    val currency: String = "USD",
    val isActive: Boolean = true,
    val createdAt: Long,
    val updatedAt: Long
)

@Serializable
data class WalletTransaction(
    val id: String,
    val walletId: String,
    val amount: Double,
    val type: TransactionType,
    val status: TransactionStatus,
    val timestamp: Long,
    val recipientAddress: String? = null,
    val description: String? = null
)

enum class TransactionType {
    SEND, RECEIVE, DEPOSIT, WITHDRAWAL
}

enum class TransactionStatus {
    PENDING, COMPLETED, FAILED, CANCELLED
}
