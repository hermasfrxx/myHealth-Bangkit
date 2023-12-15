package com.dicoding.myhealth.dummydata.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.myhealth.R

class ActivityAdapter (private val activityList: ArrayList<Activity>) : RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>() {
    class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val activityimage : ImageView = itemView.findViewById(R.id.imgactivity)
        val activityName : TextView = itemView.findViewById(R.id.nameactivity)
        val activityKalori : TextView = itemView.findViewById(R.id.kalori1)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.cardview2, parent,false)
        return ActivityViewHolder(view)
    }

    override fun getItemCount() = activityList.size
    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val (image, name,kalori) = activityList[position]
        Glide.with(holder.itemView.context)
            .load(image)
            .into(holder.activityimage)
        holder.activityName.text = name
        holder.activityKalori.text = kalori

    }
}