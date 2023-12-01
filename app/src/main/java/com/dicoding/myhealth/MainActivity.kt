package com.dicoding.myhealth

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.myhealth.API.ApiConfig
import com.dicoding.myhealth.Activity.LoginActivity

import com.dicoding.myhealth.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        token = sharedPreferences.getString("token", null).toString()

        if(sharedPreferences.getString("token", null) == null){
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }
        val btnLogin: Button = findViewById(R.id.btn_login)
        btnLogin.setOnClickListener(this)
    }
    override fun onResume() {
        getData()
        super.onResume()
    }

    private fun getData(){
        lifecycleScope.launch {
            val client = ApiConfig.getApiWithTokenService(token).getAllStories()
            binding.loading.visibility = View.VISIBLE



            }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> {
                val moveIntent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(moveIntent)
            }
        }
    }
}