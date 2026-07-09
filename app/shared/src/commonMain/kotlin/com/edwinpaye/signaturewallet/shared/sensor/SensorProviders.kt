package com.edwinpaye.signaturewallet.shared.sensor

import com.edwinpaye.signaturewallet.feature.signature.domain.SensorData
import kotlinx.coroutines.flow.Flow

interface SensorProvider {
    fun startListening(): Flow<SensorData>
    fun stopListening()
    fun isAvailable(): Boolean
}

expect class GyroscopeSensorProvider : SensorProvider {
    override fun startListening(): Flow<SensorData>
    override fun stopListening()
    override fun isAvailable(): Boolean
}

expect class AccelerometerSensorProvider : SensorProvider {
    override fun startListening(): Flow<SensorData>
    override fun stopListening()
    override fun isAvailable(): Boolean
}

expect class MagnetometerSensorProvider : SensorProvider {
    override fun startListening(): Flow<SensorData>
    override fun stopListening()
    override fun isAvailable(): Boolean
}

class CompositeSensorProvider(
    private val gyroscope: GyroscopeSensorProvider,
    private val accelerometer: AccelerometerSensorProvider,
    private val magnetometer: MagnetometerSensorProvider
) {
    fun startMonitoring(): Flow<SensorData> {
        // TODO: Combine all sensor streams
        return gyroscope.startListening()
    }

    fun stopMonitoring() {
        gyroscope.stopListening()
        accelerometer.stopListening()
        magnetometer.stopListening()
    }
}
