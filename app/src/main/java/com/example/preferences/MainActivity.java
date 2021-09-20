package com.example.preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences shp;
    SharedPreferences.Editor shpEditor;
    EditText edtUserId, edtPassword;
    Button btnLogin;
    CheckBox btncheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUserId = findViewById(R.id.edtUserid);
        edtPassword = findViewById(R.id.edtPassword);
        edtUserId.setText("");
        btncheck = findViewById(R.id.guardar);
        edtPassword.setText("");
        btnLogin = findViewById(R.id.btnLogin);

        shp = getSharedPreferences("myPreferences", MODE_PRIVATE);
        shpEditor = shp.edit();
        CheckLogin();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUserId.getText().toString().equals("") || edtPassword.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Rellenar los campos plis", Toast.LENGTH_SHORT).show();
                }else if (edtUserId.getText().toString().equals("mario") && edtPassword.getText().toString().equals("123")){
                    login(edtUserId.getText().toString(), edtPassword.getText().toString());
                }else{
                    Toast.makeText(MainActivity.this, "Credenciales no validas :V", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void CheckLogin() {
        if (shp == null){
            shp = getSharedPreferences("myPreferences", MODE_PRIVATE);
        }
        String userName = shp.getString("name", "");
        if (userName != null && !userName.equals("")) {
            Intent i = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(i);
            finish();
        }
    }

    public void login(String userid, String password) {
        try {
            if (btncheck.isChecked()) {
                    shpEditor = shp.edit();
                    shpEditor.putString("name", userid);
                    shpEditor.putString("password", password);
                    shpEditor.commit();
                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(this, "Preferencias guardadas", Toast.LENGTH_SHORT).show();
            } else{
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
                finish();
            }

        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, "Hubo un error :V we repara tu pc ", Toast.LENGTH_SHORT).show();
        }
    }

    //  shpEditor.putString("preferencia1", "Hola estoy guardando esto");
    //                    shpEditor.putString("preferencia2", "y tambien esto");
    //                    shpEditor.commit();

}