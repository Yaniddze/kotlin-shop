package com.example.kotlin_shop.presentation

import com.example.kotlin_shop.domain.factories.ProductFactory
import com.example.kotlin_shop.domain.usecases.AddCatalogItemUseCase
import com.example.kotlin_shop.domain.usecases.GetCatalogUseCase
import com.example.kotlin_shop.domain.usecases.GetViewedProductsUseCase
import com.example.kotlin_shop.ui.interfaces.CatalogView
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogPresenter @Inject constructor(
    private val factory: ProductFactory,

    private val catalogGetter: GetCatalogUseCase,

    private val catalogAdder: AddCatalogItemUseCase

) : BasePresenter<CatalogView>() {

    private var i = 11

    fun addItem() {
        val itemToAdd = factory.createProduct(
            ++i,
            "someProd$i",
            "https://static.toiimg.com/thumb/msid-74343235,width-640,resizemode-4/74343235.jpg",
            "",
            listOf(),
            1200.0,
            0
        )

        scope.launch {
            try{
                catalogAdder(itemToAdd)
                viewState.onAddCatalogItem(itemToAdd)
            }
            catch (e: Exception){
                viewState.showError("Add to server is not working")
            }
        }

    }

    fun getProducts() {
        scope.launch {

            try{
                val items = catalogGetter()
                viewState.showProducts(items)
            } catch (e: Exception){
                viewState.showError("Server is currently offline")
            }
        }
    }
}