package com.example.yemeklerkitabi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.yemeklerkitabi.R
import com.example.yemeklerkitabi.model.Tatli
import com.example.yemeklerkitabi.util.gorselIndır
import com.example.yemeklerkitabi.util.placeholderYap
import com.example.yemeklerkitabi.view.TatliListeFragmentDirections
import kotlinx.android.synthetic.main.tatli_recycler_row.view.*

class TatliRecyclerAdapter(val tatliListesi: ArrayList<Tatli>) : RecyclerView.Adapter<TatliRecyclerAdapter.TatliViewHolder>() {
    class TatliViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TatliViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.tatli_recycler_row,parent,false)
        return TatliViewHolder(view)
    }

    override fun onBindViewHolder(holder: TatliViewHolder, position: Int) {
        holder.itemView.isim2.text = tatliListesi.get(position).tatliIsim
        holder.itemView.sure2.text = tatliListesi.get(position).tatliSure

        holder.itemView.setOnClickListener {
            val action = TatliListeFragmentDirections.actionTatliListeFragmentToTatliDetayFragment(tatliListesi.get(position).uuid2)
            Navigation.findNavController(it).navigate(action)
        }

        holder.itemView.imageView2.gorselIndır(tatliListesi.get(position).tatliGorsel, placeholderYap(holder.itemView.context))
    }

    override fun getItemCount(): Int {
        return tatliListesi.size
    }

    fun tatliListesiniGuncelle(yeniTatliListesi: List<Tatli>){
        tatliListesi.clear()
        tatliListesi.addAll(yeniTatliListesi)
        notifyDataSetChanged()
    }
}