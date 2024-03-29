package com.pst.SuperShop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pst.SuperShop.models.Usuario;

import java.util.UUID;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    // Permite al ususario agregar su nombre, apellido , telefono y contrasena
    EditText etusuario, etnombre, etapellido, ettelefono, etcontraseña;
    Button btnRegistrar;

    //Declaramos un objeto firebaseAuth
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //inicialización de las variables
        etusuario=(EditText)findViewById(R.id.editusuario);
        etnombre = (EditText)findViewById(R.id.editnombre);
        etapellido=(EditText)findViewById(R.id.editapellido);
        ettelefono = (EditText)findViewById(R.id.edittelefono);
        etcontraseña=(EditText)findViewById(R.id.editcontraseña);

        btnRegistrar =(Button)findViewById(R.id.registrar);

        //inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //attaching listener to button
        btnRegistrar.setOnClickListener(this);
    }



    private void Registrar(){

        //Obtenemos el email y la contraseña desde las cajas de texto
        String email = etusuario.getText().toString().trim();
        String password  = etcontraseña.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Falta ingresar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }



        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            Escritura();
                            Toast.makeText(Registro.this,"Se ha registrado el usuario con el email: "+ etusuario.getText(),Toast.LENGTH_LONG).show();
                        }else{

                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                                Toast.makeText(Registro.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Registro.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                });

    }


    public void Escritura(){
        DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
        Usuario usuario = new Usuario();
        usuario.setIdentificacion(etusuario.getText().toString());
        usuario.setNombre(etnombre.getText().toString());
        usuario.setApellido(etapellido.getText().toString());
        double celular = Integer.valueOf(ettelefono.getText().toString());
        usuario.setTelefono(celular);
        referencia.child("usuarios").child(UUID.randomUUID().toString()).setValue(usuario);
        Toast.makeText(this,"El registro fue exitoso",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onClick(View view) {
        //Invocamos al método:
        Registrar();

    }


}
