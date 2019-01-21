package com.mario.desafiouds.iu.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mario.desafiouds.R
import com.mario.desafiouds.data.entity.StatusSchedule
import com.mario.desafiouds.iu.schedules.ScheduleFragment
import com.mario.desafiouds.util.replaceFragment
import kotlinx.android.synthetic.main.add_schedule_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddScheduleFragment : Fragment() {

    companion object {
        fun newInstance() = AddScheduleFragment()
    }

    private val viewModel: AddScheduleViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_schedule_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel.getUserLoggin()?.let {
            edit_author_add.setText(it)
        }

        button_save_add.setOnClickListener {
            val title = edit_title_add.text.toString()
            val description = edit_description_add.text.toString()
            val detail = edit_detail_add.text.toString()
            val author = edit_author_add.text.toString()

            when {
                title.isEmpty() -> edit_title_add.error = getString(R.string.required_fild)
                description.isEmpty() -> edit_description_add.error = getString(R.string.required_fild)
                detail.isEmpty() -> edit_detail_add.error = getString(R.string.required_fild)
                author.isEmpty() -> edit_author_add.error = getString(R.string.required_fild)
                else -> {
                    viewModel.addSchedule(title, description, detail, author) {
                        navigeteMain()
                    }
                }
            }
        }

        button_cancel_add.setOnClickListener {
            navigeteMain()
        }
    }

    /**
     * Função responsavel pela navegação
     */
    private fun navigeteMain() {
        val compatActivity = activity as AppCompatActivity
        compatActivity.replaceFragment(ScheduleFragment().apply {
            status = StatusSchedule.ABERTO
        }, R.id.content)
    }

}
