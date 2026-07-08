package com.edwinpaye.signaturewallet.feature.signature.domain

import kotlinx.serialization.Serializable

@Serializable
data class SignaturePoint(
    val x: Float,
    val y: Float,
    val pressure: Float = 1.0f,
    val timestamp: Long,
    val angle: Float = 0f
)

@Serializable
data class Signature(
    val id: String,
    val userId: String,
    val points: List<SignaturePoint>,
    val baseline: String? = null,
    val createdAt: Long,
    val isVerified: Boolean = false,
    val verificationScore: Float = 0f
)

@Serializable
data class SensorData(
    val timestamp: Long,
    val gyroscopeX: Float = 0f,
    val gyroscopeY: Float = 0f,
    val gyroscopeZ: Float = 0f,
    val accelerometerX: Float = 0f,
    val accelerometerY: Float = 0f,
    val accelerometerZ: Float = 0f,
    val magnetometerX: Float = 0f,
    val magnetometerY: Float = 0f,
    val magnetometerZ: Float = 0f
)
