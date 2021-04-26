package ru.navodnikov.denis.karagatantour.ui.basket


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.navodnikov.denis.domain.entity.Order
import ru.navodnikov.denis.karagatantour.R
import java.io.File

class OrderRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val orderList: MutableList<Order> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return OrderViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_order_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is OrderViewHolder -> {
                holder.bind(orderList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    fun getOrderByPosition(position: Int): Order{
        return orderList[position]
    }

    fun setItems(orderList: List<Order>) {
        this.orderList.clear()
        this.orderList.addAll(orderList)
        notifyDataSetChanged()
    }

    class OrderViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val orderImageView: ImageView = itemView.findViewById(R.id.order_image)
        private val typeOrderTextView: TextView = itemView.findViewById(R.id.type_order_text)
        private val titleOrderTextView: TextView = itemView.findViewById(R.id.title_order_text)
        private val adultsOrderTextView: TextView = itemView.findViewById(R.id.adults_order_text)
        private val childrenOrderTextView: TextView = itemView.findViewById(R.id.children_order_text)
        private val dateOrderTextView: TextView = itemView.findViewById(R.id.date_order_text)

        fun bind(order: Order) {
            val context = itemView.context
            Picasso.get().load(File(order.imageUri)).into(orderImageView)
            typeOrderTextView.text = order.typeOfOrder
            titleOrderTextView.text = order.title
            adultsOrderTextView.text = order.numberOfAdult
            childrenOrderTextView.text = order.numberOfChildren
            dateOrderTextView.text = order.date


        }
    }
}