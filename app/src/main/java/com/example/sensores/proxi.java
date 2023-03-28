package com.example.sensores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class proxi extends AppCompatActivity {

    Sensor misensor;
    SensorManager admistradorsensores;
    SensorEventListener escuchuadordeEventos;

    TextView etiquetaresultado;
    Button regresar;
    ConstraintLayout fondopantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxi);

        setContentView(R.layout.activity_proxi);

        fondopantalla = findViewById(R.id.fonpantalla);

        etiquetaresultado = findViewById(R.id.TXVresultado);

        admistradorsensores = (SensorManager) getSystemService(SENSOR_SERVICE);

        misensor = admistradorsensores.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (misensor == null) {
            Toast.makeText(proxi.this, "No se detecto el sensor ", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(proxi.this, "El sensor se a detectado", Toast.LENGTH_SHORT).show();

            escuchuadordeEventos = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {


                    if (sensorEvent.values[0] < misensor.getMaximumRange()) {
                        etiquetaresultado.setText("Valor de el sensor: " + sensorEvent.values[0] + "\n Se ha acercado al senser");
                        fondopantalla.setBackgroundColor(Color.RED);
                    } else {
                        etiquetaresultado.setText("Valor de el sensor: " + sensorEvent.values[0] + "\n Se ha alejado del senser");
                        fondopantalla.setBackgroundColor(Color.GREEN);
                    }

                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };
            inicializarSensor();

            regresar = findViewById(R.id.BTNregresar);
            regresar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

        }
    }
    public void inicializarSensor() {
        admistradorsensores.registerListener(escuchuadordeEventos, misensor, (2000 * 1000));

    }
    public void detenerSensor() {
        admistradorsensores.unregisterListener(escuchuadordeEventos, misensor);
    }
    @Override
    protected void onResume () {
        inicializarSensor();
        super.onResume();
    }

    @Override
    protected void onPause () {
        detenerSensor();
        super.onPause();
    }
}
