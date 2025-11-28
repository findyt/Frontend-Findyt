package com.example.findyt

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.ImageView
import android.widget.Toast
import android.view.animation.AnimationUtils
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.card.MaterialCardView
import com.google.android.material.navigation.NavigationView

class Pushypull : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pushypull)
        drawerLayout = findViewById(R.id.drawer_layout)
        val menuButton = findViewById<ImageView>(R.id.menuButton)
        val helpButton = findViewById<ImageView>(R.id.helpButton)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val profileCard = findViewById<MaterialCardView>(R.id.profileCard)
        val pullButton = findViewById<MaterialCardView>(R.id.pullButton)
        val chatButton = findViewById<MaterialCardView>(R.id.chatButton)
        val acceptButton = findViewById<MaterialCardView>(R.id.acceptButton)
        val rejectButton = findViewById<MaterialCardView>(R.id.rejectButton)
        val userProfileImageView = findViewById<ImageView>(R.id.userProfileImageView)
        val ageTextView = findViewById<TextView>(R.id.ageTextView)
        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val distanceTextView = findViewById<TextView>(R.id.distanceTextView)
        val descriptionPreviewTextView = findViewById<TextView>(R.id.descriptionPreviewTextView)

        userProfileImageView.setImageResource(R.drawable.fotohombre)
        ageTextView.text = "26"
        nameTextView.text = "Jose Rogelio"
        distanceTextView.text = "1.5 Km"
        descriptionPreviewTextView.text = "Me gusta el romance, busco una relación estable y apasionada."


        val slideDownAnim = AnimationUtils.loadAnimation(this, R.anim.slide_down_and_rotate)
        val slideUpAnim = AnimationUtils.loadAnimation(this, R.anim.slide_up_and_rotate)

        menuButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        navigationView.setNavigationItemSelectedListener(this)

        val headerView = navigationView.getHeaderView(0)
        val closeMenuButton = headerView.findViewById<ImageView>(R.id.closeMenuButton)
        closeMenuButton.setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        helpButton.setOnClickListener {
            val intent = Intent(this, Duda::class.java)
            startActivity(intent)
        }

        pullButton.setOnClickListener {
            val intent = Intent(this, Pulls::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        chatButton.setOnClickListener {
            val intent = Intent(this, ListaChats::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        acceptButton.setOnClickListener {
            profileCard.startAnimation(slideDownAnim)
            Handler().postDelayed({
                profileCard.clearAnimation()
            }, 500)

        }

        rejectButton.setOnClickListener {
            profileCard.startAnimation(slideUpAnim)
            Handler().postDelayed({
                profileCard.clearAnimation()
            }, 500)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                val intent = Intent(this, Perfil::class.java)
                startActivity(intent)
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Cerrando sesión...", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Principal::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
