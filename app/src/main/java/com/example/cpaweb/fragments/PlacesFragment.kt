package com.example.cpaweb.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cpaweb.adapters.PlaceListAdapter
import com.example.cpaweb.databinding.FragmentPlacesBinding
import com.example.cpaweb.models.Address
import com.example.cpaweb.models.places.Place


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PlacesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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
        placesList = ArrayList()
        adapter = PlaceListAdapter(this, placesList)
        placesRecyclerView = binding.rvPlacesList
        placesRecyclerView.adapter = adapter
        placesRecyclerView.setLayoutManager(LinearLayoutManager(this.context));


        populateList()

        adapter.notifyDataSetChanged();
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
                "SP"
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
                "SP"
            )
        )
        placesList.add(place1)
        placesList.add(place2)
        placesList.add(place1)
        placesList.add(place2)
    }
}