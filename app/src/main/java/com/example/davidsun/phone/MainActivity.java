package com.example.davidsun.phone;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //need variables
    AlarmManager alarmManager;
    TimePicker alarm_timepicker;
    Context context;
    PendingIntent pendingintent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context=this;

        //initialize alarm manager
        alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);

        //initialize timepicker
        alarm_timepicker=(TimePicker) findViewById(R.id.timePicker);

        //create calendar...
        final Calendar calendar = Calendar.getInstance();
        //Intent for alarm receiver
        final Intent recintent = new Intent(this.context, Alarm_Receiver.class);

        Button startalarm = (Button) findViewById(R.id.button2);
        startalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getMinute());
                int hour = alarm_timepicker.getHour();
                int min = alarm_timepicker.getMinute();

                //create pending intent until the correct time
                pendingintent = PendingIntent.getBroadcast(MainActivity.this, 0,
                        recintent, PendingIntent.FLAG_UPDATE_CURRENT);
                //alarm manager
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingintent);
                Intent myIntent = new Intent(MainActivity.this, Alarm_Receiver.class);
                AlarmManager alarmManager2 = (AlarmManager)getSystemService(ALARM_SERVICE);
                PendingIntent pendingIntent = PendingIntent.getService(MainActivity.this, 0, myIntent, 0);

                Calendar calendarr = Calendar.getInstance();
                if(min == 0){
                    min = 59;
                    if (hour == 0) {
                        hour = 23;
                    }
                }
                calendarr.set(Calendar.HOUR_OF_DAY, hour);
                calendarr.set(Calendar.MINUTE, min);
                calendarr.set(Calendar.SECOND, 00);

                alarmManager.setRepeating(alarmManager2.RTC_WAKEUP, calendarr.getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);
            }
        });

        Button clearalarm = (Button) findViewById(R.id.button1);
        clearalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmManager.cancel(pendingintent);
            }
        });
    }
}
