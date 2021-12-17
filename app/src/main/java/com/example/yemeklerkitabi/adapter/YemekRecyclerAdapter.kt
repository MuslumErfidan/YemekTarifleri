package com.example.yemeklerkitabi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.yemeklerkitabi.R
import com.example.yemeklerkitabi.model.Yemek
import com.example.yemeklerkitabi.util.gorselIndır
import com.example.yemeklerkitabi.util.placeholderYap
import com.example.yemeklerkitabi.view.YemekSecimFragmentDirections
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

        holder.itemView.setOnClickListener {
            val action = YemekSecimFragmentDirections.actionYemekSecimFragmentToYemekDetayFragment(0)
            Navigation.findNavController(it).navigate(action)
        }

        holder.itemView.imageView.gorselIndır(yemekSecim.get(position).yemekGorsel, placeholderYap(holder.itemView.context))
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