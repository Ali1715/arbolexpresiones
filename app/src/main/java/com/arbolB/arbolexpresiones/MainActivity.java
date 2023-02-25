package com.arbolB.arbolexpresiones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button bntIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bntIniciar=(Button) findViewById(R.id.button);

        bntIniciar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            Intent intent=new Intent(MainActivity.this,MainAct.class);
            startActivity(intent);
            }

        });

    }




    }// TextView mostrador =findViewById(R.id.mostradorPantalla);
