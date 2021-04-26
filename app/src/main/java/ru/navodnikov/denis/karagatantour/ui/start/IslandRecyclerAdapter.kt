package ru.navodnikov.denis.karagatantour.ui.start

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.navodnikov.denis.domain.entity.Island
import ru.navodnikov.denis.karagatantour.R

class IslandRecyclerAdapter(
    private val onClickListenerIsland: OnClickListenerIsland
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val islandList: MutableList<Island> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return IslandViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_island_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is IslandViewHolder -> {
                val island: Island = islandList[position]
                holder.bind(islandList[position])
                holder.itemView.setOnClickListener {
                    onClickListenerIsland.onClick(island = island)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return islandList.size
    }

    fun setItems(islandList: List<Island>) {
        this.islandList.clear()
        this.islandList.addAll(islandList)
        notifyDataSetChanged()
    }

    class IslandViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val imageIsland: ImageView = itemView.findViewById(R.id.image_island)
        private val titleIsland: TextView = itemView.findViewById(R.id.title_island)
        private val bodyIsland: TextView = itemView.findViewById(R.id.body_island)

        fun bind(island: Island) {
            val context = itemView.context
            imageIsland.setImageResource(island.image)
            titleIsland.text = context.getText(island.title)
            bodyIsland.text = context.getText(island.body)
        }
    }

    class OnClickListenerIsland(val clickListener: (island: Island) -> Unit) {
        fun onClick(island: Island) = clickListener(island)
    }
}