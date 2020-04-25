package com.example.kotlin_shop.presentation

import com.example.kotlin_shop.di.components.DaggerCatalogPresenterComponent
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.factories.ProductFactory
import com.example.kotlin_shop.domain.usecases.AddCatalogItemUseCase
import com.example.kotlin_shop.domain.usecases.GetCatalogUseCase
import com.example.kotlin_shop.domain.usecases.GetViewedProductsUseCase
import com.example.kotlin_shop.ui.interfaces.CatalogView
import kotlinx.coroutines.runBlocking
import moxy.MvpPresenter
import javax.inject.Inject

class CatalogPresenter : MvpPresenter<CatalogView>() {

    init {
        DaggerCatalogPresenterComponent.create().inject(this)
    }

    @Inject
    lateinit var factory: ProductFactory

    @Inject
    lateinit var viewedGetter: GetViewedProductsUseCase

    @Inject
    lateinit var catalogGetter: GetCatalogUseCase

    @Inject
    lateinit var catalogAdder: AddCatalogItemUseCase

    private var i = 11

    fun addItem() {
        val itemToAdd = factory.createProduct(
            ++i,
            "someProd$i",
            "https://static.toiimg.com/thumb/msid-74343235,width-640,resizemode-4/74343235.jpg",
            1200.0,
            0
        )

        runBlocking {
            catalogAdder.add(itemToAdd)
        }

        viewState?.onAddCatalogItem()
    }

    fun getProducts() {
        var items = mutableListOf<Product>()
        runBlocking {
            items = catalogGetter.get()
        }
        viewState?.showProducts(items)
    }

    fun getViewed() {
        runBlocking {
            viewState?.showViewed(viewedGetter.getViewedProducts())
        }
    }
}