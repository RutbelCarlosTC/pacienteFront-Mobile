package com.example.tarjetahospitalespinar

import com.example.tarjetahospitalespinar.model.ent.Paciente
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("api/patients/{dni}")
    fun getPacienteByDNI(@Path("dni") dni: String): Call<Paciente>
}