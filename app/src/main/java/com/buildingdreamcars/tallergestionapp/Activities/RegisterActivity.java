package com.buildingdreamcars.tallergestionapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.buildingdreamcars.tallergestionapp.Objects.Usuario;
import com.buildingdreamcars.tallergestionapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {
    EditText et_nombre,et_mail,et_confirmmail,et_pass,et_confirmpass;
    private FirebaseAuth mAuth;
    FirebaseDatabase db;
    ProgressDialog progressDialog;
    Button btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_nombre=findViewById(R.id.et_nombre);
        et_mail=findViewById(R.id.et_mail);
        et_confirmmail=findViewById(R.id.et_confirmmail);
        et_pass=findViewById(R.id.et_pass);
        et_confirmpass=findViewById(R.id.et_confirmpass);
        btn_register=findViewById(R.id.btn_regis);
        mAuth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveUser(view);
            }
        });
    }
    public void SaveUser(View view) {
        final String nombre, mail, confirmmail, pass, confirmpass;
        nombre = et_nombre.getText().toString();
        mail = et_mail.getText().toString();
        confirmmail = et_confirmmail.getText().toString();
        pass = et_pass.getText().toString();
        confirmpass = et_confirmpass.getText().toString();
        if (nombre.isEmpty() || mail.isEmpty() || confirmmail.isEmpty() || pass.isEmpty() || confirmpass.isEmpty()) {
            if (pass.length() < 6) {
                Toast.makeText(this, "La contraseña debe contener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Por favor rellena todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            if (!mail.equals(confirmmail)) {
                Toast.makeText(this, "Los correos deben ser iguales", Toast.LENGTH_SHORT).show();
            } else if (!pass.equals(confirmpass)) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            } else {
                progressDialog.setMessage("Conectando a Firebase...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                mAuth.createUserWithEmailAndPassword(mail, pass)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Usuario user = new Usuario();
                                    user.setUid(UUID.randomUUID().toString());
                                    user.setNombre(nombre);
                                    user.setMail(mail);
                                    user.setPass(pass);
                                    user.setPts(0);
                                    DatabaseReference ref = db.getReference("USERS");
                                    ref.push().setValue(user);
                                    et_nombre.setText("");
                                    et_mail.setText("");
                                    et_confirmmail.setText("");
                                    et_pass.setText("");
                                    et_confirmpass.setText("");
                                    Toast.makeText(getApplicationContext(), "Registro correcto", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    Bundle datos = new Bundle();
                                    datos.putString("Mail", mail);
                                    datos.putString("Pass", pass);
                                    intent.putExtras(datos);
                                    startActivity(intent);
                                } else {
                                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                        Toast.makeText(getApplicationContext(), "El usuario ya existe", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Ha habido un error", Toast.LENGTH_SHORT).show();

                                    }
                                }
                                progressDialog.dismiss();
                            }
                        });
            }
        }
    }
}