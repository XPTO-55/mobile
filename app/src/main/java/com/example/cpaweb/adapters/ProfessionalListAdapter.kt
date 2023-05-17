package com.example.cpaweb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
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

//        holder.itemView.setOnClickListener {
//            val intent = Intent(context, ChatActivity::class.java)
//
//            intent.putExtra("name", currentUser.name);
//            intent.putExtra("uid", currentUser.uid);
//
//            context.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return professionalList.size
    }

    class ProfessionalItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtName = itemView.findViewById<TextView>(R.id.tv_professional_name)
        val txtSpeciality = itemView.findViewById<TextView>(R.id.tv_professional_speciality)
    }

}