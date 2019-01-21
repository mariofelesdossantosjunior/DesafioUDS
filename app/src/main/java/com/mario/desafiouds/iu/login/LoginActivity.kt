package com.mario.desafiouds.iu.login

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.mario.desafiouds.R
import com.mario.desafiouds.iu.main.MainActivity
import com.mario.desafiouds.iu.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.design.textInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_login.setOnClickListener {
            val email = edit_email_login.text.toString()
            val password = edit_password_login.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                container_main.snackbar(R.string.auth)
                showProgress(true)
                viewModel.login(email, password) { isSuccess ->
                    if (isSuccess) {
                        startActivity<MainActivity>()
                        finish()
                    } else {
                        showProgress(false)
                        showError()
                    }
                }
            } else {
                showError()
            }
        }

        button_register.setOnClickListener {
            startActivity<RegisterActivity>()
            finish()
        }

        text_redefine_password.setOnClickListener {
            showDialogRedefinePassword()
        }
    }

    /**
     * Função responsavel por gerar dialog de redefinir a senha do usuario
     */
    private fun showDialogRedefinePassword() {

        alert {
            var etEmail: EditText? = null

            title = getString(R.string.app_name)

            customView {
                verticalLayout {
                    padding = 5
                    textInputLayout {
                        etEmail = editText {
                            width = maxWidth
                            hint = getString(R.string.type_email)
                            inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                        }
                    }

                    positiveButton(getString(R.string.recovery)) {
                        val email = etEmail?.text.toString()

                        if (email.isNotEmpty()) {
                            viewModel.redefinePassword(email){
                                container_main.snackbar(R.string.send_email)
                            }
                        } else {
                            container_main.snackbar(R.string.email_invalid)
                        }
                    }

                }
            }
        }.show()
    }

    private fun showError() {
        edit_email_login.error = getString(R.string.email_invalid)
        edit_password_login.error = getString(R.string.password_invalid)
    }

    override fun onStart() {
        super.onStart()
        viewModel.getUserAuth()?.let {
            updateUI()
        }

    }

    private fun showProgress(visible: Boolean) {
        if (visible) progressBar_login.visibility = View.VISIBLE else progressBar_login.visibility = View.GONE
    }

    private fun updateUI() {
        startActivity<MainActivity>()
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
