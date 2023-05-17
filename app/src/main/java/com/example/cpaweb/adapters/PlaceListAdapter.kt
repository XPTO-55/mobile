package com.example.cpaweb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cpaweb.R
import com.example.cpaweb.fragments.PlacesFragment
import com.example.cpaweb.models.places.Place

class PlaceListAdapter (val context: PlacesFragment, var placesList: ArrayList<Place>):
    RecyclerView.Adapter<PlaceListAdapter.PlaceItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceItemViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.places_list_item, parent, false)
        return PlaceListAdapter.PlaceItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceItemViewHolder, position: Int) {
        val currentPlace = placesList[position]
        holder.txtName.text = currentPlace.nomeLugar
        holder.txtStreet.text = currentPlace.address?.street
        holder.txtStreetNumber.text = currentPlace.address?.number
        holder.txtDistrict.text = currentPlace.address?.district
        holder.txtCity.text = currentPlace.address?.city
        holder.txtState.text = currentPlace.address?.uf
        holder.txtZipcode.text = currentPlace.address?.zipcode
    }

    override fun getItemCount(): Int {
        return placesList.size
    }

    class PlaceItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtName = itemView.findViewById<TextView>(R.id.tv_place_name)
        val txtStreet = itemView.findViewById<TextView>(R.id.tv_place_street)
        val txtStreetNumber = itemView.findViewById<TextView>(R.id.tv_place_street_number)
        val txtDistrict = itemView.findViewById<TextView>(R.id.tv_place_district)
        val txtCity = itemView.findViewById<TextView>(R.id.tv_place_city)
        val txtState = itemView.findViewById<TextView>(R.id.tv_place_state)
        val txtZipcode = itemView.findViewById<TextView>(R.id.tv_place_zipcode)
    }
}