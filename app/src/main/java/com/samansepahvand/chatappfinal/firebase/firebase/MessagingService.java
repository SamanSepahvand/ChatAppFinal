package com.samansepahvand.chatappfinal.firebase.firebase;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService {

    private static final String TAG = "FCM";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.e(TAG, "onMessageReceived: "+remoteMessage.getNotification().getBody());

    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        //appDatabase.cleanUp();
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d("NEW_TOKEN", s);
        getToken(s);
    }

    private static String getToken(String s) {
        return s;
    }



}

