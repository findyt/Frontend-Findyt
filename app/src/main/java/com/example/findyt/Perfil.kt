package com.example.findyt

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView

class Perfil : AppCompatActivity() {

    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val profileImageView = findViewById<ShapeableImageView>(R.id.profileImageView)
            profileImageView.setImageURI(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val backButton = findViewById<ImageView>(R.id.backButton)
        val profileImageView = findViewById<ShapeableImageView>(R.id.profileImageView)
        val generoTextView = findViewById<TextView>(R.id.generoTextView)
        val preferenciaTextView = findViewById<TextView>(R.id.preferenciaTextView)
        val trabajoTextView = findViewById<TextView>(R.id.trabajoTextView)
        val educacionTextView = findViewById<TextView>(R.id.educacionTextView)
        val pesoTextView = findViewById<TextView>(R.id.pesoTextView)
        val estaturaTextView = findViewById<TextView>(R.id.estaturaTextView)
        val interesesTextView = findViewById<TextView>(R.id.interesesTextView)
        val descripcionTextView = findViewById<TextView>(R.id.descripcionTextView)
        val apodoTextView = findViewById<TextView>(R.id.apodoTextView)
        val apodoEditText = findViewById<EditText>(R.id.apodoEditText)
        val editApodoIcon = findViewById<ImageView>(R.id.editApodoIcon)
        val editGeneroIcon = findViewById<ImageView>(R.id.editGeneroIcon)
        val editPreferenciaIcon = findViewById<ImageView>(R.id.editPreferenciaIcon)
        val editTrabajoIcon = findViewById<ImageView>(R.id.editTrabajoIcon)
        val trabajoEditText = findViewById<EditText>(R.id.trabajoEditText)
        val educacionEditText = findViewById<EditText>(R.id.educacionEditText)
        val editEducacionIcon = findViewById<ImageView>(R.id.editEducacionIcon)
        val pesoEditText = findViewById<EditText>(R.id.pesoEditText)
        val editPesoIcon = findViewById<ImageView>(R.id.editPesoIcon)
        val estaturaEditText = findViewById<EditText>(R.id.estaturaEditText)
        val editEstaturaIcon = findViewById<ImageView>(R.id.editEstaturaIcon)
        val interesesEditText = findViewById<EditText>(R.id.interesesEditText)
        val editInteresesIcon = findViewById<ImageView>(R.id.editInteresesIcon)
        val descripcionEditText = findViewById<EditText>(R.id.descripcionEditText)
        val editDescripcionIcon = findViewById<ImageView>(R.id.editDescripcionIcon)




        profileImageView.setImageResource(R.drawable.fotomujer)
        apodoTextView.text = "Alex Sofia"
        generoTextView.text = "FEMENINO"
        preferenciaTextView.text = "HETEROSEXUAL"
        trabajoTextView.text = "Secretaria"
        educacionTextView.text = "Universidad de México"
        pesoTextView.text = "53 KG"
        estaturaTextView.text = "1.65 m"
        interesesTextView.text = "#Jardineria #Caminatas #Fotografia #Diseño"
        descripcionTextView.text = "Hola, mi nombre es Alexia pero todos me dicen Alex, me gusta la jardineria, adoro salir en las mañanas a caminar, adoro fotografiar flores y aves y me gusta dibujar, actualmente trabajo como Secretaria en una empresa de Alimentos."

        profileImageView.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        setupEditToggle(
            editIcon = editApodoIcon,
            textView = apodoTextView,
            editText = apodoEditText
        )

        editGeneroIcon.setOnClickListener {
            mostrarDialogoGenero(generoTextView)
        }
        editPreferenciaIcon.setOnClickListener {
            mostrarDialogoPreferencia(preferenciaTextView)
        }
        setupEditToggle(
            editIcon = editTrabajoIcon,
            textView = trabajoTextView,
            editText = trabajoEditText
        )
        setupEditToggle(
            editIcon = editEducacionIcon,
            textView = educacionTextView,
            editText = educacionEditText
        )
        setupEditToggle(
            editIcon = editPesoIcon,
            textView = pesoTextView,
            editText = pesoEditText,
            preEditTexto = { textoActual ->
                textoActual.replace(" KG", "").trim()
            },
            postGuardadoTexto = { nuevoTexto ->
                "$nuevoTexto KG"
            }
        )
        setupEditToggle(
            editIcon = editEstaturaIcon,
            textView = estaturaTextView,
            editText = estaturaEditText,
            preEditTexto = { textoActual ->
                textoActual.replace(" m", "").trim()
            },
            postGuardadoTexto = { nuevoTexto ->
                "$nuevoTexto m"
            }
        )
        setupEditToggle(
            editIcon = editInteresesIcon,
            textView = interesesTextView,
            editText = interesesEditText
        )
        setupEditToggle(
            editIcon = editDescripcionIcon,
            textView = descripcionTextView,
            editText = descripcionEditText
        )


        backButton.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }
    }
    private fun setupEditToggle(
        editIcon: ImageView,
        textView: TextView,
        editText: EditText,
        preEditTexto: ((String) -> String)? = null,
        postGuardadoTexto: ((String) -> String)? = null
    ) {
        editIcon.tag = "editar"
        editIcon.setOnClickListener {
            if (editIcon.tag == "editar") {
                textView.visibility = View.GONE
                editText.visibility = View.VISIBLE

                val textoAEditar = preEditTexto?.invoke(textView.text.toString()) ?: textView.text.toString()
                editText.setText(textoAEditar)

                editText.requestFocus()
                editText.setSelection(editText.text.length)
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)

                editIcon.tag = "guardar"
                editIcon.setImageResource(R.drawable.guardar)

            } else {
                textView.visibility = View.VISIBLE
                editText.visibility = View.GONE

                val textoGuardado = editText.text.toString()
                textView.text = postGuardadoTexto?.invoke(textoGuardado) ?: textoGuardado

                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(editText.windowToken, 0)
                editIcon.setImageResource(R.drawable.editar)
                editIcon.tag = "editar"
            }
        }
    }

    private fun mostrarDialogoGenero(generoTextView: TextView) {
        val opciones = arrayOf("FEMENINO", "MASCULINO", "NO BINARIO")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Selecciona tu género")
            .setItems(opciones) { dialog, which ->
                val seleccion = opciones[which]
                generoTextView.text = seleccion
            }
        builder.create().show()
    }
    private fun mostrarDialogoPreferencia(preferenciaTextView: TextView) {
        val opciones = arrayOf("HETEROSEXUAL", "HOMOSEXUAL", "BISEXUAL", "PANSEXUAL")
        AlertDialog.Builder(this)
            .setTitle("Selecciona tu preferencia")
            .setItems(opciones) { _, which ->
                val seleccion = opciones[which]
                preferenciaTextView.text = seleccion
            }
            .create()
            .show()
    }
}