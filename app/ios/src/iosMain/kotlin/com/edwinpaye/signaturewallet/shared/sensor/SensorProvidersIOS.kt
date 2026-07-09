package com.edwinpaye.signaturewallet.shared.sensor

import com.edwinpaye.signaturewallet.feature.signature.domain.SensorData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

actual class GyroscopeSensorProvider : SensorProvider {
    actual override fun startListening(): Flow<SensorData> = flowOf()
    actual override fun stopListening() {}
    actual override fun isAvailable(): Boolean = false
}

actual class AccelerometerSensorProvider : SensorProvider {
    actual override fun startListening(): Flow<SensorData> = flowOf()
    actual override fun stopListening() {}
    actual override fun isAvailable(): Boolean = false
}

actual class MagnetometerSensorProvider : SensorProvider {
    actual override fun startListening(): Flow<SensorData> = flowOf()
    actual override fun stopListening() {}
    actual override fun isAvailable(): Boolean = false
}
