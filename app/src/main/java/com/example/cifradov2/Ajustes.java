package com.example.cifradov2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

public class Ajustes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        //Boton switch
        final Switch btnStch = findViewById(R.id.swichBtn);

        //Botones radioButtons
        final RadioButton btnCesar = findViewById(R.id.btnCesar);
        final RadioButton btnPares = findViewById(R.id.btnPares);
        final RadioButton btnClave = findViewById(R.id.btnClave);

        //Tengo que cambiar el estado de los botones porque cuando se cierra se destruye esta actividad
        if(MainActivity.estadoCesar == true){
            btnCesar.setChecked(true);
            btnClave.setChecked(false);
            btnPares.setChecked(false);
        }
        if(MainActivity.estadoPares == true){
            btnCesar.setChecked(false);
            btnClave.setChecked(false);
            btnPares.setChecked(true);
        }
        if(MainActivity.estadoClave == true){
            btnCesar.setChecked(false);
            btnClave.setChecked(true);
            btnPares.setChecked(false);
        }

        //Verifico el estado del switch
        if(MainActivity.estadoAlEscribir == true){
            btnStch.setChecked(true);
        }

        //Eventos que controlan los evntos del tipo de cifrado que vamos usar
        //Cifrado Cesar
        btnCesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnCesar.isChecked() == true){
                    MainActivity.estadoCesar = true;
                    MainActivity.estadoClave = false;
                    MainActivity.estadoPares = false;
                    MainActivity.txtTitulo.setText("Cifrado Cesar");
                }
            }
        });

        //Cifrado por Pares
        btnPares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnPares.isChecked()){
                    MainActivity.estadoCesar = false;
                    MainActivity.estadoClave = false;
                    MainActivity.estadoPares = true;
                    MainActivity.txtTitulo.setText("Cifrado por Pares");
                }
            }
        });

        //Cifrado por Contraseña
        btnClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnClave.isChecked()) {
                    MainActivity.estadoCesar = false;
                    MainActivity.estadoClave = true;
                    MainActivity.estadoPares = false;
                    MainActivity.txtTitulo.setText("Cifrado por Clave");
                    Toast.makeText(Ajustes.this, "Palabra clave GATO", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //Evento que controla el texto del main
        btnStch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnStch.isChecked()){
                    MainActivity.estadoAlEscribir = true;
                    MainActivity.txtEstado.setText("Cifrar al escribir");
                }else {
                    MainActivity.estadoAlEscribir = false;
                    MainActivity.txtEstado.setText("Cifrar al pulsar los botones");
                }
            }
        });
    }
}