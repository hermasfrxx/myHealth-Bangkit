package com.dicoding.myhealth.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.dicoding.myhealth.API.ApiConfig
import com.dicoding.myhealth.Response.RegisterResponse

import com.dicoding.myhealth.databinding.ActivityRegisterBinding
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginTextview.setOnClickListener { finish() }
        binding.registerBtn.setOnClickListener {
            if(binding.edRegisterName.text?.isNotEmpty() == true && binding.edRegisterEmail.text?.isNotEmpty() == true && binding.edRegisterEmail.text?.let { it1 ->
                    Patterns.EMAIL_ADDRESS.matcher(
                        it1
                    ).matches()
                }!! &&
                binding.edRegisterPassword.text?.isNotEmpty() == true && binding.edRegisterPassword.text!!.length >= 8){

                binding.loading.visibility = View.VISIBLE
                lifecycleScope.launch {
                    val client = ApiConfig.getApiService().register(
                        name = binding.edRegisterName.text.toString(),
                        email = binding.edRegisterEmail.text.toString(),
                        password = binding.edRegisterPassword.text.toString()
                    )

                    client.enqueue(object: Callback<RegisterResponse>{
                        override fun onResponse(
                            call: Call<RegisterResponse>,
                            response: Response<RegisterResponse>
                        ) {
                            val res = response.body()
                            if(response.isSuccessful && res != null){
                                finish()
                            }else{
                                Toast.makeText(this@RegisterActivity, response.message(), Toast.LENGTH_SHORT).show()
                            }

                            binding.loading.visibility = View.GONE
                        }

                        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                            Toast.makeText(this@RegisterActivity, "Kesalahan server!", Toast.LENGTH_SHORT).show()
                            binding.loading.visibility = View.GONE
                        }

                    })
                }
            }
        }
    }
}