package com.app.UAS201804004

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class RegisterActivity : AppCompatActivity() {
    lateinit var userDBHelper: DBHelper
    lateinit var inputemail: EditText
    lateinit var inputpass: EditText
    lateinit var inputusername: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val layout :ConstraintLayout = findViewById(R.id.layout)
        layout.setBackgroundResource(R.drawable.bg)

        inputemail = findViewById(R.id.email)
        inputpass = findViewById(R.id.pass)
        inputusername = findViewById(R.id.username)
        userDBHelper = DBHelper(this)
    }

    fun register(view: View){
        var emailin = inputemail.text.toString()
        var passin = inputpass.text.toString()
        var fullnamein = inputusername.text.toString()
        var cekuser = userDBHelper.cekUser(emailin)
        var status = "Gagal"

        if (cekuser == "0") {
            userDBHelper.registerUser(emailin, passin, fullnamein)
            status = "Sukses"
            startActivity(Intent(this, LoginActivity::class.java))
        }

        val toast: Toast = Toast.makeText(applicationContext, status, Toast.LENGTH_SHORT)
        toast.show()
    }

    fun cancelme(view: View){
        startActivity(Intent(this, LoginActivity::class.java))
    }

}