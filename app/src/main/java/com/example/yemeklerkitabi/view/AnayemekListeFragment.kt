package com.example.yemeklerkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yemeklerkitabi.R
import com.example.yemeklerkitabi.adapter.AnayemekRecyclerAdapter
import com.example.yemeklerkitabi.viewmodel.AnayemekListesiViewModel
import kotlinx.android.synthetic.main.fragment_anayemek_liste.*
import kotlinx.android.synthetic.main.fragment_salata_liste.*


class AnayemekListeFragment : Fragment() {

    private lateinit var viewModel : AnayemekListesiViewModel
    private val recyclerAnayemekAdapter = AnayemekRecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anayemek_liste, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(AnayemekListesiViewModel::class.java)
        viewModel.refreshData()

        anayemekSecimRecycler.layoutManager = LinearLayoutManager(context)
        anayemekSecimRecycler.adapter = recyclerAnayemekAdapter

        swipeRefreshLayout4.setOnRefreshListener {
            secimYukleniyor4.visibility = View.VISIBLE
            secimHataMesaji4.visibility = View.GONE
            anayemekSecimRecycler.visibility = View.GONE
            viewModel.refreshFromInternet()
            swipeRefreshLayout4.isRefreshing = false
        }

        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.anayemekler.observe(viewLifecycleOwner, Observer { anayemekler ->
            anayemekler?.let {
                anayemekSecimRecycler.visibility = View.VISIBLE
                recyclerAnayemekAdapter.anayemekListesiniGuncelle(anayemekler)
            }
        })

        viewModel.anayemekHataMesaji.observe(viewLifecycleOwner, Observer { hata ->
            hata?.let {
                if (it){
                    secimHataMesaji4.visibility = View.VISIBLE
                    anayemekSecimRecycler.visibility = View.GONE
                } else{
                    secimHataMesaji4.visibility = View.GONE
                }
            }
        })

        viewModel.anayemekYukleniyor.observe(viewLifecycleOwner, Observer { yukleniyor ->
            yukleniyor?.let {
                if (it){
                    anayemekSecimRecycler.visibility = View.GONE
                    secimHataMesaji4.visibility = View.GONE
                    secimYukleniyor4.visibility = View.VISIBLE
                } else{
                    secimYukleniyor4.visibility = View.GONE
                }
            }
        })
    }
}