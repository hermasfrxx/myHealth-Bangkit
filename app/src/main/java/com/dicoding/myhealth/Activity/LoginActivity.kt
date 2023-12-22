package com.dicoding.myhealth.Activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.dicoding.myhealth.API.ApiConfig
import com.dicoding.myhealth.MainActivity
import com.dicoding.myhealth.Response.LoginResponse
import com.dicoding.myhealth.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        binding.registerTextview.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        binding.loginBtn.setOnClickListener {
            if(binding.edLoginEmail.text?.isNotEmpty() == true && Patterns.EMAIL_ADDRESS.matcher(binding.edLoginEmail.text).matches() &&
                binding.edLoginPassword.text?.isNotEmpty() == true && binding.edLoginPassword.text!!.length >= 8){
                binding.loading.visibility = View.VISIBLE
                lifecycleScope.launch {
                    val client = ApiConfig.getApiService().login(
                        email = binding.edLoginEmail.text.toString(),
                        password = binding.edLoginPassword.text.toString()
                    )

                    client.enqueue(object: Callback<LoginResponse>{
                        override fun onResponse(
                            call: Call<LoginResponse>,
                            response: Response<LoginResponse>
                        ) {
                            val res = response.body()
                            if(response.isSuccessful && res != null){
                                sharedPreferences
                                    .edit()
                                    .putString("token", res.loginResult?.token)
                                    .apply()

                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                finish()
                            }else{
                                Toast.makeText(this@LoginActivity, response.message(), Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            TODO("Not yet implemented")
                        }
                    })
                }
            }
        }
    }
}