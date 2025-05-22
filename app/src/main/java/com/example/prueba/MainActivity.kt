package com.example.prueba

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.widget.Button
import android.widget.EditText
import android.widget.Toast


import android.widget.*
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import android.util.Log
import android.content.Intent



class MainActivity : AppCompatActivity() {
    private lateinit var etCorreo: EditText
    private lateinit var etContrasena: EditText
    private lateinit var btnIniciarSesion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCorreo = findViewById(R.id.etCorreo)
        etContrasena = findViewById(R.id.etContrasena)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)
        val tvRegistro = findViewById<TextView>(R.id.tvRegistro)


        btnIniciarSesion.setOnClickListener {
            val correo = etCorreo.text.toString()
            val contrasena = etContrasena.text.toString()

            if (correo.isNotEmpty() && contrasena.isNotEmpty()) {
                iniciarSesion(correo, contrasena)
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        tvRegistro.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


    }

    private fun iniciarSesion(correo: String, contrasena: String) {
        val url = "http://143.198.11.155/login.php" // ← cambia si usas ngrok

        val jsonBody = JSONObject()
        jsonBody.put("correo", correo)
        jsonBody.put("contrasena", contrasena)

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonBody,
            { response ->
                val success = response.getBoolean("success")
                val mensaje = response.getString("mensaje")
                Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()

                if (success) {
                    val usuarioId = response.getInt("usuario_id")
                    val rol = response.getString("rol")
                    // Aquí puedes abrir otra pantalla o guardar sesión
                }
            },
            { error ->
                error.printStackTrace()

                val statusCode = error.networkResponse?.statusCode
                val responseBody = error.networkResponse?.data?.let { String(it) }

                Log.e("LOGIN_ERROR", "Código HTTP: $statusCode\nRespuesta: $responseBody")

                Toast.makeText(this, "Error: ${error.localizedMessage ?: "Desconocido"}", Toast.LENGTH_LONG).show()
            }
        )

        Volley.newRequestQueue(this).add(request)
    }


}