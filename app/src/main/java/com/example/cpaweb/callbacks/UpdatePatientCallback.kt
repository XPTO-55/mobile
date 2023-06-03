package com.example.cpaweb.callbacks

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdatePatientCallback(
    private val handleMessage: (String) -> Unit,
    private val updateUser: () -> Unit,
): Callback<Void> {
    override fun onResponse(call: Call<Void>, response: Response<Void>) {
        if(response.isSuccessful){
            handleMessage("Atualizado com sucesso")
            updateUser()
        }else {
            handleMessage("Houve ao atualizar o usuário")
        }
    }

    override fun onFailure(call: Call<Void>, t: Throwable) {
        handleMessage("Ops! houve um erro na conexão")
    }
}