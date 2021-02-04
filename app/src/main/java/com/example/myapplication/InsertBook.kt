package com.app.UAS201804004

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout

class InsertBook : AppCompatActivity() {
    lateinit var userDBHelper: DBHelper
    lateinit var in_title: EditText
    lateinit var in_auth: EditText
    lateinit var in_price: EditText
    lateinit var in_desc: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        val layout: LinearLayout =findViewById(R.id.layout)
        layout.setBackgroundResource(R.drawable.bg)

        in_title = findViewById(R.id.in_title)
        in_auth = findViewById(R.id.in_auth)
        in_price = findViewById(R.id.in_price)
        in_desc = findViewById(R.id.in_desc)
        userDBHelper = DBHelper(this)
    }

    fun addData(v: View) {
        var title = in_title.text.toString()
        var auth = in_auth.text.toString()
        var price = in_price.text.toString()
        var desc = in_desc.text.toString()
        userDBHelper.insertBook(title, auth, price, desc)
        startActivity(Intent(this, Home::class.java))

    }

    fun showAll(v: View) {
        var pindah = Intent(this, Home::class.java)
        startActivity(pindah)
    }
}