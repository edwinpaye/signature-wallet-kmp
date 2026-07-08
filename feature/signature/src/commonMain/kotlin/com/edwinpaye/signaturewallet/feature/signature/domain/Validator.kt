package com.edwinpaye.signaturewallet.feature.signature.domain

import kotlin.math.sqrt

interface SignatureValidator {
    fun validateSignature(signature: Signature, baseline: Signature): Float
    fun validateSensorPattern(signatureSensor: SensorData, baselineSensor: SensorData): Float
    fun calculateSwipePattern(points: List<SignaturePoint>): Float
}

class SignatureValidatorImpl : SignatureValidator {
    
    override fun validateSignature(signature: Signature, baseline: Signature): Float {
        if (signature.points.isEmpty() || baseline.points.isEmpty()) return 0f
        
        val sizeScore = calculateSizeScore(signature, baseline)
        val velocityScore = calculateVelocityScore(signature, baseline)
        val pressureScore = calculatePressureScore(signature, baseline)
        val timeScore = calculateTimeScore(signature, baseline)
        
        return (sizeScore + velocityScore + pressureScore + timeScore) / 4f
    }

    override fun validateSensorPattern(signatureSensor: SensorData, baselineSensor: SensorData): Float {
        val gyroScore = calculateDistance(
            Triple(signatureSensor.gyroscopeX, signatureSensor.gyroscopeY, signatureSensor.gyroscopeZ),
            Triple(baselineSensor.gyroscopeX, baselineSensor.gyroscopeY, baselineSensor.gyroscopeZ)
        )
        
        val accelScore = calculateDistance(
            Triple(signatureSensor.accelerometerX, signatureSensor.accelerometerY, signatureSensor.accelerometerZ),
            Triple(baselineSensor.accelerometerX, baselineSensor.accelerometerY, baselineSensor.accelerometerZ)
        )
        
        val magScore = calculateDistance(
            Triple(signatureSensor.magnetometerX, signatureSensor.magnetometerY, signatureSensor.magnetometerZ),
            Triple(baselineSensor.magnetometerX, baselineSensor.magnetometerY, baselineSensor.magnetometerZ)
        )
        
        return 1f - ((gyroScore + accelScore + magScore) / 3f).coerceIn(0f, 1f)
    }

    override fun calculateSwipePattern(points: List<SignaturePoint>): Float {
        if (points.size < 2) return 0f
        
        var totalDistance = 0f
        for (i in 0 until points.size - 1) {
            val p1 = points[i]
            val p2 = points[i + 1]
            val dx = p2.x - p1.x
            val dy = p2.y - p1.y
            totalDistance += sqrt(dx * dx + dy * dy)
        }
        
        return (totalDistance / 1000f).coerceIn(0f, 1f)
    }

    private fun calculateSizeScore(signature: Signature, baseline: Signature): Float {
        val sigBounds = getBounds(signature.points)
        val baseBounds = getBounds(baseline.points)
        
        val sigArea = sigBounds.width * sigBounds.height
        val baseArea = baseBounds.width * baseBounds.height
        
        return if (baseArea > 0) {
            1f - (kotlin.math.abs(sigArea - baseArea) / baseArea).coerceIn(0f, 1f)
        } else {
            0f
        }
    }

    private fun calculateVelocityScore(signature: Signature, baseline: Signature): Float {
        val sigVelocity = calculateAverageVelocity(signature.points)
        val baseVelocity = calculateAverageVelocity(baseline.points)
        
        return if (baseVelocity > 0) {
            1f - (kotlin.math.abs(sigVelocity - baseVelocity) / baseVelocity).coerceIn(0f, 1f)
        } else {
            0f
        }
    }

    private fun calculatePressureScore(signature: Signature, baseline: Signature): Float {
        val sigAvgPressure = signature.points.map { it.pressure }.average()
        val baseAvgPressure = baseline.points.map { it.pressure }.average()
        
        return 1f - (kotlin.math.abs(sigAvgPressure - baseAvgPressure) / baseAvgPressure).coerceIn(0f, 1f)
    }

    private fun calculateTimeScore(signature: Signature, baseline: Signature): Float {
        if (signature.points.isEmpty() || baseline.points.isEmpty()) return 0f
        
        val sigTime = signature.points.last().timestamp - signature.points.first().timestamp
        val baseTime = baseline.points.last().timestamp - baseline.points.first().timestamp
        
        return if (baseTime > 0) {
            1f - (kotlin.math.abs(sigTime - baseTime).toFloat() / baseTime).coerceIn(0f, 1f)
        } else {
            0f
        }
    }

    private fun calculateAverageVelocity(points: List<SignaturePoint>): Float {
        if (points.size < 2) return 0f
        
        var totalVelocity = 0f
        var count = 0
        
        for (i in 0 until points.size - 1) {
            val p1 = points[i]
            val p2 = points[i + 1]
            val distance = sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y))
            val time = (p2.timestamp - p1.timestamp).toFloat()
            if (time > 0) {
                totalVelocity += distance / time
                count++
            }
        }
        
        return if (count > 0) totalVelocity / count else 0f
    }

    private fun calculateDistance(p1: Triple<Float, Float, Float>, p2: Triple<Float, Float, Float>): Float {
        val dx = p1.first - p2.first
        val dy = p1.second - p2.second
        val dz = p1.third - p2.third
        return sqrt(dx * dx + dy * dy + dz * dz) / 100f
    }

    private fun getBounds(points: List<SignaturePoint>): Bounds {
        if (points.isEmpty()) return Bounds(0f, 0f, 0f, 0f)
        
        val minX = points.minOf { it.x }
        val maxX = points.maxOf { it.x }
        val minY = points.minOf { it.y }
        val maxY = points.maxOf { it.y }
        
        return Bounds(minX, minY, maxX - minX, maxY - minY)
    }

    private data class Bounds(val x: Float, val y: Float, val width: Float, val height: Float)
}
