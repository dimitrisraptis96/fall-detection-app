package com.example.dimitris.falldetector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetActivity extends AppCompatActivity {

    public static final String TAG = "SetActivity";

    private EditText mEditTextCode;
    private EditText mEditTextPhoneNumber;
    private Button mBttnDone;

    private String mCountryCode;
    private String mPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        mEditTextCode = (EditText) findViewById(R.id.et_country_code);
        mEditTextPhoneNumber = (EditText) findViewById(R.id.et_phone_number);

        mBttnDone = (Button)    findViewById(R.id.bttn_done);
        mBttnDone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    mCountryCode = mEditTextCode.getText().toString();
                    mPhoneNumber = mEditTextPhoneNumber.getText().toString();
                    Log.d(TAG, "country code: "+mCountryCode+" phone number: "+mPhoneNumber);
                    Toast.makeText(getApplicationContext(), "Saved successfully!" + mCountryCode+ mPhoneNumber, Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    Log.e(TAG, "onClick: error during setting code or phone number");
                    Toast.makeText(getApplicationContext(), "Error during initializing contact", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
