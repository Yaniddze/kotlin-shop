package com.example.kotlin_shop.data.repositories

import android.content.SharedPreferences
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.factories.ProductFactory
import com.example.kotlin_shop.domain.repositories.ViewedProductsRepository

class ViewedProductsRepositoryImpl(
    private val sharedPreferences: SharedPreferences,
    private val productFactory: ProductFactory
): ViewedProductsRepository {

    init {
        if(savedProducts == null)
            savedProducts = sharedPreferences.getString(PRODUCT_TAG, null)
                ?.split("|")
                ?.map {
                    val info = it.split(';')
                    productFactory.createProduct(
                        info[0].toInt(),
                        info[1],
                        info[2],
                        "",
                        listOf(),
                        info[3].toDouble(),
                        info[4].toInt()
                    )
                }?.toMutableList() ?: mutableListOf()
    }

    override suspend fun getAll(): MutableList<Product> = savedProducts!!

    override suspend fun add(product: Product) {

        val sharedValue = sharedPreferences.getString(PRODUCT_TAG, null)

        val productInList = savedProducts?.firstOrNull { it.id == product.id }

        var currentValue = ""

        if(productInList != null) {
            sharedValue
                ?.split("|")
                ?.forEach {
                    if(it.split(";")[0].toInt() != product.id)
                        currentValue = "$currentValue|$it"
                }

            savedProducts?.remove(product)
        }
        else{
            currentValue = if(sharedValue == null) "" else "|$sharedValue"
        }

        val productString = "${product.id};${product.title};${product.imageUrl};${product.lot.price};${product.lot.salePercent}"

        sharedPreferences.edit().putString(PRODUCT_TAG, "$productString$currentValue").apply()

        savedProducts?.add(0, product)
    }

    companion object{

        private const val PRODUCT_TAG: String = "PRODUCT_TAG"

        private var savedProducts: MutableList<Product>? = null

    }
}