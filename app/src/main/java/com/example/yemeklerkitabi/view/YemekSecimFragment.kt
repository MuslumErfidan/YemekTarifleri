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
import com.example.yemeklerkitabi.adapter.YemekRecyclerAdapter
import com.example.yemeklerkitabi.viewmodel.YemekSecimListesiViewModel
import kotlinx.android.synthetic.main.fragment_yemek_secim.*


class YemekSecimFragment : Fragment() {

    private lateinit var viewModel : YemekSecimListesiViewModel
    private val recyclerYemekAdapter = YemekRecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_yemek_secim, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(YemekSecimListesiViewModel::class.java)
        viewModel.refreshData()

        yemekSecimRecycler.layoutManager = LinearLayoutManager(context)
        yemekSecimRecycler.adapter = recyclerYemekAdapter

        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.yemekler.observe(viewLifecycleOwner, Observer { yemekler ->
            yemekler?.let {
                yemekSecimRecycler.visibility = View.VISIBLE
                recyclerYemekAdapter.yemekSecimListesiniGüncelle(yemekler)
            }
        })

        viewModel.yemekHataMesaji.observe(viewLifecycleOwner, Observer { hata ->
            hata?.let {
                if (it){
                    secimHataMesaji.visibility = View.VISIBLE
                    yemekSecimRecycler.visibility = View.GONE
                } else {
                    secimHataMesaji.visibility = View.GONE
                }
            }
        })

        viewModel.yemekYukleniyor.observe(viewLifecycleOwner, Observer { yukleniyor ->
            yukleniyor?.let {
                if (it){
                    yemekSecimRecycler.visibility = View.GONE
                    secimHataMesaji.visibility = View.GONE
                    secimYukleniyor.visibility = View.VISIBLE
                } else {
                    secimYukleniyor.visibility = View.GONE
                }
            }
        })
    }
}