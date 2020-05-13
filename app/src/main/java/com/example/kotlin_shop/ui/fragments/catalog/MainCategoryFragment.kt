package com.example.kotlin_shop.ui.fragments.catalog

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_shop.App
import com.example.kotlin_shop.R
import com.example.kotlin_shop.domain.MainCategory
import com.example.kotlin_shop.presentation.MainCategoryPresenter
import com.example.kotlin_shop.ui.MainActivity
import com.example.kotlin_shop.ui.adapters.catalog.MainCategoryAdapter
import com.example.kotlin_shop.ui.interfaces.MainCategoryView
import kotlinx.android.synthetic.main.fragment_main_category.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class MainCategoryFragment :
    MvpAppCompatFragment(R.layout.fragment_main_category), MainCategoryView {

    @Inject
    lateinit var presenterProvider: Provider<MainCategoryPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    private val mainCategoryAdapter = MainCategoryAdapter()

    init {
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.getMainGenres()

        rvMainCategory.apply {
            setHasFixedSize(true)

            adapter = mainCategoryAdapter

            layoutManager = LinearLayoutManager(context)
        }

        clMainCategoryHeader.setOnClickListener {
            val activity = context as MainActivity

            val action =
                MainCategoryFragmentDirections.actionNavigationMainCategoryToNavigationCatalog(
                    null,
                    null
                )

            activity.navigate(action)
        }
    }

    override fun showGenres(categories: List<MainCategory>) {
        mainCategoryAdapter.loadData(categories.map { it.name })
    }

    override fun showNetworkError() {
        Toast.makeText(context, "Network Error", Toast.LENGTH_LONG).show()
    }
}