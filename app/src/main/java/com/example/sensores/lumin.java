package com.example.sensores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class lumin extends AppCompatActivity {

    // float valormax;
    ConstraintLayout fondopantalla2;
    Button regresar, Enviar;
    Sensor misensorr;
    SensorManager admistradorsensoress;
    SensorEventListener escuchuadordeEventoss;

    TextView etiquetaresultados;
    ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lumin);
        setContentView(R.layout.activity_lumin);

        fondopantalla2 = findViewById(R.id.fonpantalla);
        Enviar = findViewById(R.id.btn_Whatsp);
        etiquetaresultados = findViewById(R.id.TXVresultado);

        admistradorsensoress = (SensorManager) getSystemService(SENSOR_SERVICE);

        misensorr = admistradorsensoress.getDefaultSensor(Sensor.TYPE_LIGHT);
        Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Whatsapp_Enviar();
            }
        });
        if (misensorr == null) {
            Toast.makeText(lumin.this, "No se detecto el sensor ", Toast.LENGTH_SHORT).show();
            //  finish();
        } else {
            Toast.makeText(lumin.this, "El sensor se a detectado", Toast.LENGTH_SHORT).show();
        }
        //valormax = misensorr.getMaximumRange();
        escuchuadordeEventoss = new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                // if (sensorEvent.values[0] < misensorr.getMaximumRange()); {
                //  etiquetaresultados.setText("Valor de el sensor: " + sensorEvent.values[0]+ "\n Se ha acercado al senser");

/*
                if (sensorEvent.values[0] < misensorr.getMaximumRange()) {
                    etiquetaresultados.setText("Valor de el sensor: " + sensorEvent.values[0] + "\n Se ha acercado al senser");
                    // fondopantalla2.setBackgroundColor(Color.RED);
                } else {
                    etiquetaresultados.setText("Valor de el sensor: " + sensorEvent.values[0] + "\n Se ha alejado del senser");
                    //fondopantalla2.setBackgroundColor(Color.GREEN);
                }*/


                //int newValue = (int) (255f *   / valormax);
                // fondopantalla2.setBackgroundColor(Color.rgb(newValue, newValue, newValue));

                etiquetaresultados.setText("Valor de el sensor: " + sensorEvent.values[0] );
            }
            // getSupportActionBar().setTitle("Luminosidad : " + value + " lx");


            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        inicializarSensor();

        regresar= findViewById(R.id.BTNregresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void inicializarSensor(){
        admistradorsensoress.registerListener(escuchuadordeEventoss,misensorr,SensorManager.SENSOR_DELAY_FASTEST);

    }

    public void  detenerSensor(){
        admistradorsensoress.unregisterListener(escuchuadordeEventoss,misensorr);
    }

    @Override
    protected void onResume() {
        inicializarSensor();
        super.onResume();
    }

    @Override
    protected void onPause() {
        detenerSensor();
        super.onPause();
    }
    public void Whatsapp_Enviar(){
        Intent enviar = new Intent();
        enviar.setAction(Intent.ACTION_SEND);
        enviar.putExtra(Intent.EXTRA_TEXT, "El valor del sensor de luminosidad es: " + etiquetaresultados);
        enviar.setType("text/plain");
        enviar.setPackage("com.whatsapp");
        startActivity(enviar);
    }
}