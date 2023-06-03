package com.example.cpaweb.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cpaweb.CommunityHome
import com.example.cpaweb.R
import com.example.cpaweb.adapters.PlaceListAdapter
import com.example.cpaweb.callbacks.GetPlaceCallback
import com.example.cpaweb.callbacks.GetProfessionalCallback
import com.example.cpaweb.databinding.FragmentPlacesBinding
import com.example.cpaweb.models.Address
import com.example.cpaweb.models.places.Place
import com.example.cpaweb.rest.Api
import com.example.cpaweb.services.PlaceService
import com.example.cpaweb.services.ProfessionalService
class PlacesFragment : Fragment() {
    private lateinit var placesRecyclerView: RecyclerView;
    private lateinit var placesList: ArrayList<Place>;
    private lateinit var adapter: PlaceListAdapter;
    private lateinit var binding: FragmentPlacesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlacesBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeActivity = activity as CommunityHome
        val toolBar = homeActivity.findViewById<Toolbar>(R.id.tb_toolbar)
        toolBar.title = String.format("CPA | %s", getString(R.string.menu_places))

        placesList = ArrayList()
        adapter = PlaceListAdapter(this, placesList)
        placesRecyclerView = binding.rvPlacesList
        placesRecyclerView.adapter = adapter
        placesRecyclerView.setLayoutManager(LinearLayoutManager(this.context));


//        populateList()

        val service = Api.createService<PlaceService>(PlaceService::class.java)

        service.getPlaces().enqueue(
            GetPlaceCallback(
            { mensagem ->
                Toast.makeText(
                    view.context,
                    mensagem,
                    Toast.LENGTH_LONG
                ).show()
            },
            adapter
        )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun populateList() {
        val place1 = Place(
            0L,
            "Bras elétrica",
            "Boa localização",
            "",
            Address(
                0L,
                "Rua gaivota",
                "Moema",
                "23",
                "",
                "00000-000",
                "São Paulo",
                "SP",
                "https://goo.gl/maps/DTCxJgAKDNQdS3Ws8"
            )
        )
        val place2 = Place(
            1L,
            "Bras elétrica",
            "Boa localização",
            "",
            Address(
                1L,
                "Rua gaivota",
                "Moema",
                "23",
                "",
                "00000-000",
                "São Paulo",
                "SP",
                "https://goo.gl/maps/DZfv7PBsc7LE9MXv6"
            )
        )
        placesList.add(place1)
        placesList.add(place2)
        placesList.add(place1)
        placesList.add(place2)
    }
}