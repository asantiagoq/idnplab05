package com.example.login;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.login.databinding.ActivityMainBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        EditText edtUsername = binding.edtUsername;
        EditText edtPassword = binding.edtPassword;
        Button btnLogin = binding.btnLogin;
        Button btnAddAccount = binding.btnAddAccount;

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtUsername.getText().toString().equals("admin") && edtPassword.getText().toString().equals("admin")){
                    Toast.makeText(getApplicationContext(),"Bienvenido a mi app", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Bienvenido a mi app");
                } else {
                    Toast.makeText(getApplicationContext(),"Error en la autenticacion", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Error en la autenticacion");
                }
            }
        });

        btnAddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                //startActivity(intent);
            }
        });
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>(){
                @Override
                public void onActivityResult(ActivityResult activityResult){
                    Integer resultCode = activityResult.getResultCode();
                    if(resultCode == AccountActivity.ACCOUNT_ACEPTAR){
                        Intent data = activityResult.getData();
                    } else if (resultCode == AccountActivity.ACCOUNT_CANCELAR){
                        Toast.makeText(getApplicationContext(),"Cancelado", Toast.LENGTH_LONG).show();
                    }
                    Intent data = activityResult.getData();
                }
            }
    );
}