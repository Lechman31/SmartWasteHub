package com.example.smartwastehub;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private EditText usernameInput, emailInput, passwordInput, confirmPasswordInput;
    private ImageView eyeIconPassword, eyeIconConfirmPassword, backArrow;
    private Spinner maritalStatusSpinner;
    private TextView signInLink;

    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;

    private ProgressDialog progressDialog; // Loading dialog

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Find views
        usernameInput = findViewById(R.id.usernameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
        eyeIconPassword = findViewById(R.id.eyeIconPassword);
        eyeIconConfirmPassword = findViewById(R.id.eyeIconConfirmPassword);
        maritalStatusSpinner = findViewById(R.id.maritalStatusSpinner);
        Button signupButton = findViewById(R.id.signupButton);
        signInLink = findViewById(R.id.signInLink);
        backArrow = findViewById(R.id.backArrow); // Initialize back arrow

        // Setup Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.marital_status_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maritalStatusSpinner.setAdapter(adapter);

        // Password eye icon click
        eyeIconPassword.setOnClickListener(v -> {
            if (isPasswordVisible) {
                passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                eyeIconPassword.setImageResource(R.drawable.showpass);
            } else {
                passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                eyeIconPassword.setImageResource(R.drawable.showpass_open);
            }
            isPasswordVisible = !isPasswordVisible;
            passwordInput.setSelection(passwordInput.getText().length());
        });

        // Confirm Password eye icon click
        eyeIconConfirmPassword.setOnClickListener(v -> {
            if (isConfirmPasswordVisible) {
                confirmPasswordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                eyeIconConfirmPassword.setImageResource(R.drawable.showpass);
            } else {
                confirmPasswordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                eyeIconConfirmPassword.setImageResource(R.drawable.showpass_open);
            }
            isConfirmPasswordVisible = !isConfirmPasswordVisible;
            confirmPasswordInput.setSelection(confirmPasswordInput.getText().length());
        });

        // Sign Up button click
        signupButton.setOnClickListener(v -> {
            String username = usernameInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString();
            String confirmPassword = confirmPasswordInput.getText().toString();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) ||
                    TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
                Toast.makeText(SignupActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || !email.endsWith("@gmail.com")) {
                Toast.makeText(SignupActivity.this, "Please enter a valid Gmail address", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Show progress dialog
            progressDialog = new ProgressDialog(SignupActivity.this);
            progressDialog.setMessage("Signing up...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            // Delay 1.5 seconds then move to LoginActivity
            new Handler().postDelayed(() -> {
                progressDialog.dismiss();
                Toast.makeText(SignupActivity.this, "Signup Successful. Please log in.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }, 1500);
        });

        // Already have an account? Sign In
        signInLink.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        // Handle back arrow button click
        backArrow.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish(); // Close the current activity and return to the previous one
    }
}
