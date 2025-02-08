package com.example.tarjetahospitalespinar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tarjetahospitalespinar.ui.theme.TarjetaHospitalEspinarTheme

import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tarjetahospitalespinar.model.ent.Paciente
import com.google.gson.Gson
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var etDni: EditText
    private lateinit var btnBuscar: Button
    private lateinit var tvHcn: TextView
    private lateinit var tvPaterno: TextView
    private lateinit var tvMaterno: TextView
    private lateinit var tvNombre: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referenciar vistas
        etDni = findViewById(R.id.etDni)
        btnBuscar = findViewById(R.id.btnBuscar)
        tvHcn = findViewById(R.id.tvHcn)
        tvPaterno = findViewById(R.id.tvPaterno)
        tvMaterno = findViewById(R.id.tvMaterno)
        tvNombre = findViewById(R.id.tvNombre)

        btnBuscar.setOnClickListener {
            val dni = etDni.text.toString()
            if (dni.isNotEmpty()) {
                buscarPaciente(dni)
            }
        }
    }

    private fun buscarPaciente(dni: String) {

        RetrofitClient.instance.getPacienteByDNI(dni).enqueue(object : Callback<Paciente> {
            override fun onResponse(call: Call<Paciente>, response: Response<Paciente>) {
                if (response.isSuccessful) {
                    val paciente = response.body()
                    if (paciente != null) {
                       mostrarDatosPaciente(paciente)
                    } else {
                        Toast.makeText(applicationContext, "No se encontraron datos", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(applicationContext, "Error en la respuesta", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Paciente>, t: Throwable) {
                val message = "Error en la conexión: ${t.message}"
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                Log.e("CONEXIÓN", message)
            }
        })
    }

    private fun mostrarDatosPaciente(paciente: Paciente) {
        // Combinar nombres
        val nombreCompleto = if (!paciente.segundoNombre.isNullOrEmpty()) {
            "${paciente.primerNombre} ${paciente.segundoNombre}"
        } else {
            paciente.primerNombre
        }

        // Actualizar UI
        tvHcn.text = "H. C. N.: ${paciente.nroHistoriaClinica}"
        tvPaterno.text = "A. Paterno: ${paciente.apellidoPaterno}"
        tvMaterno.text = "A. Materno: ${paciente.apellidoMaterno}"
        tvNombre.text = "Nombre: $nombreCompleto"
    }
}
