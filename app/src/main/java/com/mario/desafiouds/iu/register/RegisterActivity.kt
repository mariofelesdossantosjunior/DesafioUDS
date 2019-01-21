package com.mario.desafiouds.iu.register

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mario.desafiouds.R
import com.mario.desafiouds.iu.login.LoginActivity
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegisterActivity : AppCompatActivity() {

    private val viewModel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        button_save_register.setOnClickListener {

            val name = edit_name_register.text.toString()
            val email = edit_email_register.text.toString()
            val password = edit_password_register.text.toString()

            when {
                name.isEmpty() -> edit_name_register.error = getString(R.string.required_fild)
                email.isEmpty()-> edit_email_register.error = getString(R.string.required_fild)
                password.isEmpty() -> edit_password_register.error = getString(R.string.required_fild)
                password.length < 6 -> edit_password_register.error = getString(R.string.validate_caracter)
                else -> {

                    showProgress(true)

                    viewModel.register(email, password, name) { isSucess, isError ->

                        if (isSucess) {
                            showProgress(false)
                            startActivity<LoginActivity>()
                            finish()
                        } else {
                            isError?.let {
                                showProgress(false)
                                container_register.snackbar(it.message.toString())
                            }
                        }
                    }
                }
            }

        }

        button_cancel_register.setOnClickListener {
            startActivity<LoginActivity>()
            finish()
        }
    }

    private fun showProgress(visible: Boolean) {
        if (visible) progressBar_register.visibility = View.VISIBLE else progressBar_register.visibility = View.GONE
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity<LoginActivity>()
        finish()
    }
}
