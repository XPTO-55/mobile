package com.example.cpaweb.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cpaweb.AboutProfessional
import com.example.cpaweb.R
import com.example.cpaweb.fragments.ProfessionalsFragment
import com.example.cpaweb.models.users.professionals.Professional

class ProfessionalListAdapter(val context: ProfessionalsFragment, var professionalList: ArrayList<Professional>):
    RecyclerView.Adapter<ProfessionalListAdapter.ProfessionalItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessionalItemViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.professional_list_item, parent, false)
        return ProfessionalItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfessionalItemViewHolder, position: Int) {
        val currentProfessional = professionalList[position]
        holder.txtName.text = currentProfessional.name
        holder.txtSpeciality.text = currentProfessional.especialidade
        holder.rating.rating = currentProfessional.ratings.map { it.rating }.average().toFloat()

        holder.btnMore.setOnClickListener {
            val intent = Intent(holder.itemView.getContext(), AboutProfessional::class.java)
            intent.putExtra("professionalData", currentProfessional)
            holder.itemView.context.startActivity(intent)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return professionalList.size
    }

    fun updateData(newItems: List<Professional>) {
        professionalList.clear()
        professionalList.addAll(newItems)
        notifyDataSetChanged()
    }

    class ProfessionalItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtName = itemView.findViewById<TextView>(R.id.tv_professional_name)
        val txtSpeciality = itemView.findViewById<TextView>(R.id.tv_professional_speciality)
        val rating = itemView.findViewById<RatingBar>(R.id.rb_rating)
        val btnMore = itemView.findViewById<Button>(R.id.btn_more)
    }

}