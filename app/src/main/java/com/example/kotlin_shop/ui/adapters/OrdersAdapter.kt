package com.example.kotlin_shop.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_shop.R
import com.example.kotlin_shop.domain.FullOrder
import com.example.kotlin_shop.domain.Order
import java.text.DecimalFormat

class OrdersAdapter: RecyclerView.Adapter<OrdersAdapter.Holder>() {
    inner class Holder(private val view: View): RecyclerView.ViewHolder(view){
        fun bind(order: FullOrder){
            view.findViewById<TextView>(R.id.tvOrderCustomer).text =
                "${order.userLastName} ${order.userFirstName}"
            view.findViewById<TextView>(R.id.tvOrderPhone).text = order.userPhone
            val fullPrice = order.items.map { it.product.calcDiscountPrice() * it.count }.sum()
            val format = DecimalFormat("#.##")
            view.findViewById<TextView>(R.id.tvOrderTotal).text =
                "${format.format(fullPrice).toString()} руб"

            view.findViewById<TextView>(R.id.tvOrderPayment).text =
                if(order.paymentType == Order.PaymentType.CashOnReceiving)
                    "наличными при получении"
                else
                    "картой онлайн"
            view.findViewById<RecyclerView>(R.id.rvOrderItems).apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = OrderItemAdapter(order.items)
            }
        }
    }

    private var dataSet: List<FullOrder> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_layout, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val order = dataSet[position]

        holder.bind(order)
    }

    override fun getItemCount(): Int = dataSet.size

    fun changeItems(orders: List<FullOrder>){
        dataSet = orders
        notifyDataSetChanged()
    }
}