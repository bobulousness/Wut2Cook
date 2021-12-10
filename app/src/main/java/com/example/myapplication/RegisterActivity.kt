package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.RegisterBinding

class RegisterActivity: AppCompatActivity() {
    private lateinit var reg: RegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        reg = RegisterBinding.inflate(layoutInflater)
        setContentView(reg.root)

        val dbh = DatabaseHelper(this)
        val name = reg.etFullname
        val email = reg.etEmail
        val pass = reg.etPassword

        dbh.fillIngredients(this)
        dbh.fillFilters(this)


        reg.submit.setOnClickListener{
            dbh.addUser(name.text.toString(), email.text.toString(), pass.text.toString())
            startActivity(Intent(this,LoginPageActivity::class.java))
        }

    }
}