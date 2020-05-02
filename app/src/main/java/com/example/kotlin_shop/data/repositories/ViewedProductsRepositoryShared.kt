package com.example.kotlin_shop.data.repositories

import android.content.SharedPreferences
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.ViewedProduct
import com.example.kotlin_shop.domain.ViewedProductFactory
import com.example.kotlin_shop.domain.repositories.ViewedProductsRepository

class ViewedProductsRepositoryShared(
    private val sharedPreferences: SharedPreferences,
    private val factory: ViewedProductFactory
): ViewedProductsRepository {

    init {
        if(savedProducts == null)
            savedProducts = sharedPreferences.getString(PRODUCT_TAG, null)
                ?.split("|")
                ?.map {
                    val info = it.split(';')
                    factory(
                        info[0].toInt(),
                        info[1],
                        info[2]
                    )
                }?.toMutableList() ?: mutableListOf()
    }

    override suspend fun getAll(): MutableList<ViewedProduct> = savedProducts!!

    override suspend fun add(product: Product) {

        val sharedValue = sharedPreferences.getString(PRODUCT_TAG, null)

        val productInList = savedProducts?.firstOrNull { it.productId == product.id }

        var currentValue = ""

        if(productInList != null) {
            sharedValue
                ?.split("|")
                ?.forEach {
                    if(it.split(";")[0].toInt() != product.id)
                        currentValue = "$currentValue|$it"
                }

            savedProducts?.remove(productInList)
        }
        else{
            currentValue = if(sharedValue == null) "" else "|$sharedValue"
        }

        val productString = "${product.id};${product.imageUrl};${product.title}"

        sharedPreferences.edit().putString(PRODUCT_TAG, "$productString$currentValue").apply()

        savedProducts?.add(0, factory(product.id, product.imageUrl, product.title))
    }

    companion object{

        private const val PRODUCT_TAG: String = "PRODUCT_TAG"

        private var savedProducts: MutableList<ViewedProduct>? = null

    }
}