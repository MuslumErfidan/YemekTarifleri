package com.example.yemeklerkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yemeklerkitabi.R
import com.example.yemeklerkitabi.adapter.TatliRecyclerAdapter
import com.example.yemeklerkitabi.viewmodel.TatliListesiViewModel
import kotlinx.android.synthetic.main.fragment_tatli_liste.*


class TatliListeFragment : Fragment() {

    private lateinit var viewModel : TatliListesiViewModel
    private val recyclerTatliAdapter = TatliRecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tatli_liste, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(TatliListesiViewModel::class.java)
        viewModel.refreshData()

        tatliSecimRecycler.layoutManager = LinearLayoutManager(context)
        tatliSecimRecycler.adapter = recyclerTatliAdapter

        swipeRefreshLayout2.setOnRefreshListener {
            secimYukleniyor2.visibility = View.VISIBLE
            secimHataMesaji2.visibility = View.GONE
            tatliSecimRecycler.visibility = View.GONE
            viewModel.refreshFromInternet()
            swipeRefreshLayout2.isRefreshing = false
        }

        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.tatlilar.observe(viewLifecycleOwner, Observer { tatlilar ->
            tatlilar?.let {
                tatliSecimRecycler.visibility = View.VISIBLE
                recyclerTatliAdapter.tatliListesiniGuncelle(tatlilar)
            }
        })

        viewModel.tatliHataMesaji.observe(viewLifecycleOwner, Observer { hata ->
            hata?.let {
                if (it){
                    secimHataMesaji2.visibility = View.VISIBLE
                    tatliSecimRecycler.visibility = View.GONE
                } else {
                    secimHataMesaji2.visibility = View.GONE
                }
            }
        })

        viewModel.tatliYukleniyor.observe(viewLifecycleOwner, Observer { yukleniyor ->
            yukleniyor?.let {
                if (it){
                    tatliSecimRecycler.visibility = View.GONE
                    secimHataMesaji2.visibility = View.GONE
                    secimYukleniyor2.visibility = View.VISIBLE
                } else {
                    secimYukleniyor2.visibility = View.GONE
                }
            }
        })
    }
}