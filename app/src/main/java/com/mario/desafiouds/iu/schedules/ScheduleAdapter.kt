package com.mario.desafiouds.iu.schedules

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mario.desafiouds.R
import com.mario.desafiouds.data.entity.Schedule
import com.mario.desafiouds.data.entity.StatusSchedule
import kotlinx.android.synthetic.main.item_schedule.view.*


class ScheduleAdapter(
    private val items: ArrayList<Schedule>,
    private val clickListener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**
     * Função responsavel por notifica o click de item da list para a view
     */
    interface OnItemClickListener {
        fun onItemClick(schedule: Schedule, itemView: View)
    }

    /**
     * Função responsavel por remover todos itens da listView
     */
    fun clearItem() {
        items.clear()
        notifyDataSetChanged()
    }

    /**
     * Função responsavel por adicioinar todos itens da listView
     */
    fun addItems(itemsToAdd: List<Schedule>) {
        items.addAll(itemsToAdd)
        notifyDataSetChanged()
    }

    /**
     * Função responsavel por colapsar todas as view
     * menos a selecionada
     */
    fun colapseAll(schedule: Schedule) {
        items.filterNot { it == schedule }.forEach {
            it.expanded = false
            notifyDataSetChanged()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_schedule, parent, false)
        return ViewHolder(view)
    }


    /**
     * Função responsavel pelo bind das view
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val schedule = items[position]

        (holder as ViewHolder).bind(schedule, clickListener)

        holder.itemView.setOnClickListener {
            colapseAll(schedule)
            val expanded = schedule.expanded
            schedule.expanded = !expanded
            notifyItemChanged(position)
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }


    /**
     * Classe responsavel pelo bind e pelo click no adapter
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(schedule: Schedule, listener: OnItemClickListener) =
            with(itemView) {
                text_title_schedule_open.text = """${context.getString(R.string.title)}: ${schedule.title}"""
                txt_description_schedule_open.text =
                        "${context.getString(R.string.description)}: ${schedule.description}"
                sub_txt_detail_schedule_open.text = "${context.getString(R.string.detail)}: ${schedule.detail}"
                sub_txt_author_schedule_open.text = "${context.getString(R.string.autor)}: ${schedule.author}"

                val expanded = schedule.expanded
                sub_item.visibility = if (expanded) View.VISIBLE else View.GONE

                if (schedule.status == StatusSchedule.ABERTO) {
                    button_close_schedule.visibility = View.VISIBLE
                    button_reopen_schedule.visibility = View.GONE
                } else {
                    button_reopen_schedule.visibility = View.VISIBLE
                    button_close_schedule.visibility = View.GONE
                }

                button_close_schedule.setOnClickListener {
                    listener.onItemClick(schedule, it)
                }

                button_reopen_schedule.setOnClickListener {
                    listener.onItemClick(schedule, it)
                }
            }

    }

}
