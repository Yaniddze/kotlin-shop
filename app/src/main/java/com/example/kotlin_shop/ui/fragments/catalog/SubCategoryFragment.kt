package com.example.kotlin_shop.ui.fragments.catalog

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_shop.App
import com.example.kotlin_shop.R
import com.example.kotlin_shop.domain.SubCategory
import com.example.kotlin_shop.presentation.SubCategoryPresenter
import com.example.kotlin_shop.ui.MainActivity
import com.example.kotlin_shop.ui.adapters.catalog.MainCategoryAdapter
import com.example.kotlin_shop.ui.adapters.catalog.SubCategoryAdapter
import com.example.kotlin_shop.ui.interfaces.SubCategoryView
import kotlinx.android.synthetic.main.fragment_sub_category.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class SubCategoryFragment : MvpAppCompatFragment(R.layout.fragment_sub_category), SubCategoryView {

    @Inject
    lateinit var presenterProvider: Provider<SubCategoryPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    init {
        App.appComponent.inject(this)
    }

    private val categoryAdapter = SubCategoryAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainCategory = requireArguments().get("main_category") as String

        tvCatalogSubCategoryMainCategoryName.text = "Основная категория: $mainCategory"

        rvSubCategory.apply {
            setHasFixedSize(true)

            adapter = categoryAdapter

            layoutManager = LinearLayoutManager(context)
        }

        presenter.getSubCategory(mainCategory)

        clSubCategoryHeader.setOnClickListener {
            val activity = context as MainActivity

            val action =
                SubCategoryFragmentDirections.actionNavigationSubCategoryToNavigationCatalog(
                    mainCategory,
                    null
                )

            action.mainCategory = mainCategory

            activity.navigate(action)
        }
    }

    override fun showGenres(categories: List<SubCategory>) {
        categoryAdapter.loadData(categories.map { it.name })
    }

    override fun showNetworkError() {
        Toast.makeText(context, "Network error", Toast.LENGTH_LONG).show()
    }
}