package com.samansepahvand.chatappfinal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.samansepahvand.chatappfinal.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FCM";
    String newToken="null";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getToken();

    }

    private String getToken() {

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, instanceIdResult -> {
            newToken = instanceIdResult.getToken();
            Log.e(TAG, newToken);
        });

        return newToken;
    }

}