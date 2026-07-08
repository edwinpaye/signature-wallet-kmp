package com.edwinpaye.signaturewallet.feature.wallet.domain

import kotlinx.coroutines.flow.Flow

class GetWalletUseCase(private val repository: WalletRepository) {
    suspend operator fun invoke(walletId: String): Result<Wallet> {
        require(walletId.isNotBlank()) { "Wallet ID cannot be empty" }
        return repository.getWallet(walletId)
    }
}

class ObserveWalletBalanceUseCase(private val repository: WalletRepository) {
    operator fun invoke(walletId: String): Flow<Double> {
        require(walletId.isNotBlank()) { "Wallet ID cannot be empty" }
        return repository.observeWalletBalance(walletId)
    }
}

class GetTransactionHistoryUseCase(private val repository: WalletRepository) {
    suspend operator fun invoke(walletId: String): Result<List<WalletTransaction>> {
        require(walletId.isNotBlank()) { "Wallet ID cannot be empty" }
        return repository.getTransactionHistory(walletId)
    }
}
