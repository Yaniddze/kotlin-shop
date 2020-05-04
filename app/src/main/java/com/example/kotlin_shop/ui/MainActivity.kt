package com.example.kotlin_shop.ui

import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kotlin_shop.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_catalog, R.id.navigation_account, R.id.navigation_cart
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private var doubleTap: Boolean = false

    override fun onBackPressed() {
        currentFocus?.clearFocus()

        if(doubleTap){
            super.onBackPressed()
            doubleTap = false
            return
        }

        this.doubleTap = true
        Toast.makeText(this, "Click BACK again to exit", Toast.LENGTH_LONG).show()
        Handler().postDelayed({
            doubleTap = false
        }, 2000)

    }

    override fun onSupportNavigateUp(): Boolean {
        doubleTap = true
        onBackPressed()
        return true
    }

    fun navigate(directions: NavDirections){
        navController.navigate(directions)
    }
}
