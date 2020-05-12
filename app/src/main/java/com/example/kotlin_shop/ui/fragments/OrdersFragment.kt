package com.example.kotlin_shop.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_shop.App
import com.example.kotlin_shop.R
import com.example.kotlin_shop.domain.FullOrder
import com.example.kotlin_shop.presentation.OrdersPresenter
import com.example.kotlin_shop.ui.adapters.OrdersAdapter
import com.example.kotlin_shop.ui.interfaces.OrdersView
import kotlinx.android.synthetic.main.fragment_orders.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class OrdersFragment: MvpAppCompatFragment(R.layout.fragment_orders), OrdersView {

    private val ordersAdapter = OrdersAdapter()

    @Inject
    lateinit var presenterProvider: Provider<OrdersPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    init {
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvOrders.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ordersAdapter
        }

        presenter.getOrders()
    }

    override fun showOrders(orders: List<FullOrder>) {
        ordersAdapter.changeItems(orders)
    }
}