package com.app.UAS201804004

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Home : AppCompatActivity() {
    private lateinit var rv_tampilan: RecyclerView
    lateinit var userDBHelper: DBHelper
    private var list: ArrayList<DBModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val layout: ConstraintLayout = findViewById(R.id.layout)
        layout.setBackgroundResource(R.drawable.bg)

        val btn_add :FloatingActionButton = findViewById(R.id.btn_add)
        btn_add.setOnClickListener {
            startActivity(Intent(this, InsertBook::class.java))
        }

        rv_tampilan = findViewById(R.id.rv_tampil)
        rv_tampilan.setHasFixedSize(true)
        userDBHelper = DBHelper(this)
        list.addAll(userDBHelper.fullData())
        rv_tampilan.layoutManager = LinearLayoutManager(this)

        var cardData = DBAdapter(list)
        rv_tampilan.adapter = cardData
    }
}