package com.example.yemeklerkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.yemeklerkitabi.R
import com.example.yemeklerkitabi.viewmodel.AnayemekDetayViewModel
import kotlinx.android.synthetic.main.fragment_anayemek_detay.*


class AnayemekDetayFragment : Fragment() {

    private lateinit var viewModel : AnayemekDetayViewModel
    private var anayemekId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anayemek_detay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(AnayemekDetayViewModel::class.java)
        viewModel.roomVerisiniAl()

        arguments?.let {
            anayemekId = AnayemekDetayFragmentArgs.fromBundle(it).anayemekId
        }

        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.anayemekLiveData.observe(viewLifecycleOwner, Observer { anayemek ->
            anayemek?.let {
                anayemekIsim.text = it.anayemekIsım
                anayemekSure.text = it.anayemekSure
                anayemekKalori.text = it.anayemekKalori
                anayemekMalzemeler.text = it.anayemekMalzemeler
                anayemekYapilis.text = it.anayemekYapilis
            }
        })
    }
}