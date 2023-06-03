package com.example.cpaweb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import android.widget.Toast
import com.example.cpaweb.callbacks.UpdatePatientCallback
import com.example.cpaweb.callbacks.UpdateProfessionalCallback
import com.example.cpaweb.databinding.ActivityEditUserBinding
import com.example.cpaweb.helpers.AuthManager
import com.example.cpaweb.models.Address
import com.example.cpaweb.models.auth.LoginResponse
import com.example.cpaweb.models.users.UserBase
import com.example.cpaweb.models.users.patient.UpdatePatientDTO
import com.example.cpaweb.models.users.professionals.Professional
import com.example.cpaweb.models.users.professionals.UpdateProfessionalDTO
import com.example.cpaweb.rest.Api
import com.example.cpaweb.services.PatientService
import com.example.cpaweb.services.ProfessionalService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditUser() : AppCompatActivity() {
    private lateinit var binding: ActivityEditUserBinding
    private lateinit var patientService: PatientService
    private lateinit var professionalService: ProfessionalService
    fun populateFields(userData: UserBase){
        binding.edtName.setText(userData.name?.toString())
        binding.edtEmail.setText(userData.email?.toString())
        binding.edtPhone.setText(userData.phone?.toString())
        binding.edtCep.setText(userData.address?.zipcode?.toString())
        binding.edtRua.setText(userData.address?.street?.toString())
    }

    fun bindingOptions(){
        val name: String = binding.edtName.text.toString()
        val email: String = binding.edtEmail.text.toString()
        val landline: String  = binding.edtName.text.toString()
        val phone: String  = binding.edtName.text.toString()
        val address: Address = Address(
            zipcode = binding.edtCep.text.toString(),
            complement = binding.edtComplement.toString(),
            number = binding.edtNumHouse.toString(),
            street = binding.edtRua.toString()
        )
       

        binding.btnSave.setOnClickListener {
            val userType = AuthManager.getUserType()

            when (userType) {
                "professional" -> {
                    professionalService = Api.createService(ProfessionalService::class.java)
                    var request: Call<Void> = professionalService.updateProfessional(
                        UpdateProfessionalDTO(
                            name = name,
                            email = email,
                            phone = phone,
                            landline = landline,
                            address = Address(
                                zipcode = address.zipcode,
                                complement = address.complement,
                                number = address.number,
                                street = address.street,
                            ),
                        )
                    )

                    request.enqueue(UpdateProfessionalCallback(
                        { message ->
                            Toast.makeText(
                                baseContext,
                                message,
                                Toast.LENGTH_LONG
                            ).show()

                        },
                        ::updateUser
                    ) )
                }

                else -> {
                    patientService = Api.createService(PatientService::class.java)
                    val request = patientService.updatePatient(
                        UpdatePatientDTO(
                            name = name,
                            email = email,
                            phone = phone,
                            landline = landline,
                            address = Address(
                                zipcode = address.zipcode,
                                complement = address.complement,
                                number = address.number,
                                street = address.street,
                            ),
                        )
                    )

                    request.enqueue(UpdatePatientCallback(
                        { message ->
                            Toast.makeText(
                                baseContext,
                                message,
                                Toast.LENGTH_LONG
                            ).show()

                        },
                        ::updateUser
                    ) )
                }
            }
        }

        binding.btnCancel.setOnClickListener {
            startActivity(Intent(this, CommunityHome::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AuthManager.init(this)
//        populateFields()
        bindingOptions()

        val userType = AuthManager.getUserType()
        val userId = AuthManager.getUserInfoId()
        when(userType){
            "professionals" -> {
                val service = Api.createService<ProfessionalService>(ProfessionalService::class.java)
                val request = service.getProfessional(userId!!)
                request.enqueue(object: Callback<Professional> {
                    override fun onResponse(
                        call: Call<Professional>,
                        response: Response<Professional>
                    ) {
                        if(response.isSuccessful){
                            val userBase = response.body() as UserBase
                            populateFields(userBase)
                        }else{
                            Toast.makeText(baseContext, "Erro ao buscar informações do usuário", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Professional>, t: Throwable) {
                        Toast.makeText(baseContext, "Ops! Houve um erro de conexão", Toast.LENGTH_SHORT).show()
                        println("EROOOOO ${t.message}")
                    }
                })
            }
            else -> {
                val service = Api.createService<PatientService>(PatientService::class.java)
                val request = service.getPatient(userId!!)
                request.enqueue(object: Callback<UserBase> {
                    override fun onResponse(
                        call: Call<UserBase>,
                        response: Response<UserBase>
                    ) {
                       if(response.isSuccessful){
                           val userBase = response.body() as UserBase
                           populateFields(userBase)
                       }else{
                           Toast.makeText(baseContext, "Erro ao buscar informações do usuário", Toast.LENGTH_SHORT).show()
                       }
                    }

                    override fun onFailure(call: Call<UserBase>, t: Throwable) {
                        Toast.makeText(baseContext, "Ops! Houve um erro de conexão", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }

    fun updateUser() {
        val user = AuthManager.getUser()
        AuthManager.saveUser(LoginResponse(
            id = user.id,
            userType = user.userType.toString(),
            profileUrl = user.profileUrl?.toString(),
            username = binding.edtName.text.toString(),
            email = binding.edtEmail.text.toString(),
            phone = binding.edtPhone.text.toString(),
        ))
    }
}