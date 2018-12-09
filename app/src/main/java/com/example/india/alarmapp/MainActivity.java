package com.example.india.alarmapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Calendar;

import static java.util.Calendar.MINUTE;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    AlarmManager alrm1;
    TimePicker t1;
   // int id;
    Button b1, b2;
    TextView te;
    Context context;
    PendingIntent pen1;
    int choose_item;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        te = (TextView) findViewById(R.id.textView);

        alrm1 = (AlarmManager) getSystemService(ALARM_SERVICE);
        t1 = (TimePicker) findViewById(R.id.timePicker);
        final Calendar calendar = Calendar.getInstance();
        final Intent myintent=new Intent(this.context,AlarmClass.class);
        Spinner spinner=(Spinner)findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.Alarm_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar.set(Calendar.HOUR_OF_DAY,t1.getCurrentHour());
                calendar.set(Calendar.MINUTE,t1.getCurrentMinute());
                int hour=t1.getCurrentHour();
                int minute=t1.getCurrentMinute();
                String hour_string=String.valueOf(hour);
                String minute_string=String.valueOf(minute);


                if(hour> 12)
                {
                    hour_string=String.valueOf(hour-12);
                }
                if(minute < 10){
                    minute_string="0"+String.valueOf(minute);
                }

                te.setText("Alarm on! "+hour_string+":"+minute_string);

                myintent.putExtra("extra","on");

                myintent.putExtra("array_choice",choose_item);
                pen1=PendingIntent.getBroadcast(MainActivity.this,0,myintent,PendingIntent.FLAG_UPDATE_CURRENT);

                alrm1.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pen1);

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pen1!=null) {
                    te.setText("Alarm off");
                    alrm1.cancel(pen1);

                    myintent.putExtra("extra", "off");
                    myintent.putExtra("array_choice", choose_item);
                    sendBroadcast(myintent);
                }
                else
                {
                    te.setText("First set alarm");
                }

            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {

       // Toast.makeText(adapterView.getContext(),"the spinner item is" +id,Toast.LENGTH_SHORT).show();
       choose_item=(int)id;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}


