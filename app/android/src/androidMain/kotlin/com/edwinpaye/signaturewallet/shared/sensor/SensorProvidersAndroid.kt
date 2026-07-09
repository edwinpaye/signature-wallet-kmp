package com.edwinpaye.signaturewallet.shared.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.edwinpaye.signaturewallet.feature.signature.domain.SensorData
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

actual class GyroscopeSensorProvider(
    private val context: Context
) : SensorProvider {
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

    actual override fun startListening(): Flow<SensorData> = callbackFlow {
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                if (event != null) {
                    trySend(
                        SensorData(
                            timestamp = System.currentTimeMillis(),
                            gyroscopeX = event.values[0],
                            gyroscopeY = event.values[1],
                            gyroscopeZ = event.values[2]
                        )
                    )
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        sensorManager.registerListener(listener, gyroscope, SensorManager.SENSOR_DELAY_FASTEST)
        awaitClose {
            sensorManager.unregisterListener(listener)
        }
    }

    actual override fun stopListening() {}

    actual override fun isAvailable(): Boolean = gyroscope != null
}

actual class AccelerometerSensorProvider(
    private val context: Context
) : SensorProvider {
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    actual override fun startListening(): Flow<SensorData> = callbackFlow {
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                if (event != null) {
                    trySend(
                        SensorData(
                            timestamp = System.currentTimeMillis(),
                            accelerometerX = event.values[0],
                            accelerometerY = event.values[1],
                            accelerometerZ = event.values[2]
                        )
                    )
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        sensorManager.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_FASTEST)
        awaitClose {
            sensorManager.unregisterListener(listener)
        }
    }

    actual override fun stopListening() {}

    actual override fun isAvailable(): Boolean = accelerometer != null
}

actual class MagnetometerSensorProvider(
    private val context: Context
) : SensorProvider {
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

    actual override fun startListening(): Flow<SensorData> = callbackFlow {
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                if (event != null) {
                    trySend(
                        SensorData(
                            timestamp = System.currentTimeMillis(),
                            magnetometerX = event.values[0],
                            magnetometerY = event.values[1],
                            magnetometerZ = event.values[2]
                        )
                    )
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        sensorManager.registerListener(listener, magnetometer, SensorManager.SENSOR_DELAY_FASTEST)
        awaitClose {
            sensorManager.unregisterListener(listener)
        }
    }

    actual override fun stopListening() {}

    actual override fun isAvailable(): Boolean = magnetometer != null
}
