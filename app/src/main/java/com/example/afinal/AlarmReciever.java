package com.example.afinal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlarmReciever extends BroadcastReceiver {

    // alarm reciever class that creates a notification on recieve
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifyLemubit")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Time to do your task!")
                .setContentText("You have a reminder")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }

}
