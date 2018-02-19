/*
package com.example.dimitris.falldetector;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.IntDef;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import static com.example.dimitris.falldetector.StartActivity.TAG;

public class AccelerometerService extends Service implements SensorEventListener {

    private Sensor mAccelerometer;
    private SensorManager mSensorManager;

    private final Handler mHandler;

    private int fallThreshold = 10;
    private long lastUpdate = -1;
    private float accel_values[];
    private float last_accel_values[];
    private float mAccelCurrent = SensorManager.GRAVITY_EARTH;
    private float mAccelLast = SensorManager.GRAVITY_EARTH;
    private float mAccel = 0.00f;

    private final static int CHECK_INTERVAL = 100; // [msec]


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mSensorManager.registerListener(this, mAccelerometer,
                SensorManager.SENSOR_DELAY_UI, new Handler());
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        long curTime = System.currentTimeMillis();
        // sampling with f= 10Hz.
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
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
                    bundle.putFloat(Constants.VALUE, mAccelCurrent);
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);

                    mChart.addEntry(mAccelCurrent);

                    if (mAccel > fallThreshold) {

                        Log.d(TAG, "acceleration greater than threshold");
                        // Send the emergency message back to the Activity
                        Message msg = mHandler.obtainMessage(Constants.MESSAGE_CHANGED);
                        Bundle bundle = new Bundle();
                        bundle.putFloat(Constants.VALUE, mAccelCurrent);
                        msg.setData(bundle);
                        mHandler.sendMessage(msg);
//                      // TODO: here maybe i have a fall => call contact
//                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//                        String c = preferences.getString(Code,null);
//                        String ph = preferences.getString(Phone,null);
//                        String uri = "tel: +" + c + ph;
//                        Intent intent = new Intent(android.content.Intent.ACTION_CALL, Uri.parse(uri));
//                        startActivity(intent);

                    }
                }
                last_accel_values = accel_values.clone();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // Safe not to implement
    }
}
*/
