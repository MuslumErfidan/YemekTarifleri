package com.example.yemeklerkitabi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.yemeklerkitabi.R
import com.example.yemeklerkitabi.model.Salata
import com.example.yemeklerkitabi.util.gorselIndır
import com.example.yemeklerkitabi.util.placeholderYap
import com.example.yemeklerkitabi.view.SalataListeFragmentDirections
import kotlinx.android.synthetic.main.salata_recycler_row.view.*

class SalataRecyclerAdapter(val salataListesi : ArrayList<Salata>) : RecyclerView.Adapter<SalataRecyclerAdapter.SalataViewHolder>() {
    class SalataViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.salata_recycler_row,parent,false)
        return SalataViewHolder(view)
    }

    override fun onBindViewHolder(holder: SalataViewHolder, position: Int) {
        holder.itemView.isim3.text = salataListesi.get(position).salataIsım
        holder.itemView.sure3.text = salataListesi.get(position).salataSure

        holder.itemView.setOnClickListener {
            val action = SalataListeFragmentDirections.actionSalataListeFragmentToSalataDetayFragment(salataListesi.get(position).uuid3)
            Navigation.findNavController(it).navigate(action)
        }

        holder.itemView.imageView3.gorselIndır(salataListesi.get(position).salataGorsel, placeholderYap(holder.itemView.context))
    }

    override fun getItemCount(): Int {
        return salataListesi.size
    }

    fun salataListesiniGuncelle(yeniSalataListesi: List<Salata>){
        salataListesi.clear()
        salataListesi.addAll(yeniSalataListesi)
        notifyDataSetChanged()
    }
}