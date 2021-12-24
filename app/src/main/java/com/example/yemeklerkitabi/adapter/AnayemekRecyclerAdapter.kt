package com.example.yemeklerkitabi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.yemeklerkitabi.R
import com.example.yemeklerkitabi.model.Anayemek
import com.example.yemeklerkitabi.util.gorselIndır
import com.example.yemeklerkitabi.util.placeholderYap
import com.example.yemeklerkitabi.view.AnayemekListeFragmentDirections
import kotlinx.android.synthetic.main.anayemek_recycler_row.view.*

class AnayemekRecyclerAdapter(val anayemekListesi: ArrayList<Anayemek>) : RecyclerView.Adapter<AnayemekRecyclerAdapter.AnayemekViewHolder>() {
    class AnayemekViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnayemekViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.anayemek_recycler_row,parent,false)
        return AnayemekViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnayemekViewHolder, position: Int) {
        holder.itemView.isim4.text = anayemekListesi.get(position).anayemekIsım
        holder.itemView.sure4.text = anayemekListesi.get(position).anayemekSure

        holder.itemView.setOnClickListener {
            val action = AnayemekListeFragmentDirections.actionAnayemekListeFragmentToAnayemekDetayFragment(anayemekListesi.get(position).uuid4)
            Navigation.findNavController(it).navigate(action)
        }

        holder.itemView.imageView4.gorselIndır(anayemekListesi.get(position).anayemekGorsel, placeholderYap(holder.itemView.context))
    }

    override fun getItemCount(): Int {
        return anayemekListesi.size
    }

    fun anayemekListesiniGuncelle(yeniAnayemekListesi: List<Anayemek>){
        anayemekListesi.clear()
        anayemekListesi.addAll(yeniAnayemekListesi)
        notifyDataSetChanged()
    }
}