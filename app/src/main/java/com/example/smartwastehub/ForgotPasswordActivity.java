package com.example.smartwastehub;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailInput, newPasswordInput, confirmNewPasswordInput;
    private Button resetPasswordButton;
    private ImageView backArrow, eyeIconPassword, eyeIconConfirmPassword; // Added Eye icons
    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Initialize views
        emailInput = findViewById(R.id.emailInput);
        newPasswordInput = findViewById(R.id.newPasswordInput);
        confirmNewPasswordInput = findViewById(R.id.confirmNewPasswordInput);
        resetPasswordButton = findViewById(R.id.resetPasswordButton);
        backArrow = findViewById(R.id.backArrow);

        eyeIconPassword = findViewById(R.id.eyeIconPassword); // Initialize eye icons
        eyeIconConfirmPassword = findViewById(R.id.eyeIconConfirmPassword);

        // Assume the email is passed from LoginActivity, and populate it
        String email = getIntent().getStringExtra("userEmail");
        if (email != null) {
            emailInput.setText(email);
        }

        // Password eye icon click
        eyeIconPassword.setOnClickListener(v -> {
            if (isPasswordVisible) {
                newPasswordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                eyeIconPassword.setImageResource(R.drawable.showpass);
            } else {
                newPasswordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                eyeIconPassword.setImageResource(R.drawable.showpass_open);
            }
            isPasswordVisible = !isPasswordVisible;
            newPasswordInput.setSelection(newPasswordInput.getText().length());
        });

        // Confirm Password eye icon click
        eyeIconConfirmPassword.setOnClickListener(v -> {
            if (isConfirmPasswordVisible) {
                confirmNewPasswordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                eyeIconConfirmPassword.setImageResource(R.drawable.showpass);
            } else {
                confirmNewPasswordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                eyeIconConfirmPassword.setImageResource(R.drawable.showpass_open);
            }
            isConfirmPasswordVisible = !isConfirmPasswordVisible;
            confirmNewPasswordInput.setSelection(confirmNewPasswordInput.getText().length());
        });

        // Handle Reset Password Button Click
        resetPasswordButton.setOnClickListener(v -> {
            String newPassword = newPasswordInput.getText().toString().trim();
            String confirmPassword = confirmNewPasswordInput.getText().toString().trim();

            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(ForgotPasswordActivity.this, "Please fill in both fields.", Toast.LENGTH_SHORT).show();
            } else if (!newPassword.equals(confirmPassword)) {
                Toast.makeText(ForgotPasswordActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ForgotPasswordActivity.this, "Password reset successfully!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Handle Back Arrow Click
        backArrow.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish(); // Close this activity
    }
}
