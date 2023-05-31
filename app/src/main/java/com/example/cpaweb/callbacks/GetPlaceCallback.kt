package com.example.cpaweb.callbacks

import com.example.cpaweb.adapters.PlaceListAdapter
import com.example.cpaweb.adapters.ProfessionalListAdapter
import com.example.cpaweb.models.places.Place
import com.example.cpaweb.models.users.professionals.Professional
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetPlaceCallback(
    private val handleError: (String) -> Unit,
    private val adapter: PlaceListAdapter,
): Callback<List<Place>> {
    override fun onResponse(call: Call<List<Place>>, response: Response<List<Place>>) {
        if(response.isSuccessful){
            if(response.code() === 204){
                handleError("Não há lugares cadastrados")
            }
            adapter.updateData(response.body()!!)
        }else {
            handleError("Houve um erro na busca dos lugares")
        }
    }

    override fun onFailure(call: Call<List<Place>>, t: Throwable) {
        handleError("Ops! houve um erro na conexão")
    }
}