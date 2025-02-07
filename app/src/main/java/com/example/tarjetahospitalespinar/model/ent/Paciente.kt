package com.example.tarjetahospitalespinar.model.ent

import com.google.gson.annotations.SerializedName

data class Paciente (
    @SerializedName("IdPaciente")
    val id: Int,

    @SerializedName("ApellidoPaterno")
    val apellidoPaterno: String,

    @SerializedName("ApellidoMaterno")
    val apellidoMaterno: String,

    @SerializedName("PrimerNombre")
    val primerNombre: String,

    @SerializedName("SegundoNombre")
    val segundoNombre: String?,

    @SerializedName("FechaNacimiento")
    val fechaNacimiento: String?,

    @SerializedName("NroDocumento")
    val nroDocumento: String?,

    @SerializedName("NroHistoriaClinica")
    val nroHistoriaClinica: Int
){
    override fun toString(): String {
        return "Paciente(id=$id, apellidoPaterno='$apellidoPaterno', apellidoMaterno='$apellidoMaterno', " +
               "primerNombre='$primerNombre', segundoNombre=$segundoNombre, fechaNacimiento=$fechaNacimiento, " +
               "nroDocumento=$nroDocumento, nroHistoriaClinica=$nroHistoriaClinica)"
    }
}