package com.example.kotlin_shop.presentation

import com.example.kotlin_shop.domain.factories.ProductFactory
import com.example.kotlin_shop.domain.usecases.AddCatalogItemUseCase
import com.example.kotlin_shop.domain.usecases.GetCatalogUseCase
import com.example.kotlin_shop.domain.usecases.GetHintsUseCase
import com.example.kotlin_shop.domain.usecases.GetViewedProductsUseCase
import com.example.kotlin_shop.ui.interfaces.CatalogView
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class CatalogPresenter @Inject constructor(
    private val factory: ProductFactory,

    private val catalogGetter: GetCatalogUseCase,

    private val catalogAdder: AddCatalogItemUseCase,

    private val hintsGetter: GetHintsUseCase

) : BasePresenter<CatalogView>() {

    private var i = 11

    companion object{
        private const val MAX_RESULTS = 5
    }

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

    fun getProducts(query: String) {
        scope.launch {
            try{
                val items = catalogGetter().filter { it.title.contains(query) }.toMutableList()
                viewState.showProducts(items)
            } catch (e: UnknownHostException){
                viewState.showNetworkError()
            } catch (e: SocketTimeoutException){
                viewState.showNetworkError()
            }
        }
    }

    fun getHints(query: String){

        if(query != "")
            scope.launch {
                try{
                    val hints = hintsGetter("default", query, MAX_RESULTS)

                    viewState.showHints(hints)
                }catch (e: Exception){
                    viewState.showNetworkError()
                }
            }
    }
}