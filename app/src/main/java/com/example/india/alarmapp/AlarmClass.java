package com.example.india.alarmapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmClass extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String extra=intent.getExtras().getString("extra");

        Integer input=intent.getExtras().getInt("array_choice");

        Intent service_intent=new Intent(context,RingtoneService.class);
        service_intent.putExtra("extra",extra);
        service_intent.putExtra("array_choice",input);
        context.startService(service_intent);



    }
}