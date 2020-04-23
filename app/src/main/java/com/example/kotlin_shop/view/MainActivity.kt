package com.example.kotlin_shop.view

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kotlin_shop.R
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.view.fragments.CartFragmentDirections
import com.example.kotlin_shop.view.fragments.CatalogFragmentDirections

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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    fun showDetailed(product: Product){
        val action = CatalogFragmentDirections.actionNavigationCatalogToNaigationDetailed(product)

        navController.navigate(action)
    }

    fun showPurchaseFragment(){
        val action = CartFragmentDirections.actionNavigationCartToPurchaseFragment()

        navController.navigate(action)
    }

}
