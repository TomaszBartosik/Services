package com.example.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";
    private SensorManager sensorManager;
    Sensor accelerometer;

    TextView edittext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittext = findViewById(R.id.editText);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener((SensorEventListener) MainActivity.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);






        BroadcastReceiver myReceiver1 = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                edittext.setText("Telefon sie laduje");
                //Toast.makeText(context,"Telefon sie laduje",Toast.LENGTH_LONG).show();
            }
        };


            registerReceiver(myReceiver1, new IntentFilter(Intent.ACTION_POWER_CONNECTED));


    }

    public void SendBroadcast(View view) {

        Intent intent = new Intent("com.codingflow.EXAMPLE_ACTION");
        intent.putExtra("com.codingflow.EXAMPLE_ACTION","Broadcast received");
        sendBroadcast(intent);





    }

    @Override
    protected void onResume() {
        super.onResume();


        BroadcastReceiver myReceiver2 = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                edittext.setText("moj");
                //Toast.makeText(context,"My broadcast ",Toast.LENGTH_LONG).show();

            }
        };

        registerReceiver(myReceiver2, new IntentFilter("com.codingflow.EXAMPLE_ACTION"));

    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
       // Toast.makeText(getApplicationContext(),"x"+sensorEvent.values[0],Toast.LENGTH_LONG).show();
        double x =sensorEvent.values[0];
        float y =sensorEvent.values[1];
        float z =sensorEvent.values[2];
        int xa = (int)x;
        int ya = (int)y;
        int za = (int)z;



        edittext.setText("x:"+xa +"y"+ya+"z"+za);



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
