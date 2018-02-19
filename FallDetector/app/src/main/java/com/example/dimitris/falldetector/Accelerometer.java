package com.example.dimitris.falldetector;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


public class Accelerometer implements SensorEventListener {

    public static final String TAG = "Accelerometer";

    private Sensor mSensor;
    private SensorManager mSensorManager;

    private final Handler mHandler;

    private long lastUpdate = -1;

    private float accel_values[];
    private float last_accel_values[];

    private int fallThreshold = 10;

    private float mAccelCurrent = SensorManager.GRAVITY_EARTH;
    private float mAccelLast = SensorManager.GRAVITY_EARTH;
    private float mAccel = 0.00f;

    private final static int CHECK_INTERVAL = 100; // [msec]

    public static final String Code = "codeKey";
    public static final String Phone = "phoneKey";


    public Accelerometer(SensorManager sm, Sensor s, Handler h){
        mSensorManager = sm;
        mSensor = s;
        mHandler = h;
    }

    public void startListening(){
        if (mSensor == null) {
            Log.w(TAG, "Warning: no accelerometer");

            // Send a failure message back to the Activity
            Message msg = mHandler.obtainMessage(Constants.MESSAGE_TOAST);
            Bundle bundle = new Bundle();
            bundle.putString(Constants.TOAST, "Unable to find accelerometer");
            msg.setData(bundle);
            mHandler.sendMessage(msg);

        } else {
            Log.w(TAG, "Warning: yes accelerometer");

            mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void stopListening(){
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { /*Safe not to implement*/ }

    @Override
    public void onSensorChanged(SensorEvent event) {

        long curTime = System.currentTimeMillis();

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // sampling frequency f= 10Hz.
            if ((curTime - lastUpdate) > CHECK_INTERVAL) {

                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                accel_values = event.values.clone();

                if (last_accel_values != null) {

                    mAccelLast = mAccelCurrent;
                    mAccelCurrent =(float)Math.sqrt(accel_values[0]* accel_values[0] + accel_values[1]*accel_values[1]
                            + accel_values[2]*accel_values[2]);

                    float delta = mAccelCurrent - mAccelLast;
                    mAccel = mAccel * 0.9f + delta;

                    // Send the value back to the Activity
                    Message msg = mHandler.obtainMessage(Constants.MESSAGE_CHANGED);
                    Bundle bundle = new Bundle();
                    bundle.putFloat(Constants.VALUE, mAccel);
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);

                    if (mAccel > fallThreshold) {

                        Log.w(TAG, "acceleration greater than threshold");
                        // Send the value back to the Activity
                        msg = mHandler.obtainMessage(Constants.MESSAGE_EMERGENCY);
                        mHandler.sendMessage(msg);
                    }
                }
                last_accel_values = accel_values.clone();
            }
        }
    }

}
