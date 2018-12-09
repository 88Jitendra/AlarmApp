package com.example.india.alarmapp;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Random;

public class RingtoneService extends Service {
     MediaPlayer mediaPlayer;
    int start_id;
    boolean running;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return  null;
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

            String state=intent.getExtras().getString("extra");

        Integer choice=intent.getExtras().getInt("array_choice");
        NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Intent intent_main_activity = new Intent(this.getApplicationContext(),MainActivity.class);

        PendingIntent pen= PendingIntent.getActivity(this,0,intent_main_activity,0);
        Notification notificatio_popup=new Notification.Builder(this)
                .setSmallIcon(R.drawable.alarmicon)
                .setContentTitle("An alarm is going off")
                .setContentText("Click on it")
                .setContentIntent(pen)
                .setAutoCancel(true)
                .build();




        assert state !=null;
        switch (state)
        {
            case "on": start_id=1;
                   break;
            case "off": start_id=0;
                  break;
            default: start_id=0;
                break;

        }

       if(!this.running && start_id==1)
       {

           this.running=true;
           this.start_id=0;



           notificationManager.notify(0,notificatio_popup);

           if(choice==0)
           {
               int minimum=1;
               int maximum=10;
               Random random=new Random();
               int wale=random.nextInt(maximum+minimum);
               if(wale==1) {
                   mediaPlayer = MediaPlayer.create(this, R.raw.amazing);
                   mediaPlayer.start();
               }
               else if(wale==2)
               {
                   mediaPlayer = MediaPlayer.create(this,R.raw.car_lock_tone);
                   mediaPlayer.start();
               }
               else if(wale==3)
               {
                   mediaPlayer = MediaPlayer.create(this,R.raw.emergency_alert);
                   mediaPlayer.start();
               }
               else if(wale==4)
               {
                   mediaPlayer = MediaPlayer.create(this,R.raw.fog_horn);
                   mediaPlayer.start();
               }
               else if(wale==5)
               {
                   mediaPlayer = MediaPlayer.create(this,R.raw.gta_5_alarm);
                   mediaPlayer.start();
               }
               else if(wale==6)
               {
                   mediaPlayer = MediaPlayer.create(this,R.raw.gothic_piano_instrumental);
                   mediaPlayer.start();
               }
               else if(wale==7)
               {
                   mediaPlayer = MediaPlayer.create(this,R.raw.like_it_loud);
                   mediaPlayer.start();
               }
               else if(wale==8)
               {
                   mediaPlayer = MediaPlayer.create(this,R.raw.louder_gypsy);
                   mediaPlayer.start();
               }
               else if(wale==9)
               {
                   mediaPlayer = MediaPlayer.create(this,R.raw.phone_bell_ringing);
                   mediaPlayer.start();
               }
               else
               {
                   mediaPlayer = MediaPlayer.create(this,R.raw.think_lod);
                   mediaPlayer.start();
               }

           }
           else if(choice==1)
           {
                   mediaPlayer = MediaPlayer.create(this, R.raw.amazing);

                   mediaPlayer.start();


           }
           else if(choice==2)
           {
               mediaPlayer = MediaPlayer.create(this,R.raw.car_lock_tone);
               mediaPlayer.start();
           }
           else if(choice==3)
           {
               mediaPlayer = MediaPlayer.create(this,R.raw.emergency_alert);
               mediaPlayer.start();
           }
           else if(choice==4)
           {
               mediaPlayer = MediaPlayer.create(this,R.raw.fog_horn);
               mediaPlayer.start();
           }
           else if(choice==5)
           {
               mediaPlayer = MediaPlayer.create(this,R.raw.gta_5_alarm);
               mediaPlayer.start();
           }
           else if(choice==6)
           {
               mediaPlayer = MediaPlayer.create(this,R.raw.gothic_piano_instrumental);
               mediaPlayer.start();
           }
           else if(choice==7)
           {
               mediaPlayer = MediaPlayer.create(this,R.raw.like_it_loud);
               mediaPlayer.start();
           }
           else if(choice==8)
           {
               mediaPlayer = MediaPlayer.create(this,R.raw.louder_gypsy);
               mediaPlayer.start();
           }
           else if(choice==9)
           {
               mediaPlayer = MediaPlayer.create(this,R.raw.phone_bell_ringing);
               mediaPlayer.start();
           }
           else
           {
               mediaPlayer = MediaPlayer.create(this,R.raw.think_lod);
               mediaPlayer.start();
           }
       }
        else if(this.running && start_id==0)
       {
           mediaPlayer.stop();
           mediaPlayer.reset();
           this.running=false;
           this.start_id=0;
       }
        else if(!this.running && start_id==0)
       {
           this.running=false;
           this.start_id=0;
       }
        else if(this.running && start_id==1)
       {
          this.running=true;
          this.start_id=1;
       }
        else
       {
           this.running=false;
           this.start_id=0;//g.e("This is error");
       }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

       super.onDestroy();
        this.running=false;
       //oast.makeText(this,"On Destroy is called",Toast.LENGTH_SHORT).show();
    }
}