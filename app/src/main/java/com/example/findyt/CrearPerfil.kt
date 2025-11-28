package com.example.findyt

import android.content.Intent
import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.TextView
import android.content.res.Resources
import android.text.Editable
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView

class CrearPerfil : AppCompatActivity() {

    private lateinit var profileImageView: ShapeableImageView
    private lateinit var addPhotoIcon: ImageView
    private var selectedImageUri: Uri? = null

    private lateinit var generoLayout: LinearLayout
    private lateinit var preferenciaLayout: LinearLayout
    private lateinit var generoTextView: TextView
    private lateinit var preferenciaTextView: TextView

    private lateinit var ubicacionEditText: LinearLayout

    private lateinit var trabajoEditText: EditText
    private lateinit var educacionEditText: EditText

    private lateinit var descripcionEditText: EditText
    private lateinit var charCountTextView: TextView
    private lateinit var apodoEditText: EditText
    private lateinit var pesoEditText: EditText
    private lateinit var estaturaEditText: EditText
    private lateinit var interesesEditText: EditText

    private lateinit var crearPerfilButton: Button





    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            profileImageView.setImageURI(it)
            addPhotoIcon.visibility = ImageView.GONE
        }
        }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_perfil)

        profileImageView = findViewById(R.id.profileImageView)
        addPhotoIcon = findViewById(R.id.addPhotoIcon)

        generoLayout = findViewById(R.id.generoLayout)
        preferenciaLayout = findViewById(R.id.preferenciaLayout)

        generoTextView = findViewById(R.id.generoTextView)
        preferenciaTextView = findViewById(R.id.preferenciaTextView)

        trabajoEditText = findViewById(R.id.trabajoEditText)
        educacionEditText = findViewById(R.id.educacionEditText)

        descripcionEditText = findViewById(R.id.descripcionEditText)
        charCountTextView = findViewById(R.id.charCounterTextView)

        apodoEditText = findViewById(R.id.apodoEditText)
        pesoEditText = findViewById(R.id.pesoEditText)
        estaturaEditText = findViewById(R.id.estaturaEditText)
        interesesEditText = findViewById(R.id.interesesEditText)

        crearPerfilButton = findViewById(R.id.crearPerfilButton)


        descripcionEditText.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val currentLength = s?.length ?: 0
                val maxLength = 300
                charCountTextView.text = "$currentLength / $maxLength"
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })



        profileImageView.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        generoLayout.setOnClickListener {
            mostrarDialogoGenero()
        }

        preferenciaLayout.setOnClickListener {
            mostrarDialogoPreferencia()
        }



        crearPerfilButton.setOnClickListener {
            if (validarCampos()) {
                Toast.makeText(this, "Perfil creado exitosamente", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Pushypull::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                finish()
            }
        }
    }
    private fun mostrarDialogoGenero() {
        val opciones = arrayOf("Masculino", "Femenino", "No binario")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Selecciona tu género")
            .setItems(opciones) { _, which ->
                val seleccion = opciones[which]
                generoTextView.text = seleccion
            }
        builder.create().show()
    }

    private fun mostrarDialogoPreferencia() {
        val opciones = arrayOf("Heterosexual", "Homosexual", "Bisexual", "Pansexual")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Selecciona tu preferencia")
            .setItems(opciones) { dialog, which ->
                val seleccion = opciones[which]
                preferenciaTextView.text = seleccion
            }
        builder.create().show()
    }
    private fun validarCampos(): Boolean {
        if (selectedImageUri == null) {
            Toast.makeText(this, "Campo de Foto de Perfil vacio", Toast.LENGTH_SHORT).show()
            return false
        }
        if (apodoEditText.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa un apodo", Toast.LENGTH_SHORT).show()
            return false
        }
        if (trabajoEditText.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa tu trabajo", Toast.LENGTH_SHORT).show()
            return false
        }
        if (educacionEditText.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa tu educación", Toast.LENGTH_SHORT).show()
            return false
        }
        if (pesoEditText.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa tu peso", Toast.LENGTH_SHORT).show()
            return false
        }
        if (estaturaEditText.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa tu estatura", Toast.LENGTH_SHORT).show()
            return false
        }
        if (interesesEditText.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa tus intereses", Toast.LENGTH_SHORT).show()
            return false
        }
        if (descripcionEditText.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "Por favor, añade una descripción", Toast.LENGTH_SHORT).show()
            return false
        }
        if (generoTextView.text.toString().equals("GÉNERO", ignoreCase = true)) {
            Toast.makeText(this, "Por favor, selecciona tu género", Toast.LENGTH_SHORT).show()
            return false
        }
        if (preferenciaTextView.text.toString().equals("PREFERENCIA", ignoreCase = true)) {
            Toast.makeText(this, "Por favor, selecciona tu preferencia", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    private fun mostrarDialogoDeSalida(){
        AlertDialog.Builder(this)
            .setTitle("Salir")
            .setMessage("¿Estás seguro de que deseas salir?")
            .setPositiveButton("Sí") { dialog, which ->
                val intent = Intent(this, Principal::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                finish()
            }
            .setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
    override fun onBackPressed() {
        mostrarDialogoDeSalida()
    }
}