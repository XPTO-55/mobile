package com.example.cpaweb.callbacks

import com.example.cpaweb.adapters.AppointmentListAdapter
import com.example.cpaweb.models.appointments.Appointment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetAppointmentsCallback(
    private val handleError: (String) -> Unit,
    private val adapter: AppointmentListAdapter,
): Callback<List<Appointment>> {
    override fun onResponse(call: Call<List<Appointment>>, response: Response<List<Appointment>>) {
        if(response.isSuccessful){
            if(response.code() === 204){
                handleError("Não há consultas cadastrados")
                return
            }
            adapter.updateData(response.body()!!)
        }else {
            handleError("Houve um erro na busca das consultas")
        }
    }

    override fun onFailure(call: Call<List<Appointment>>, t: Throwable) {
        println("EROOOO: ${t.message}")
        handleError("Ops! houve um erro na conexão")
    }
}