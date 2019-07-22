package di2k.lintaspena.dashboard

import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import di2k.lintaspena.instagramlayout.R

class LoginActivity: AppCompatActivity() {
    private lateinit var mEmailView: AutoCompleteTextView
    private lateinit var mPasswordView: EditText
    private lateinit var mProgressView: View
    private lateinit var mLoginFormView: View

    @Override
    fun onCreated(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)//Created by 디딬 Didik M. Hadiningrat on 22 July 2019
        setContentView(R.layout.activity_login)
    }

}