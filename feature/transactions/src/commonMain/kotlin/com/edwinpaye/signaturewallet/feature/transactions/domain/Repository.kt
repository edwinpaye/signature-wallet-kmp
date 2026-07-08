package com.edwinpaye.signaturewallet.feature.transactions.domain

import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun createTransaction(transaction: TransactionRecord): Result<String>
    suspend fun getTransaction(transactionId: String): Result<TransactionRecord>
    suspend fun getTransactionsByWallet(walletId: String): Result<List<TransactionRecord>>
    fun observeTransactions(walletId: String): Flow<List<TransactionRecord>>
    suspend fun updateTransactionStatus(transactionId: String, status: String): Result<Unit>
}
