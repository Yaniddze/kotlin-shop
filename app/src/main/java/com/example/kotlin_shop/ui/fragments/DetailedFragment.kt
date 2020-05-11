package com.example.kotlin_shop.ui.fragments

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin_shop.App
import com.example.kotlin_shop.R
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.ViewedProduct
import com.example.kotlin_shop.presentation.DetailedPresenter
import com.example.kotlin_shop.ui.adapters.PhotosAdapter
import com.example.kotlin_shop.ui.adapters.ViewedProductsAdapter
import com.example.kotlin_shop.ui.interfaces.DetailedView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class DetailedFragment() : MvpAppCompatFragment(R.layout.fragment_detailed), DetailedView {

    @Inject
    lateinit var presenterProvider: Provider<DetailedPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    init {
        App.appComponent.inject(this)
    }

    private lateinit var titleView: TextView
    private lateinit var priceView: TextView
    private lateinit var fullPriceView: TextView
    private lateinit var discountView: TextView
    private lateinit var genreTitle: TextView
    private lateinit var btnToCart: Button
    private lateinit var ivFavorite: ImageView
    private lateinit var rvPhotos: RecyclerView

    private val viewedAdapter = ViewedProductsAdapter()
    private val photosAdapter = PhotosAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvPhotos = view.findViewById(R.id.rvDetailedPhotos)
        titleView = view.findViewById(R.id.tvDetailedTitle)
        priceView = view.findViewById(R.id.tvDetailedPrice)
        fullPriceView = view.findViewById(R.id.tvDetailedFullPrice)
        discountView = view.findViewById(R.id.tvDetailedDiscount)
        genreTitle = view.findViewById(R.id.tvDetailedGenre)
        btnToCart = view.findViewById(R.id.btnToCart)
        ivFavorite = view.findViewById(R.id.ivFavoriteDetailed)

        val productId = arguments?.get("productId") as String

        btnToCart.isActivated = false

        val viewedManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
        val recyclerViewed = view.findViewById<RecyclerView>(R.id.rvDetailedViewed)

        recyclerViewed.apply {
            setHasFixedSize(true)

            layoutManager = viewedManager

            adapter = viewedAdapter
        }

        val photosManager = LinearLayoutManager(requireContext())
        photosManager.orientation = LinearLayoutManager.HORIZONTAL

        rvPhotos.apply {
            setHasFixedSize(true)

            layoutManager = photosManager

            adapter = photosAdapter
        }

        presenter.getProduct(productId)
        presenter.getViewed(productId)
    }

    override fun onAddToCart() {
        Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
    }

    override fun showProduct(product: Product) {

        btnToCart.isActivated = true

        val photos = mutableListOf(product.imageUrl)

        product.otherPhotos.forEach {
            photos.add(it)
        }

        photosAdapter.loadItems(photos)

        titleView.text = product.name
        genreTitle.text = "${product.category.mainCategory.name}, ${product.category.name}"

        if (product.discountPercent > 0) {
            fullPriceView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            fullPriceView.text = product.getRoundedFullPrice()
            discountView.text = "- ${product.discountPercent} %"
            discountView.background =
                discountView.resources.getDrawable(R.drawable.sale_layout, null)
        }

        priceView.text = "${product.getRoundedDiscountPrice()} руб"

        btnToCart.setOnClickListener {
            presenter.addToCart(product)
        }

        ivFavorite.background = context?.getDrawable(
            if (product.isFavorite)
                R.drawable.ic_favorite
            else
                R.drawable.ic_unfavorite
        )

        ivFavorite.setOnClickListener {
            onFavoriteClick(product)
        }
    }

    private fun onFavoriteClick(product: Product) {
        if (!product.isFavorite) {
            presenter.addToFavorite(product)
        } else {
            presenter.deleteFromFavorite(product)
        }
    }

    override fun showNetworkError() {
        Toast.makeText(context, "Network Error", Toast.LENGTH_LONG).show()
    }

    override fun showError(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

    override fun showViewed(viewed: MutableList<ViewedProduct>) {
        viewedAdapter.changeItemSource(viewed)
    }

    override fun onFavoriteChanged(product: Product) {
        ivFavorite.background = context?.getDrawable(
            if (product.isFavorite)
                R.drawable.ic_favorite
            else
                R.drawable.ic_unfavorite
        )
    }
}