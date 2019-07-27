package com.pst.SuperShop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class Registro extends AppCompatActivity {

    EditText etusuario, etnombre, etapellido, ettelefono, etcontraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etusuario=(EditText)findViewById(R.id.editusuario);
        etnombre = (EditText)findViewById(R.id.editnombre);
        etapellido=(EditText)findViewById(R.id.editapellido);
        ettelefono = (EditText)findViewById(R.id.edittelefono);
        etcontraseña=(EditText)findViewById(R.id.editcontraseña);

    }





}
