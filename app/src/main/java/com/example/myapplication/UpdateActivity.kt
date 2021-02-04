package com.app.UAS201804004

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout

class UpdateActivity : AppCompatActivity() {
    lateinit var userDBHelper: DBHelper
    lateinit var up_title: EditText
    lateinit var up_auth: EditText
    lateinit var up_price: EditText
    lateinit var up_desc: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val layout: LinearLayout =findViewById(R.id.layout)
        layout.setBackgroundResource(R.drawable.bg)

        up_title = findViewById(R.id.up_title)
        up_auth = findViewById(R.id.up_auth)
        up_price = findViewById(R.id.up_price)
        up_desc = findViewById(R.id.up_desc)
        userDBHelper = DBHelper(this)

        val bundle = intent.extras
        if (bundle != null) {
            up_title.setText(bundle.getString("title"))
            up_auth.setText(bundle.getString("auth"))
            up_price.setText(bundle.getString("price"))
            up_desc.setText(bundle.getString("desc"))
        }
        userDBHelper = DBHelper(this)
    }

    fun updateData(v: View){
        var title = up_title.text.toString()
        var auth = up_auth.text.toString()
        var price = up_price.text.toString()
        var desc = up_desc.text.toString()

        userDBHelper.updateData(title, auth, price, desc)
        startActivity(Intent(this, Home::class.java))
    }

    fun cancelData(v: View){
        startActivity(Intent(this, Home::class.java))
    }

}
