package com.example.kotlin_shop.ui.fragments

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlin_shop.App
import com.example.kotlin_shop.R
import com.example.kotlin_shop.domain.FavoriteProduct
import com.example.kotlin_shop.presentation.FavoritePresenter
import com.example.kotlin_shop.ui.adapters.FavoriteAdapter
import com.example.kotlin_shop.ui.interfaces.FavoriteView
import kotlinx.android.synthetic.main.fragment_favorites.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class FavoriteFragment : MvpAppCompatFragment(R.layout.fragment_favorites), FavoriteView {

    companion object{
        private var recyclerState: Parcelable? = null
    }

    @Inject
    lateinit var providerPresenter: Provider<FavoritePresenter>

    private val presenter by moxyPresenter {providerPresenter.get()}

    init {
        App.appComponent.inject(this)
    }


    private val favoriteAdapter = FavoriteAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rvFavoritesList.apply {
            setHasFixedSize(true)

            layoutManager = GridLayoutManager(context, 2)

            adapter = favoriteAdapter
        }

        presenter.getFavorites()
    }

    override fun onPause() {
        super.onPause()

        recyclerState = rvFavoritesList.layoutManager!!.onSaveInstanceState()
    }

    override fun onStart() {
        super.onStart()

        rvFavoritesList.layoutManager!!.onRestoreInstanceState(recyclerState)
    }

    override fun showFavorites(favorites: List<FavoriteProduct>) {
        favoriteAdapter.changeItems(favorites)
    }
}
