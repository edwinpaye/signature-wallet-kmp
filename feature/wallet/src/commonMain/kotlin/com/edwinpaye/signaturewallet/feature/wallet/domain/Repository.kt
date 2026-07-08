package com.edwinpaye.signaturewallet.feature.wallet.domain

import kotlinx.coroutines.flow.Flow

interface WalletRepository {
    suspend fun createWallet(userId: String, initialBalance: Double): Result<Wallet>
    suspend fun getWallet(walletId: String): Result<Wallet>
    suspend fun getWalletsByUserId(userId: String): Result<List<Wallet>>
    fun observeWalletBalance(walletId: String): Flow<Double>
    suspend fun updateBalance(walletId: String, newBalance: Double): Result<Unit>
    suspend fun getTransactionHistory(walletId: String): Result<List<WalletTransaction>>
    fun observeTransactions(walletId: String): Flow<List<WalletTransaction>>
}
