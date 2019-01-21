package com.mario.desafiouds.iu.schedules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mario.desafiouds.R
import com.mario.desafiouds.data.entity.Schedule
import com.mario.desafiouds.data.entity.StatusSchedule
import com.mario.desafiouds.iu.add.AddScheduleFragment
import com.mario.desafiouds.util.replaceFragment
import kotlinx.android.synthetic.main.schedule_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScheduleFragment
    : Fragment(),
    ScheduleAdapter.OnItemClickListener {

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    var status: StatusSchedule = StatusSchedule.FECHADO

    private val adapter: ScheduleAdapter by lazy {
        ScheduleAdapter(arrayListOf(), this)
    }
    private val viewModel: ScheduleViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.schedule_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        setupTitle(status)
        setupRecycle()
        loadItensDataBase()

        float_button_add_schedule.setOnClickListener {
            val compatActivity = activity as AppCompatActivity
            compatActivity.replaceFragment(AddScheduleFragment(), R.id.content)
        }
    }

    /**
     * Função responsavel por gerenciar o titulo
     */
    private fun setupTitle(status: StatusSchedule) {
        txt_status_schedules.text = if (status == StatusSchedule.ABERTO) getString(R.string.open_schedule)
        else getString(R.string.close_schedule)
    }

    /**
     * Função responsavel por carregar as pautas da database por status
     */
    private fun loadItensDataBase() {
        viewModel.getAllSchedulesByStatus(status).observe(this, Observer<List<Schedule>> { schedules ->
            schedules?.let {
                populateScheduleList(schedules)
            }
        })
    }

    /**
     * Função responsavle por atualizar a listView
     */
    private fun populateScheduleList(schedules: List<Schedule>) {
        adapter.clearItem()
        adapter.addItems(schedules)
    }

    /**
     * Função responsavel por configurar a ListView
     */
    private fun setupRecycle() {
        recycle_schedules.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycle_schedules.setHasFixedSize(true)
        recycle_schedules.adapter = adapter
    }

    /**
     * Função responsavel pelo click da ListView
     */
    override fun onItemClick(schedule: Schedule, itemView: View) {
        schedule.expanded = false

        if (schedule.status == StatusSchedule.ABERTO) {
            schedule.status = StatusSchedule.FECHADO
        } else {
            schedule.status = StatusSchedule.ABERTO
        }

        viewModel.updateSchedule(schedule)
        loadItensDataBase()

    }

}
