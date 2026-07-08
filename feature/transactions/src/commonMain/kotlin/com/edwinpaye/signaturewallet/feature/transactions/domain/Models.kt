package com.edwinpaye.signaturewallet.feature.transactions.domain

import kotlinx.serialization.Serializable

@Serializable
data class TransactionRecord(
    val id: String,
    val walletId: String,
    val amount: Double,
    val status: String,
    val createdAt: Long,
    val signatureId: String? = null,
    val sensorData: SensorDataRecord? = null
)

@Serializable
data class SensorDataRecord(
    val gyroscopeX: Float = 0f,
    val gyroscopeY: Float = 0f,
    val gyroscopeZ: Float = 0f,
    val accelerometerX: Float = 0f,
    val accelerometerY: Float = 0f,
    val accelerometerZ: Float = 0f
)
