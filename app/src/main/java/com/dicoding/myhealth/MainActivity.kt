package com.dicoding.myhealth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create an Intent to start HomeActivity
        val intent = Intent(this, HomeActivity::class.java)

        // Start HomeActivity
        startActivity(intent)

        // Finish MainActivity so that it's not in the back stack
        finish()
    }
}
