package com.example.cpaweb.callbacks

import com.example.cpaweb.models.users.professionals.Professional
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetProfessionalCallback(
    private val handleError: (String) -> Unit,
    private val launchActivity: Unit,
): Callback<List<Professional>> {
    override fun onResponse(call: Call<List<Professional>>, response: Response<List<Professional>>) {
        if(response.isSuccessful){
            if(response.code() === 204){
                handleError("Não há profissionais cadastrados")
            }
        }else {
            handleError("Houve um erro na busca dos profissionais")
        }
    }

    override fun onFailure(call: Call<List<Professional>>, t: Throwable) {
        handleError("Ops! houve um erro na conexão")
    }
}