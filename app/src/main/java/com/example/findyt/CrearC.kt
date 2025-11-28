package com.example.findyt

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CrearC : AppCompatActivity() {

    private lateinit var nombreEditText: EditText
    private lateinit var apellidoPaternoEditText: EditText
    private lateinit var apellidoMaternoEditText: EditText
    private lateinit var diaEditText: EditText
    private lateinit var mesEditText: EditText
    private lateinit var anoEditText: EditText
    private lateinit var numeroTelefonicoEditText: EditText
    private lateinit var correoElectronicoEditText: EditText
    private lateinit var contrasenaEditText: EditText
    private lateinit var confirmarContrasenaEditText: EditText
    private lateinit var crearCuentaButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crearc)

        try {
            nombreEditText = findViewById(R.id.nombreEditText)
            apellidoPaternoEditText = findViewById(R.id.apellidoPaternoEditText)
            apellidoMaternoEditText = findViewById(R.id.apellidoMaternoEditText)
            diaEditText = findViewById(R.id.diaEditText)
            mesEditText = findViewById(R.id.mesEditText)
            anoEditText = findViewById(R.id.anoEditText)
            numeroTelefonicoEditText = findViewById(R.id.numeroTelefonicoEditText)
            correoElectronicoEditText = findViewById(R.id.correoElectronicoEditText)
            contrasenaEditText = findViewById(R.id.contrasenaEditText)
            confirmarContrasenaEditText = findViewById(R.id.confirmarContrasenaEditText)
            crearCuentaButton = findViewById(R.id.crearCuentaButton)
        } catch (e: Exception) {
            Toast.makeText(this, "Error al inicializar las vistas: ${e.message}", Toast.LENGTH_LONG).show()
            e.printStackTrace()
            finish()
            return
        }


        crearCuentaButton.setOnClickListener {
            val nombre = nombreEditText.text.toString().trim()
            val apellidoPaterno = apellidoPaternoEditText.text.toString().trim()
            val apellidoMaterno = apellidoMaternoEditText.text.toString().trim()
            val dia = diaEditText.text.toString().trim()
            val mes = mesEditText.text.toString().trim()
            val ano = anoEditText.text.toString().trim()
            val numeroTelefonico = numeroTelefonicoEditText.text.toString().trim()
            val correoElectronico = correoElectronicoEditText.text.toString().trim()
            val contrasena = contrasenaEditText.text.toString()
            val confirmarContrasena = confirmarContrasenaEditText.text.toString()

            if (nombre.isNotEmpty() && apellidoPaterno.isNotEmpty() && dia.isNotEmpty() && mes.isNotEmpty() && ano.isNotEmpty() && numeroTelefonico.isNotEmpty() && correoElectronico.isNotEmpty() && contrasena.isNotEmpty() && confirmarContrasena.isNotEmpty()) {

                if (contrasena == confirmarContrasena) {
                    Toast.makeText(this, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, CrearPerfil::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                } else {
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun mostrarDialogoDeSalida(){
        AlertDialog.Builder(this)
            .setTitle("Salir")
            .setMessage("¿Estás seguro de que deseas salir?")
            .setPositiveButton("SI"){ dialog, which ->
                val intent = Intent(this, Principal::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                finish()
            }
            .setNegativeButton("NO") { dialog, which ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
    override fun onBackPressed() {
        mostrarDialogoDeSalida()
    }
}
