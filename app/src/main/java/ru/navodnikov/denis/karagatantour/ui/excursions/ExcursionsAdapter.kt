package ru.navodnikov.denis.karagatantour.ui.excursions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.flaviofaria.kenburnsview.KenBurnsView
import com.squareup.picasso.Picasso
import ru.navodnikov.denis.domain.entity.Excursion
import ru.navodnikov.denis.karagatantour.R
import java.io.File

class ExcursionsAdapter(
    private val onClickListenerExcursion: OnClickListenerExcursion
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val excursionsList: MutableList<Excursion> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ExcursionsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_conteiner_excursion, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ExcursionsViewHolder -> {
                val excursion: Excursion = excursionsList[position]
                holder.bind(excursionsList[position])
                holder.itemView.setOnClickListener {
                    onClickListenerExcursion.onClick(excursion = excursion)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return excursionsList.size
    }

    fun setItems(excursionsList: List<Excursion>){
        this.excursionsList.clear()
        this.excursionsList.addAll(excursionsList)
        notifyDataSetChanged()
    }

    class ExcursionsViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val kbvExcursion: KenBurnsView = itemView.findViewById(R.id.excursion_kbv)
        private val title: TextView = itemView.findViewById(R.id.excursion_title_text_view)

        fun bind(excursion: Excursion) {
            Picasso.get().load(File(excursion.imageUri)).into(kbvExcursion)
            title.text = itemView.context.getText(excursion.title)
        }
    }

    class OnClickListenerExcursion(val clickListener: (excursion: Excursion) -> Unit) {
        fun onClick(excursion: Excursion) = clickListener(excursion)
    }

}