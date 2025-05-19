package com.example.smartwastehub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private ImageView eyeIcon;
    private Button signInButton;
    private TextView signUpText, forgotPasswordText;

    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Find views
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        eyeIcon = findViewById(R.id.eyeIcon);
        signInButton = findViewById(R.id.signInButton);
        signUpText = findViewById(R.id.signUpText);
        forgotPasswordText = findViewById(R.id.forgotPassword);

        // Toggle password visibility
        eyeIcon.setOnClickListener(v -> {
            if (isPasswordVisible) {
                passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                eyeIcon.setImageResource(R.drawable.showpass);
            } else {
                passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                eyeIcon.setImageResource(R.drawable.showpass_open);
            }
            isPasswordVisible = !isPasswordVisible;
            passwordInput.setSelection(passwordInput.getText().length());
        });

        // Set "Sign Up" text color
        String fullText = "Don't have an account? Sign Up";
        SpannableString spannableString = new SpannableString(fullText);
        int signUpStart = fullText.indexOf("Sign Up");
        int signUpEnd = signUpStart + "Sign Up".length();

        spannableString.setSpan(
                new ForegroundColorSpan(Color.parseColor("#ADFF2F")),
                signUpStart,
                signUpEnd,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        signUpText.setText(spannableString);

        // Sign Up click listener
        signUpText.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        // Forgot Password click listener
        forgotPasswordText.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            intent.putExtra("userEmail", email);
            startActivity(intent);
        });

        signInButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (email.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
            } else if (!isValidGmail(email)) {
                Toast.makeText(LoginActivity.this, "Please enter a valid Gmail address (example@gmail.com)", Toast.LENGTH_SHORT).show();
            } else if (password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
            } else {
                // Save login state
                SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                prefs.edit().putBoolean("isLoggedIn", true)
                        .putString("userEmail", email)
                        .apply();

                // Extract username from email
                String username = email.split("@")[0];

                // Go to HomeActivity with username
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
            }
        });

    }

    private boolean isValidGmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.endsWith("@gmail.com");
    }
}
