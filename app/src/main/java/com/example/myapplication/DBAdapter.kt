package com.app.UAS201804004

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DBAdapter (private val listData: ArrayList<DBModel>): RecyclerView.Adapter<DBAdapter.CardViewHolder>() {
    inner class CardViewHolder(itemV : View): RecyclerView.ViewHolder(itemV) {
        var v_title: TextView = itemV.findViewById(R.id.v_title)
        var v_price: TextView = itemV.findViewById(R.id.v_price)
        var v_desc: TextView = itemV.findViewById(R.id.v_desc)
        var v_auth: TextView = itemV.findViewById(R.id.v_auth)
        var btn_update: Button = itemV.findViewById(R.id.btn_update)
        var btn_delete: Button = itemV.findViewById(R.id.btn_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val data = listData[position]
        holder.v_title.text = data.title
        holder.v_auth.text = data.author
        holder.v_price.text = data.price
        holder.v_desc.text = data.desc

        holder.btn_delete.setOnClickListener {
            var adapterDBHelper: DBHelper
            adapterDBHelper = DBHelper(holder.itemView.context)
            adapterDBHelper.deleteData(data.title)
            listData.removeAt(position)
            notifyDataSetChanged()
        }

        holder.btn_update.setOnClickListener {
            val intentUpd = Intent(holder.itemView.context, UpdateActivity::class.java)
            val bundle = Bundle()
            bundle.putString("title", data.title)
            bundle.putString("auth", data.author)
            bundle.putString("price", data.price)
            bundle.putString("desc", data.desc)

            intentUpd.putExtras(bundle)
            holder.itemView.context.startActivity(intentUpd)

        }
    }

}