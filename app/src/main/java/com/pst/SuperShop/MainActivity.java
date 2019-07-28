package com.pst.SuperShop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText tusuario, tcontraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tusuario=(EditText)findViewById(R.id.l_usuario);
        tcontraseña=(EditText)findViewById(R.id.l_contraseña);
    }

    public void ingresoCliente(View view) {
        Intent i = new Intent(this, cliente.class );
        startActivity(i);
    }

    public void FRegistro(View view) {
        Intent i = new Intent(this, Registro.class );
        startActivity(i);
    }

}
