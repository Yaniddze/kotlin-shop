package com.example.kotlin_shop.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.kotlin_shop.R
import com.example.kotlin_shop.model.Product
import kotlinx.android.synthetic.main.detailed_layout.*

class DetailedView: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailed_layout)

        val product = intent.extras?.get("product") as? Product

        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        supportActionBar?.title = getString(R.string.detailed_header)

        if(product != null){
            Glide
                .with(this)
                .load(product.imageUrl)
                .error(R.mipmap.ic_launcher)
                .into(ivDetailedImage)

            tvDetailedPrice.text = product.calcDiscountPrice().toString() + " $"
            tvDetailedTitle.text = product.title
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}