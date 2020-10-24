package com.example.testapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class ResisterFragment : Fragment() {
    lateinit var firstNameField:EditText
    lateinit var lastNameField:EditText
    lateinit var userNameField:EditText
    lateinit var passwordField:EditText
    lateinit var emailField:EditText
    lateinit var agreeField:CheckBox
    lateinit var registerBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =inflater.inflate(R.layout.fragment_resister, container, false)
        initView(root)
        agreeField.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                registerBtn.isEnabled = true
                registerBtn.setBackgroundResource(R.drawable.gradient)
            }else{
                registerBtn.isEnabled = false
                registerBtn.setBackgroundResource(R.drawable.disable_btn_bg)
            }
        }
        registerBtn.setOnClickListener {
            val firstName = firstNameField.text.toString()
            val lastName = lastNameField.text.toString()
            val userName = userNameField.text.toString()
            val email = emailField.text.toString()
            val password = passwordField.text.toString()
            register(firstName,lastName,userName,email,password)

        }
        return root
    }
    private fun initView(root:View){
        firstNameField = root.findViewById(R.id.first_name_field)
        lastNameField = root.findViewById(R.id.last_name_field)
        userNameField = root.findViewById(R.id.username_field)
        emailField   =  root.findViewById(R.id.email_field)
        passwordField = root.findViewById(R.id.password_field)
        agreeField = root.findViewById(R.id.checkBox)
        registerBtn = root.findViewById(R.id.register_btn)
        registerBtn.isEnabled = false
    }
    private fun register(firstName:String, lastName:String, userName:String, email:String, password:String){
        if(firstName.isEmpty()){
            firstNameField.error = "enter first name"
        }else if(lastName.isEmpty()){
            lastNameField.error = "enter last name"

        }
        else if(userName.isEmpty()){
            userNameField.error = "enter username"

        }
        else if(email.isEmpty()){
            emailField.error = "enter email"

        }
        else if(password.isEmpty()){
            passwordField.error = "enter password"

        }
        else if(password.length < 5){
            passwordField.error = "password must contain more than 5 letter"

        }else if(!emailField.text.toString().trim().matches(emailPattern.toRegex())){
            emailField.error = "enter valid email"
        }else{
            val user = User(null, firstName, lastName, userName, email, password)
            App.db.userDao().insertAll(user)
            App.storage.apply {
                isLoggedIn = true
                myName = "$firstName $lastName"
                myEmail = email
            }
            val profileUpdateIntent = Intent(BROADCAST_PROFILE_UPDATE)
            LocalBroadcastManager.getInstance(requireActivity()).sendBroadcast(profileUpdateIntent)
            val ft: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            ft.replace(R.id.container, ProductFragment())
            ft.commit()
        }
    }

}