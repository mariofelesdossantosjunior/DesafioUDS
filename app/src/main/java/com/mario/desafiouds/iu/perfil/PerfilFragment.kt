package com.mario.desafiouds.iu.perfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mario.desafiouds.R
import com.mario.desafiouds.iu.login.LoginActivity
import kotlinx.android.synthetic.main.perfil_fragment.*
import org.jetbrains.anko.support.v4.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class PerfilFragment : Fragment() {

    companion object {
        fun newInstance() = PerfilFragment()
    }

    private val viewModel: PerfilViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.perfil_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel.getUserLogged()?.let {
            txt_name_perfil.text = it.displayName
            txt_email_perfil.text = it.email
        }

        button_logout_perfil.setOnClickListener {
            viewModel.logoutAuth()
            startActivity<LoginActivity>()
            activity?.finish()
        }
    }


}
