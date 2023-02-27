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
    Button generar;

    // Button btnIniciar;
    EditText expresion;
    Button btncalcular;
    EditText ediTvalorn;
    TextView resultado;
    TextView mostrar;
    EditText edtif;
    EditText edtif2;
    TextView edtif3;
    ArbolExpresionesClass a = new ArbolExpresionesClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generador_exp);

        ediTvalorn = (EditText) findViewById(R.id.ediT_valor_n);
        expresion = (EditText) findViewById(R.id.EdTexpresion);
        edtif = (EditText) findViewById(R.id.editText_if);
        edtif2 = (EditText) findViewById(R.id.editText_if2);
        edtif3 = (TextView) findViewById(R.id.textView8);
        btncalcular = (Button) findViewById(R.id.btnCalcular);
        resultado = (TextView) findViewById(R.id.Textview_resultado);
        mostrar = (TextView) findViewById(R.id.textView4);
        generar=(Button) findViewById(R.id.buttongen);

        generar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Python(1,ediTvalorn.getText().toString(),expresion.getText().toString(),edtif.getText().toString(),edtif2.getText().toString(),resultado.getText().toString(),mostrar);
            }

        });

        btncalcular.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                a.Arbol(a.AcomodarExpresion(expresion.getText().toString(), ediTvalorn.getText().toString()));
                double resp = a.Resultado();
                /*for (int i=0; i<=edtif.getText().length();i++){


                        Character ifn=edtif.getText().charAt(i);
                        switch (ifn) {
                            case '>':
                                if(Double.parseDouble(edtif2.getText().toString())<resp)
                                    resultado.setText(String.valueOf(resp));

                                break;
                            case '<':
                                if(Double.parseDouble(edtif2.getText().toString())>resp)
                                    resultado.setText(String.valueOf(resp));

                                    break;
                            case '=':
                                if(resp==Double.parseDouble(edtif2.getText().toString()))
                                    resultado.setText(String.valueOf(resp));

                                break;
                            default:
                                ;
                                break;

                        }

                }*/

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
            case R.id.btncode:
                Toast.makeText(getBaseContext(), "Code", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btnadd:
                Toast.makeText(getBaseContext(), "Code", Toast.LENGTH_SHORT).show();
                break;
              //  startActivity(intent);


        }
        return super.onOptionsItemSelected(item);
    }

    private void Python(int i, String n,String x,String m,String e,String r, TextView textView) {

        switch (i) {

                 case 1:
                textView.append(n + "\n");     //solo muestra los valores de
                      textView.append(x + "\n");
                      textView.append("if " + "c" + m + e + ":"+ "\n");
                     textView.append("print("+r +  ")" +"\n");
                     break;

                 default:
        }
    }


}
