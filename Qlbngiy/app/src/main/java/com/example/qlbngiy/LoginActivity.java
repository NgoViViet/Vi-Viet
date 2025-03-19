package com.example.qlbngiy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText edtEmail, edtPassword;
    private CheckBox chkRemember;
    private Button btnLogin;
    private TextView txtRegister, txtForgotPassword;
    private UserDAO userDAO;

    // SharedPreferences để lưu thông tin tài khoản khi "lưu tài khoản"
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "userPref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_REMEMBER = "remember";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        chkRemember = findViewById(R.id.chkRemember);
        btnLogin = findViewById(R.id.btnLogin);
        txtRegister = findViewById(R.id.txtRegister);
        txtForgotPassword = findViewById(R.id.txtForgotPassword);

        userDAO = new UserDAO(this);
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Nếu đã lưu tài khoản, load lên giao diện
        if (sharedPreferences.getBoolean(KEY_REMEMBER, false)) {
            edtEmail.setText(sharedPreferences.getString(KEY_EMAIL, ""));
            edtPassword.setText(sharedPreferences.getString(KEY_PASSWORD, ""));
            chkRemember.setChecked(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                // Kiểm tra đăng nhập từ database
                if (userDAO.loginUser(email, password)) {
                    // Lưu tài khoản nếu checkbox được tích
                    if (chkRemember.isChecked()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(KEY_EMAIL, email);
                        editor.putString(KEY_PASSWORD, password);
                        editor.putBoolean(KEY_REMEMBER, true);
                        editor.apply();
                    } else {
                        // Nếu không tích, xóa dữ liệu lưu trữ
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.apply();
                    }

                    // Đăng nhập thành công -> chuyển sang MainActivity
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Sai email hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Chuyển sang màn hình Đăng ký
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        // Chuyển sang màn hình Quên mật khẩu
        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });
    }
}
