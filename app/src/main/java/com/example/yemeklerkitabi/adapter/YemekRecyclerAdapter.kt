package com.example.yemeklerkitabi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yemeklerkitabi.R
import com.example.yemeklerkitabi.model.Yemek
import kotlinx.android.synthetic.main.yemek_recycler_row.view.*

class YemekRecyclerAdapter(val yemekSecim : ArrayList<Yemek>) : RecyclerView.Adapter<YemekRecyclerAdapter.YemekViewHolder>() {

    class YemekViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.yemek_recycler_row, parent, false)
        return YemekViewHolder(view)
    }

    override fun onBindViewHolder(holder: YemekViewHolder, position: Int) {
        holder.itemView.isim.text = yemekSecim.get(position).yemekIsim
        holder.itemView.sure.text = yemekSecim.get(position).yemekSure
        //gorsel kısmı eklenecek
    }

    override fun getItemCount(): Int {
        return yemekSecim.size
    }

    fun yemekSecimListesiniGüncelle(yeniYemekSecimListesi: List<Yemek>) {
        yemekSecim.clear()
        yemekSecim.addAll(yeniYemekSecimListesi)
        notifyDataSetChanged()
    }
}