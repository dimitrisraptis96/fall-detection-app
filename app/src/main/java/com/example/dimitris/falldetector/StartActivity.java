package com.example.dimitris.falldetector;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    public static final String TAG = "StartActivity";

    private Sensor mSensor;
    private SensorManager mSensorManager;
    private SwitchCompat mSwitchCompat;

    private LineChart mChart;

    private List<Float> entries;

    private long lastUpdate = -1;

    private float accel_values[];
    private float last_accel_values[];

    private int fallThreshold = 10;

    private float mAccelCurrent =  SensorManager.GRAVITY_EARTH;
    private float mAccelLast = SensorManager.GRAVITY_EARTH;
    private float mAccel = 0.00f;
    /**
     * Text showing accelerometer values
     */
    private int maxAlertPeriod = 30;
    private int remainingAlertPeriod = 0;
    private boolean alert = false;
    private final static int CHECK_INTERVAL = 100; // [msec]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mSwitchCompat = (SwitchCompat) findViewById(R.id.switch1);

        // set accelerometer sensor
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mSwitchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked) {
                    // start monitoring
//                    startListening();
                    int i=0;
                    while (i<100){
                        float acc = (float) (Math.random() * 40) + 30f;
                        addEntry(acc);
                        i++;
                    }
                } else {
                    // pause monitoring
//                    stopListening();
                }
            }
        });

        mChart = (LineChart) findViewById(R.id.chart);
        mChart.setOnChartValueSelectedListener(this);

        // enable description text
        mChart.getDescription().setEnabled(true);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        // set an alternative background color
        mChart.setBackgroundColor(Color.LTGRAY);

        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);

        // add empty data
        mChart.setData(data);

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
//        l.setTypeface(mTfLight);
        l.setTextColor(Color.WHITE);

        XAxis xl = mChart.getXAxis();
//        xl.setTypeface(mTfLight);
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(false);
        xl.setAvoidFirstLastClipping(true);
        xl.setEnabled(true);

        YAxis leftAxis = mChart.getAxisLeft();
//        leftAxis.setTypeface(mTfLight);
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);

    }

    /*public void startListening(){
        if (mSensor == null) {
            Log.w(TAG, "Warning: no accelerometer");
            mSwitchCompat.setChecked(false);
            Toast.makeText(this, "Device doesn't have accelerometer", Toast.LENGTH_SHORT).show();
        } else {
            Log.w(TAG, "Warning: yes accelerometer");
            mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void stopListening(){
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Safe not to implement
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        long curTime = System.currentTimeMillis();
        // only allow one update every 100ms.
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            if ((curTime - lastUpdate) > CHECK_INTERVAL) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                accel_values = event.values.clone();

//                if (alert && remainingAlertPeriod > 0) {
//                    remainingAlertPeriod = remainingAlertPeriod - 1;
//                } else {
//                    alert = false;
//                }

                if (last_accel_values != null) {

                    *//**
                     float speed = Math.abs(
                     accel_values[0] + accel_values[1] + accel_values[2] -
                     last_accel_values[0] + last_accel_values[1] + last_accel_values[2])
                     / diffTime * 1000;
                     **//*
                    mAccelLast = mAccelCurrent;
                    mAccelCurrent =(float)Math.sqrt(accel_values[0]* accel_values[0] + accel_values[1]*accel_values[1]
                            + accel_values[2]*accel_values[2]);
                    float delta = mAccelCurrent - mAccelLast;
                    mAccel = mAccel * 0.9f + delta;

                    entries.add(new Entry(data.getValueX(), data.getValueY()));

                    Log.w(TAG, "mAccel = "+mAccel);

                    if (mAccel > fallThreshold) {
						*//*
						 * Send Alert
						 *//*
                        Log.d(TAG, "acceleration greater than threshold");
//                        alert = true;
//                        remainingAlertPeriod = maxAlertPeriod;
//
//                        Message message = new Message();
//                        message.what = EventTrigger.ACCELEROMETER;
//                        message.getData().putString("path",mAccel+"");
//
//                        try {
//                            if (serviceMessenger != null) {
//                                serviceMessenger.send(message);
//                            }
//                        } catch (RemoteException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
                    }
                }
                last_accel_values = accel_values.clone();
            }
        }
    }*/

    private void addEntry(float acceleration) {

        LineData data = mChart.getData();

        if (data != null) {

            ILineDataSet set = data.getDataSetByIndex(0);
            // set.addEntry(...); // can be called as well

            if (set == null) {
                set = createSet();
                data.addDataSet(set);
            }

            data.addEntry(new Entry(set.getEntryCount(), acceleration), 0);
            data.notifyDataChanged();

            // let the chart know it's data has changed
            mChart.notifyDataSetChanged();

            // limit the number of visible entries
            mChart.setVisibleXRangeMaximum(120);
            // mChart.setVisibleYRange(30, AxisDependency.LEFT);

            // move to the latest entry
            mChart.moveViewToX(data.getEntryCount());

            // this automatically refreshes the chart (calls invalidate())
            // mChart.moveViewTo(data.getXValCount()-7, 55f,
            // AxisDependency.LEFT);
        }
    }

    private LineDataSet createSet() {

        LineDataSet set = new LineDataSet(null, "Dynamic Data");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(ColorTemplate.getHoloBlue());
        set.setCircleColor(Color.WHITE);
        set.setLineWidth(2f);
        set.setCircleRadius(4f);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(Color.rgb(244, 117, 117));
        set.setValueTextColor(Color.WHITE);
        set.setValueTextSize(9f);
        set.setDrawValues(false);
        return set;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i("Entry selected", e.toString());
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }

}
