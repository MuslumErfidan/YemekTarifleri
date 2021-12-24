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
import com.example.yemeklerkitabi.adapter.SalataRecyclerAdapter
import com.example.yemeklerkitabi.viewmodel.SalataListesiViewModel
import kotlinx.android.synthetic.main.fragment_salata_liste.*


class SalataListeFragment : Fragment() {

    private lateinit var viewModel : SalataListesiViewModel
    private val recyclerSalataAdapter = SalataRecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_salata_liste, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SalataListesiViewModel::class.java)
        viewModel.refreshData()

        salataSecimRecycler.layoutManager = LinearLayoutManager(context)
        salataSecimRecycler.adapter = recyclerSalataAdapter

        swipeRefreshLayout3.setOnRefreshListener {
            secimYukleniyor3.visibility = View.VISIBLE
            secimHataMesaji3.visibility = View.GONE
            salataSecimRecycler.visibility = View.GONE
            viewModel.refreshFromInternet()
            swipeRefreshLayout3.isRefreshing = false
        }

        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.salatalar.observe(viewLifecycleOwner, Observer { salatalar ->
            salatalar?.let {
                salataSecimRecycler.visibility = View.VISIBLE
                recyclerSalataAdapter.salataListesiniGuncelle(salatalar)
            }
        })

        viewModel.salataHataMesaji.observe(viewLifecycleOwner, Observer { hata ->
            hata?.let {
                if (it){
                    secimHataMesaji3.visibility = View.VISIBLE
                    salataSecimRecycler.visibility = View.GONE
                } else{
                    secimHataMesaji3.visibility = View.GONE
                }
            }
        })

        viewModel.salataYukleniyor.observe(viewLifecycleOwner, Observer { yukleniyor ->
            yukleniyor?.let {
                if (it){
                    salataSecimRecycler.visibility = View.GONE
                    secimHataMesaji3.visibility = View.GONE
                    secimYukleniyor3.visibility = View.VISIBLE
                } else{
                    secimYukleniyor3.visibility = View.GONE
                }
            }
        })
    }
}