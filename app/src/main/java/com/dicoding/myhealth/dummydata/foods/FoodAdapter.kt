package com.dicoding.myhealth.dummydata.foods

import android.graphics.Outline
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.myhealth.R

class FoodAdapter(private val foodList: ArrayList<Food>) :RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodimage: ImageView = itemView.findViewById(R.id.imgfood)
        val name: TextView = itemView.findViewById(R.id.namefood)
        val kalori: TextView = itemView.findViewById(R.id.kalori)
        val lemak: TextView = itemView.findViewById(R.id.lemak)
        val protein: TextView = itemView.findViewById(R.id.protein)

        init {
            val radius = itemView.resources.getDimension(R.dimen.corner_radius)
            foodimage.clipToOutline = true
            foodimage.outlineProvider = object : ViewOutlineProvider(){
                override fun getOutline(view: View?, outline: Outline?) {
                    outline?.setRoundRect(0,0, view?.width ?: 0, view?.height ?: 0, radius)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val (image, name, kalori, lemak, protein) = foodList[position]
        Glide.with(holder.itemView.context)
            .load(image)
            .into(holder.foodimage)
        holder.name.text = name
        holder.kalori.text = kalori
        holder.lemak.text = lemak
        holder.protein.text = protein
    }

    override fun getItemCount() = foodList.size

}