package com.example.smartwasteapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content view to the Points Summary layout as the main screen
        setContentView(R.layout.points_history)
    }
}
