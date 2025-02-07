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
import androidx.appcompat.app.AppCompatActivity
import com.example.tarjetahospitalespinar.model.ent.Paciente
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dni = "24865656" // prueba dni valido
        /*
        * 23979509
        * 24888772
        * 24865656
        * 40567405
        * 24891081
        * */

        RetrofitClient.instance.getPacienteByDNI(dni).enqueue(object : Callback<Paciente> {
            override fun onResponse(call: Call<Paciente>, response: Response<Paciente>) {
                if (response.isSuccessful) {
                    val paciente = response.body()
                    Log.d("API_RESPONSE", "Paciente: $paciente")
                } else {
                    Log.e("API_ERROR", "Error en la respuesta")
                }
            }

            override fun onFailure(call: Call<Paciente>, t: Throwable) {
                Log.e("API_ERROR", "Error en la conexión: ${t.message}")
            }
        })
    }
}
