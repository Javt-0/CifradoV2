package com.example.cifradov2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static Boolean estadoAlEscribir = true;
    @SuppressLint("StaticFieldLeak")
    public static TextView txtEstado = null;
    public static TextView txtTitulo = null;

    //Variables que cambian en Ajustes
    public static Boolean estadoCesar = true;
    public static Boolean estadoPares = false;
    public static Boolean estadoClave = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Boton para cambiar de actividad
        final Button btnAjustes = findViewById(R.id.btnAjustes);

        //Textos de cifrado
        EditText txtArriba = findViewById(R.id.txtCifrado);
        EditText txtAbajo = findViewById(R.id.txtDescifrado);

        //Texto que cambia en el main
        txtEstado = findViewById(R.id.txtAlEscribir);
        txtTitulo = findViewById(R.id.txtTitulo);

        //Botones de cifrado
        Button btnCifrar = findViewById(R.id.btnCifrar);
        Button btnDescifrar = findViewById(R.id.btnDescifrado);

        //Eventos al modificar los textos
        txtArriba.addTextChangedListener(new TextWatcher() {

             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void afterTextChanged(Editable editable) {
                 if (editable.hashCode() == txtArriba.getEditableText().hashCode() && txtArriba.hasFocus()) {
                     txtAbajo.setText(editable.toString());
                     if (estadoAlEscribir == true) {
                         if (estadoCesar == true) {
                             txtAbajo.setText(cifrarCesar(txtArriba.getText().toString()));
                         } else if (estadoPares == true) {
                             txtAbajo.setText(cifrarPares(txtArriba.getText().toString()));
                         } else if (estadoClave == true) {
                             txtAbajo.setText(cifrarClave(txtArriba.getText().toString()));
                         }
                     }
                 }
             }
        });

        txtAbajo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.hashCode() == txtAbajo.getEditableText().hashCode() && txtAbajo.hasFocus()){
                    txtArriba.setText(editable.toString());
                    if (estadoAlEscribir == true) {
                        if (estadoCesar == true) {
                            txtArriba.setText(descifrarCesar(txtAbajo.getText().toString()));
                        } else if (estadoPares == true) {
                            txtArriba.setText(cifrarPares(txtAbajo.getText().toString()));
                        } else if (estadoClave == true) {
                            txtArriba.setText(descifrarClave(txtAbajo.getText().toString()));
                        }
                    }
                }

            }
        });

        //Evento para cambiar de activity
        btnAjustes.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intento = new Intent(MainActivity.this, Ajustes.class);
                startActivity(intento);
                return false;
            }
        });

        //Eventos al pulsar botones
        btnCifrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (estadoAlEscribir == false) {
                    if (estadoCesar == true) {
                        txtAbajo.setText(cifrarCesar(txtArriba.getText().toString()));
                    } else if (estadoPares == true) {
                        if (txtAbajo.getText().toString().equalsIgnoreCase(txtArriba.getText().toString()) == true){
                            txtAbajo.setText(cifrarPares(txtArriba.getText().toString()));
                        }else{
                            txtAbajo.setText(txtAbajo.getText().toString());
                        }
                    } else if (estadoClave == true) {
                        txtAbajo.setText(cifrarClave(txtArriba.getText().toString()));
                    }
                }
            }
        });

        btnDescifrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (estadoAlEscribir == false) {
                    if (estadoCesar == true) {
                        txtArriba.setText(descifrarCesar(txtAbajo.getText().toString()));
                    } else if (estadoPares == true) {
                        if (txtAbajo.getText().toString().equalsIgnoreCase(txtArriba.getText().toString()) == true){
                            txtArriba.setText(descifrarPares(txtAbajo.getText().toString()));
                        }else{
                            txtArriba.setText(txtArriba.getText().toString());
                        }

                    } else if (estadoClave == true) {
                        txtArriba.setText(descifrarClave(txtAbajo.getText().toString()));
                    }
                }
            }
        });

    }

    private String cifrarCesar(String txt){
        final int indice = 3;
        String textoOriginal = txt;
        StringBuilder cifrado = new StringBuilder();

        for (int i = 0; i < textoOriginal.length(); i++) {
            char caracterActual = textoOriginal.charAt(i);

            if (caracterActual >= 32 && caracterActual <= 126) {
                int nuevoCodigoASCII = caracterActual + indice;
                if (nuevoCodigoASCII > 126) {
                    nuevoCodigoASCII = (nuevoCodigoASCII - 126) + 32 - 1;
                }
                cifrado.append((char) nuevoCodigoASCII);
            } else {
                cifrado.append(caracterActual);
            }
        }
        return cifrado.toString();
    }

    private String descifrarCesar(String txt){
        final int indice = 3;
        String textoCifrado = txt;
        StringBuilder descifrado = new StringBuilder();

        for (int i = 0; i < textoCifrado.length(); i++) {
            char caracterActual = textoCifrado.charAt(i);

            if (caracterActual >= 32 && caracterActual <= 126) {
                int nuevoCodigoASCII = caracterActual - indice;
                if (nuevoCodigoASCII < 32) {
                    nuevoCodigoASCII = 126 - (32 - nuevoCodigoASCII - 1);
                }
                descifrado.append((char) nuevoCodigoASCII);
            } else {
                descifrado.append(caracterActual);
            }
        }
        return descifrado.toString();
    }

    private String cifrarPares(String txt){
        String texto = txt;

        // Creamos un arreglo con los caracteres del texto
        char[] caracteres = texto.toCharArray();

        // Recorremos el arreglo de caracteres
        for (int i = 0; i < caracteres.length; i++) {
            // Si la posición es par
            if (i % 2 == 0) {
                // Cambiamos el caracter por el que está en la posición siguiente
                if (i + 1 < caracteres.length) {
                    char temp = caracteres[i];
                    caracteres[i] = caracteres[i + 1];
                    caracteres[i + 1] = temp;
                }
            }
        }
        return new String(caracteres);
    }

    private String descifrarPares(String txt){
        String texto = txt;

        // Creamos un arreglo con los caracteres del texto
        char[] caracteres = texto.toCharArray();

        // Recorremos el arreglo de caracteres
        for (int i = 0; i < caracteres.length; i++) {
            // Si la posición es par
            if (i % 2 == 0) {
                // Cambiamos el caracter por el que está en la posición siguiente
                if (i + 1 < caracteres.length) {
                    char temp = caracteres[i];
                    caracteres[i] = caracteres[i + 1];
                    caracteres[i + 1] = temp;
                }
            }
        }
        return new String(caracteres);
    }

    private String cifrarClave(String txt){
        String texto1 = txt;
        String texto2 = "GATO";
        int codigoNuevo = 0;
        int codigo;
        String letra2 = "";
        String textoCifrado = "";
        // Se obtiene la diferencia entre las longitudes de los textos
        int diferencia = texto1.length() - texto2.length();
        int contador = 0;
        // Si la longitud de texto2 es menor, se rellena hasta igualar la longitud de texto1
        if (diferencia > 0) {
            String aux = "";
            for (int i = 0; i < diferencia; i++) {
                char letra = texto2.charAt(contador);
                aux += letra;
                contador ++;
                if(contador == 4){
                    contador = 0;
                }
            }
            texto2 += aux;
        }

        for (int i = 0; i < texto1.length(); i++) {
            codigo = (int)texto1.charAt(i);
            int codigo2 = (int)texto2.charAt(i);
            if(codigo >=32 || codigo <=126){
                codigoNuevo = (codigo + codigo2) - 127;
                if(codigoNuevo <= 0){
                    codigoNuevo = codigoNuevo + 95;
                }
                letra2 = Character.toString((char) codigoNuevo);
                textoCifrado += letra2;
            }

        }
        System.out.println("Codigo nuevo; " + textoCifrado );
        return textoCifrado;
    }

    private String descifrarClave(String txt){
        String texto1 = txt;
        String texto2 = "GATO";
        int codigoNuevo = 0;
        int codigo;
        String letra2 = "";
        String textoCifrado = "";
        // Se obtiene la diferencia entre las longitudes de los textos
        int diferencia = texto1.length() - texto2.length();
        int contador = 0;
        // Si la longitud de texto2 es menor, se rellena hasta igualar la longitud de texto1
        if (diferencia > 0) {
            String aux = "";
            for (int i = 0; i < diferencia; i++) {
                char letra = texto2.charAt(contador);
                aux += letra;
                contador ++;
                if(contador == 4){
                    contador = 0;
                }
            }
            texto2 += aux;
        }

        for (int i = 0; i < texto1.length(); i++) {
            codigo = (int)texto1.charAt(i);
            int codigo2 = (int)texto2.charAt(i);
            if(codigo >=32 || codigo <=126){
                codigoNuevo = (codigo - codigo2) + 127;
                if(codigoNuevo <= 0){
                    codigoNuevo = codigoNuevo - 95;
                }
                letra2 = Character.toString((char) codigoNuevo);
                textoCifrado += letra2;
            }

        }
        System.out.println("Codigo nuevo; " + textoCifrado );
        return textoCifrado;
    }
}