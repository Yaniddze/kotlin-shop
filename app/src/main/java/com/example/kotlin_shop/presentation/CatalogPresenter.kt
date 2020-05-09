package com.example.kotlin_shop.presentation

import com.example.kotlin_shop.domain.MainCategory
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.SubCategory
import com.example.kotlin_shop.domain.factories.ProductFactory
import com.example.kotlin_shop.domain.usecases.*
import com.example.kotlin_shop.ui.interfaces.CatalogView
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class CatalogPresenter @Inject constructor(

    private val factory: ProductFactory,

    private val catalogGetter: GetCatalogUseCase,

    private val catalogAdder: AddCatalogItemUseCase,

    private val hintsGetter: GetHintsUseCase,

    private val favoriteAdder: AddFavoriteUseCase,
    
    private val favoriteDeleter: DeleteFavoriteUseCase,

    private val refreshFavoriteUseCase: RefreshFavoriteUseCase

    ) : BasePresenter<CatalogView>() {

    private var i = 12

    companion object{
        private const val MAX_RESULTS = 5
    }

    fun addItem() {
        val itemToAdd = factory(
            ++i,
            "someProd$i",
            "https://static.toiimg.com/thumb/msid-74343235,width-640,resizemode-4/74343235.jpg",
            "",
            listOf(),
            1200.0,
            0,
            SubCategory("other", MainCategory("other")),
            listOf()
        )

        launch {
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
        launch {
            try{
                val items = catalogGetter().filter { it.name.contains(query) }.toMutableList()
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
            launch {
                try{
                    val hints = hintsGetter(query, MAX_RESULTS)

                    viewState.showHints(hints)
                }catch (e: Exception){
                    viewState.showNetworkError()
                }
            }
    }

    fun addToFavorite(product: Product){
        launch {
            favoriteAdder(product)
            product.isFavorite = true
            viewState.onFavoriteAdded(product)
        }
    }

    fun deleteFromFavorite(product: Product) {
        launch {
            favoriteDeleter(product)
            product.isFavorite = false
            viewState.onFavoriteDeleted(product)
        }
    }

    fun refreshFavorites(products: MutableList<Product>) {
        launch {
            val changed = refreshFavoriteUseCase(products)

            viewState.onFavoriteRefreshed(changed)
        }
    }
}