package com.arbolB.arbolexpresiones;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainAct extends AppCompatActivity {

    // Button btnIniciar;
    EditText expresion;
    Button btncalcular;
    EditText ediTvalorn;
    TextView resultado;
    ArbolExpresionesClass a = new ArbolExpresionesClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generador_exp);

        ediTvalorn = (EditText) findViewById(R.id.ediT_valor_n);
        expresion = (EditText) findViewById(R.id.EdTexpresion);
        btncalcular = (Button) findViewById(R.id.btnCalcular);
        resultado = (TextView) findViewById(R.id.Textview_resultado);

        btncalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                a.Arbol(a.AcomodarExpresion(expresion.getText().toString(), ediTvalorn.getText().toString()));
                double resp = a.Resultado();
                resultado.setText(String.valueOf(resp));


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnadd:
                Toast.makeText(getBaseContext(), "Diagramas", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
