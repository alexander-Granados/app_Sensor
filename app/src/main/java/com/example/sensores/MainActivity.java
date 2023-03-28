package com.example.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnaproxi, btnlumino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        btnaproxi = findViewById(R.id.proximidad);
        btnlumino = findViewById(R.id.luminosidad);



        btnaproxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, proxi.class);
                startActivity(intent);

            }

        });

        btnlumino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, lumin.class);
                startActivity(intent);
            }
        });


    }
}