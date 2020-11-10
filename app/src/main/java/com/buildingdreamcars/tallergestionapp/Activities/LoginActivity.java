package com.buildingdreamcars.tallergestionapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.buildingdreamcars.tallergestionapp.DatosTemp.Preferencias;
import com.buildingdreamcars.tallergestionapp.Objects.Usuario;
import com.buildingdreamcars.tallergestionapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    TextView tvRegistro;
    EditText et_mail,et_pass;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    Button btn_login;
    String mail;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvRegistro=findViewById(R.id.tv_sign_in);
        et_mail=findViewById(R.id.et_mail);
        et_pass=findViewById(R.id.et_pass);
        btn_login=findViewById(R.id.btn_login);


        Bundle datos = getIntent().getExtras();
        if (datos!=null){
            String email = datos.getString("Mail");
            String pass = datos.getString("Pass");
            et_mail.setText(email);
            et_pass.setText(pass);
        }
        mAuth=FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acceder(view);
            }
        });




        tvRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    public void acceder(View view) {
        mail = et_mail.getText().toString();
        final String pass = et_pass.getText().toString();
        if (mail.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Rellena los datos", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.setMessage("Conectando con Firebase");
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(mail, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                cargaruser();
                            } else {
                                Toast.makeText(LoginActivity.this, "No existe usuario, compruebalo", Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        }
                    });

        }
    }
    public void cargaruser() {
        ref = FirebaseDatabase.getInstance().getReference("USERS");
        Query q = ref.orderByChild("mail").equalTo(mail);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot Snapshot : snapshot.getChildren()
                ) {
                    Usuario user = Snapshot.getValue(Usuario.class);
                    Preferencias.setLoged(getApplicationContext(),true);
                    Preferencias.setNickname(getApplicationContext(),user.getNombre());
                    Preferencias.setMail(getApplicationContext(),user.getMail());
                    Preferencias.setPass(getApplicationContext(),user.getPass());
                }

                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}