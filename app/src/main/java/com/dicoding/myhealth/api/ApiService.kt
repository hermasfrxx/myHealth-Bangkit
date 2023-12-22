package com.dicoding.myhealth.API



import com.dicoding.myhealth.Response.GetAllStoriesResponse
import com.dicoding.myhealth.Response.LoginResponse
import com.dicoding.myhealth.Response.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

        @FormUrlEncoded
        @POST("register")
        fun register(
            @Field("name") name: String,
            @Field("email") email: String,
            @Field("password") password: String
        ): Call<RegisterResponse>

        @FormUrlEncoded
        @POST("login")
        fun login(
            @Field("email") email: String,
            @Field("password") password: String
        ): Call<LoginResponse>
    @GET("stories")
    fun getAllStories(
    ): Call<GetAllStoriesResponse>

}