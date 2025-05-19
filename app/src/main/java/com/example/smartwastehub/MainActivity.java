package com.example.smartwastehub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Detect click anywhere
        View mainLayout = findViewById(android.R.id.content);
        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open LoginActivity
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

                // Add fade animation
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                finish(); // Optional: close MainActivity
            }
        });
    }
}
