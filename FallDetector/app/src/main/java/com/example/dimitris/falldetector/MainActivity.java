package com.example.dimitris.falldetector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";

    private Button set;
    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        set = (Button) findViewById(R.id.btn_set);
        set.setOnClickListener(this);

        start = (Button) findViewById(R.id.btn_start);
        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_set:
                intent = new Intent(this, SetActivity.class);
                startActivity(intent);
                return;
            case R.id.btn_start:
                intent = new Intent(this, StartActivity.class);
                startActivity(intent);
                return;
        }
    }

}
