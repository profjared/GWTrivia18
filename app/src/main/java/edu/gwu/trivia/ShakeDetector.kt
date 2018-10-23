package edu.gwu.trivia

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log

class ShakeDetector (private val context: Context): SensorEventListener {
    private var sensorManager: SensorManager? = null
    private var accelerometer: Sensor? = null
    var shakeListener: ShakeListener? = null

    private var acceleration = 0.0f
    private var accelerationCurrent = SensorManager.GRAVITY_EARTH
    private var accelerationLast = SensorManager.GRAVITY_EARTH

    private val TAG = "ShakeDetector"

    interface ShakeListener {
        fun shakeDetected()
    }

    fun start() {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorManager?.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    fun stop() {
        sensorManager?.unregisterListener(this, accelerometer)

        sensorManager = null
        accelerometer = null
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //not using this
    }

    override fun onSensorChanged(sensorEvent: SensorEvent) {
        val x = sensorEvent.values[0]
        val y = sensorEvent.values[1]
        val z = sensorEvent.values[2]

        Log.d(TAG, "x:$x y:$y z:$z")

        accelerationCurrent = Math.sqrt((x * x + y * y + z * z).toDouble()).toFloat()

        var delta = accelerationCurrent - accelerationLast

        accelerationLast = accelerationCurrent

        acceleration = acceleration * 0.9f + delta //perform low pass filter

        Log.d(TAG, "acceleration:$acceleration")

        if(acceleration > 8) {
            shakeListener?.shakeDetected()
        }



    }


}