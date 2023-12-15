package com.dicoding.myhealth

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dicoding.myhealth.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        token = sharedPreferences.getString("token", null).toString()
        binding.logot.setOnClickListener{
            sharedPreferences.edit().clear().apply()
            startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
            finish()
        }
        Log.e("logintoken",token)
        if(sharedPreferences.getString("token", null) == null){
            Log.d("Homeoperoper","oper")
            startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
            finish()
        }
    }
}
