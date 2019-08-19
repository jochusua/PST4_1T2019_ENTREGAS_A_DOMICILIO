package com.pst.SuperShop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.pst.SuperShop.uiModels.shoppingCart;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText tusuario, tcontrasenia;
    Button btnRegistrar, btnLogin;
    CheckBox checkBox;

    //Declaramos un objeto firebaseAuth
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tusuario=(EditText)findViewById(R.id.l_usuario);
        tcontrasenia =(EditText)findViewById(R.id.l_contraseña);
        checkBox=(CheckBox)findViewById(R.id.checkBox);

        btnLogin = (Button) findViewById(R.id.blogin);


        //inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //asociamos un oyente al evento clic del botón
        btnLogin.setOnClickListener(this);

        //prueba
    }


    private void loguearUsuario() {
        final String email = tusuario.getText().toString().trim();
        String password = tcontrasenia.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if (TextUtils.isEmpty(email)) {//(precio.equals(""))
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        //loguear usuario
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            int pos = email.indexOf("@");
                            String user = email.substring(0, pos);
                            if(checkBox.isChecked()){
                                Intent i = new Intent(getApplication(), repartidor.class);
                                startActivity(i);
                            }else {
                                Intent i = new Intent(getApplication(), DrawerCliente.class);
                                startActivity(i);
                            }
                            Intent i= new Intent(getApplication(), DrawerCliente.class );
                            enviar_info();
                            startActivity(i);

                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                                Toast.makeText(MainActivity.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "No se pudo ingresar con ese usuario ", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    public void enviar_info(){
        Intent i= new Intent(this, shoppingCart.class);
        i.putExtra("dato2",tusuario.getText().toString());
    }


    @Override
    public void onClick(View view) {
        loguearUsuario();

    }

    public void FRegistro(View view) {
        Intent i = new Intent(this, Registro.class );
        startActivity(i);
    }


}
