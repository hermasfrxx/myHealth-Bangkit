package com.dicoding.myhealth

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.myhealth.databinding.ActivityHomeBinding
import com.dicoding.myhealth.dummydata.activity.Activity
import com.dicoding.myhealth.dummydata.activity.ActivityAdapter
import com.dicoding.myhealth.dummydata.foods.Food
import com.dicoding.myhealth.dummydata.foods.FoodAdapter
import com.dicoding.myhealth.widget.SpaceItemDecoration

@Suppress("DEPRECATION")
class HomeActivity : AppCompatActivity() {
    private lateinit var rv_foods: RecyclerView
    private lateinit var rv_activity: RecyclerView
    private var Foodlist = ArrayList<Food>()
    private var Activitylist = ArrayList<Activity>()
    private lateinit var binding: ActivityHomeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rv_foods = findViewById(R.id.rv_foods)
        Foodlist.addAll(getListFoods())
        showRecyclerList()

        rv_activity = findViewById(R.id.rv_activity)
        Activitylist.addAll(getListActivity())
        showRecyclerListActivity()



    }
    private fun getListFoods(): ArrayList<Food>{
        val name = resources.getStringArray(R.array.food_name)
        val kalori = resources.getStringArray(R.array.food_kalori)
        val lemak = resources.getStringArray(R.array.food_lemak)
        val protein = resources.getStringArray(R.array.food_protein)
        val image = resources.getStringArray(R.array.image_foods)
        val listfoods = ArrayList<Food>()
        for (i in name.indices){
            val food = Food(image[i], name[i], kalori[i], lemak[i], protein[i])
            listfoods.add(food)
        }
        return listfoods
    }
    private fun getListActivity() : ArrayList<Activity>{
        val activityname = resources.getStringArray(R.array.activity_name)
        val activitykalori = resources.getStringArray(R.array.activity_kalori)
        val image = resources.getStringArray(R.array.activity_img)
        val listactivity = ArrayList<Activity>()
        for (i in activityname.indices){
            val activity = Activity(image[i], activityname[i], activitykalori[i])
            listactivity.add(activity)
        }
        return listactivity
    }
    private fun showRecyclerList() {
        // Use LinearLayoutManager with HORIZONTAL orientation
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_foods.layoutManager = layoutManager
        val foodAdapter = FoodAdapter(Foodlist)
        rv_foods.adapter = foodAdapter

        val spaceBetweenItems = resources.getDimensionPixelSize(R.dimen.corner_radius)
        rv_foods.addItemDecoration(SpaceItemDecoration(spaceBetweenItems))
    }
    private fun showRecyclerListActivity() {
        // Use LinearLayoutManager with HORIZONTAL orientation
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_activity.layoutManager = layoutManager
        val activityAdapter = ActivityAdapter(Activitylist)
        rv_activity.adapter = activityAdapter

        val spaceBetweenItems = resources.getDimensionPixelSize(R.dimen.corner_radius)
        rv_activity.addItemDecoration(SpaceItemDecoration(spaceBetweenItems))
    }

//        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
//        token = sharedPreferences.getString("token", null).toString()
//        binding.logot.setOnClickListener{
//            sharedPreferences.edit().clear().apply()
//            startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
//            finish()
//        }
//        Log.e("logintoken",token)
//        if(sharedPreferences.getString("token", null) == null){
//            Log.d("Homeoperoper","oper")
//            startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
//            finish()
//        }
//    }
    }
