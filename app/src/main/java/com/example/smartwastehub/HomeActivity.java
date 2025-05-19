package com.example.smartwastehub;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.MediaController;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView welcomeText;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcomeText = findViewById(R.id.welcomeText);
        videoView = findViewById(R.id.videoView2);


        // Retrieve username from intent
        String username = getIntent().getStringExtra("username");
        if (username != null) {
            welcomeText.setText("Welcome, " + username + "!");
        }

        // Set up the video
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid1);
        videoView.setVideoURI(videoUri);

        // Optional: Add media controls to the video
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        // Auto-start the video
        videoView.start();
    }
}
