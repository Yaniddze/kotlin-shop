package com.example.kotlin_shop.presentation

import com.example.kotlin_shop.di.components.DaggerCatalogPresenterComponent
import com.example.kotlin_shop.domain.factories.ProductFactory
import com.example.kotlin_shop.domain.usecases.AddCatalogItemUseCase
import com.example.kotlin_shop.domain.usecases.GetCatalogUseCase
import com.example.kotlin_shop.domain.usecases.GetViewedProductsUseCase
import com.example.kotlin_shop.ui.interfaces.CatalogView
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class CatalogPresenter : BasePresenter<CatalogView>() {

    init {
        DaggerCatalogPresenterComponent.create().inject(this)
    }

    @Inject
    lateinit var factory: ProductFactory

    @Inject
    lateinit var viewedGetter: GetViewedProductsUseCase


    @field:[Inject Named("mainCatalogGetter")]
    lateinit var mainCatalogGetter: GetCatalogUseCase


    @field:[Inject Named("subCatalogGetter")]
    lateinit var subCatalogGetter: GetCatalogUseCase


    @field:[Inject Named("mainCatalogAdder")]
    lateinit var mainCatalogAdder: AddCatalogItemUseCase


    @field:[Inject Named("subCatalogAdder")]
    lateinit var subCatalogAdder: AddCatalogItemUseCase

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
            mainCatalogAdder.add(itemToAdd)
            viewState.onAddCatalogItem()
        }

    }

    fun getProducts() {
        scope.launch {

            val items = try{
                mainCatalogGetter.get()
            } catch (e: Exception){
                viewState.showMainCatalogError()
                subCatalogGetter.get()
            }
            viewState.showProducts(items)
        }
    }

    fun getViewed() {
        scope.launch {
            val products = viewedGetter.getViewedProducts()
            viewState.showViewed(products)
        }
    }
}