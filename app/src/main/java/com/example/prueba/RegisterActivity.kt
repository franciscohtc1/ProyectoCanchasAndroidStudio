package com.example.prueba  // ← cambia esto por tu paquete real

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.prueba.R
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val nombre = findViewById<EditText>(R.id.etNombre)
        val correo = findViewById<EditText>(R.id.etCorreoRegistro)
        val contrasena = findViewById<EditText>(R.id.etContrasenaRegistro)
        val botonRegistrar = findViewById<Button>(R.id.btnRegistrar)

        botonRegistrar.setOnClickListener {
            val nombreTxt = nombre.text.toString()
            val correoTxt = correo.text.toString()
            val contrasenaTxt = contrasena.text.toString()

            if (nombreTxt.isNotEmpty() && correoTxt.isNotEmpty() && contrasenaTxt.isNotEmpty()) {
                registrarUsuario(nombreTxt, correoTxt, contrasenaTxt)
            } else {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registrarUsuario(nombre: String, correo: String, contrasena: String) {
        val url = "http://143.198.11.155/registro.php" // O reemplaza con tu URL de ngrok

        val jsonBody = JSONObject()
        jsonBody.put("nombre", nombre)
        jsonBody.put("correo", correo)
        jsonBody.put("contrasena", contrasena)

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonBody,
            { response ->
                val success = response.getBoolean("success")
                val mensaje = response.getString("mensaje")
                Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()

                if (success) {
                    finish() // opcional: vuelve al login
                    //Prueba de git
                }
            },
            { error ->
                error.printStackTrace()
                Toast.makeText(this, "Error: ${error.localizedMessage ?: "Desconocido"}", Toast.LENGTH_LONG).show()
            }
        )

        Volley.newRequestQueue(this).add(request)
    }
}