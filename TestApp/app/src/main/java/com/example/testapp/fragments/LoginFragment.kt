package com.example.testapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentTransaction
import com.example.testapp.App
import com.example.testapp.ProductFragment
import com.example.testapp.R
import com.example.testapp.ResisterFragment


class LoginFragment : Fragment() {
    lateinit var progressBar: ProgressBar
    lateinit var emailField: EditText
    lateinit var password: EditText
    lateinit var loginBtn: Button
    lateinit var signUpBtn:TextView


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        initViews(root)

        progressBar.visibility = View.INVISIBLE
        signUpBtn.setOnClickListener {
            val ft: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            ft.replace(R.id.container, ResisterFragment())
            ft.commit()
        }
        loginBtn.setOnClickListener {
            if (emailField.text.toString().isEmpty()) {
                emailField.error = "Enter email "
            } else if(password.text.toString().isEmpty()){
                password.error = "Enter password"
            }else {
               login()
            }
        }
        return root
    }

    private fun initViews(root:View){
        loginBtn = root.findViewById(R.id.register_btn)
        signUpBtn = root.findViewById(R.id.register)
        emailField = root.findViewById(R.id.emailField)
        password = root.findViewById(R.id.passwordField)
        progressBar = root.findViewById(R.id.login_progress_bar)
    }

    fun login(){
        val user = App.db.userDao().findByName(userName = emailField.text.trim().toString())
        if(user!=null){
            if(user.password == password.text.toString()){
                App.storage.isLoggedIn = true
                App.storage.myName = user.firstName+" "+user.lastName
                val ft: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                ft.replace(R.id.container, ProductFragment())
                ft.commit()
            }else{
                Toast.makeText(context,"wrong password",Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(context,"Invalid userName or password",Toast.LENGTH_SHORT).show()
        }
    }
}


