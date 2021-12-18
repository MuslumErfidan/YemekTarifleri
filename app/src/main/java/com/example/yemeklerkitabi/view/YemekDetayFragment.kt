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
import com.example.yemeklerkitabi.viewmodel.YemekDetayViewModel
import kotlinx.android.synthetic.main.fragment_yemek_detay.*


class YemekDetayFragment : Fragment() {

    private lateinit var viewModel : YemekDetayViewModel
    private var yemekId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_yemek_detay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            yemekId = YemekDetayFragmentArgs.fromBundle(it).yemekId

        }

        viewModel = ViewModelProviders.of(this).get(YemekDetayViewModel::class.java)
        viewModel.roomVerisiniAl(yemekId)

        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.yemekLiveData.observe(viewLifecycleOwner, Observer {yemek ->
            yemek?.let {
                yemekIsim.text = it.yemekIsim
                yemekSure.text = it.yemekSure
                yemekKalori.text = it.yemekKalori
                yemekMalzemeler.text = it.yemekMalzemeler
                yemekYapilis.text = it.yemekYapilis
                context?.let {
                    yemekResim.gorselIndır(yemek.yemekGorsel, placeholderYap(it))
                }
            }
        })
    }
}