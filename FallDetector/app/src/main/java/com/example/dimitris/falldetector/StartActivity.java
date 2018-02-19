package com.example.dimitris.falldetector;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.github.mikephil.charting.charts.LineChart;

public class StartActivity extends AppCompatActivity{

    public static final String TAG = "StartActivity";

    private SwitchCompat mSwitchCompat;

    private ToggleButton mToggle;

    private SensorManager mSensorManager;
    private Sensor mSensor;

    private LineChart mLineChart;
    private Plot mPlot;

    public static final String Code = "codeKey";
    public static final String Phone = "phoneKey";

//    private AccelerometerService mService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mSwitchCompat = (SwitchCompat) findViewById(R.id.switch1);

        mLineChart = (LineChart) findViewById(R.id.chart);

        mToggle = (ToggleButton) findViewById(R.id.toggleButton);

        mPlot = new Plot(mLineChart);
        mPlot.setChart();

        // set accelerometer sensor
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        final Accelerometer accelerometer = new Accelerometer(mSensorManager, mSensor, mHandler);

        mSwitchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked) {
                    accelerometer.startListening();
                } else {
                    accelerometer.stopListening();
                }
            }
        });

    }

    public static final String MyPREFERENCES = "MyPrefs" ;

    private String getUri(){
        SharedPreferences preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String c = preferences.getString(Code,null);
        String ph = preferences.getString(Phone,null);
        Log.w(TAG, "tel: +" + c + ph);
        return "tel: +" + c + ph;
    }

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.MESSAGE_CHANGED:
                    float value = msg.getData().getFloat(Constants.VALUE);
                    mPlot.addEntry(value);

                    break;
                case Constants.MESSAGE_EMERGENCY:
//                    Intent intent = new Intent(android.content.Intent.ACTION_CALL, Uri.parse(getUri()));
//                    startActivity(intent);
                    mToggle.setChecked(true);

                    break;
                case Constants.MESSAGE_TOAST:
                    Toast.makeText(getApplicationContext(),msg.getData().getString(Constants.TOAST),
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

}
