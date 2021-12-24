package com.example.yemeklerkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.yemeklerkitabi.R
import com.example.yemeklerkitabi.util.gorselIndır
import com.example.yemeklerkitabi.util.placeholderYap
import com.example.yemeklerkitabi.viewmodel.SalataDetayViewModel
import kotlinx.android.synthetic.main.fragment_salata_detay.*


class SalataDetayFragment : Fragment() {

    private lateinit var viewModel : SalataDetayViewModel
    private var salataId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_salata_detay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            salataId = SalataDetayFragmentArgs.fromBundle(it).salataId
        }

        viewModel = ViewModelProviders.of(this).get(SalataDetayViewModel::class.java)
        viewModel.roomVerisiniAl(salataId)

        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.salataLiveData.observe(viewLifecycleOwner, Observer { salata ->
            salata?.let {
                salataIsim.text = it.salataIsım
                salataSure.text = it.salataSure
                salataKalori.text = it.salataKalori
                salataMalzemeler.text = it.salataMalzemeler
                salataYapilis.text = it.salataYapilis
                context?.let {
                    salataResim.gorselIndır(salata.salataGorsel, placeholderYap(it))
                }
            }
        })
    }
}