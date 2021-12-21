package com.example.yemeklerkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.yemeklerkitabi.R
import com.example.yemeklerkitabi.viewmodel.TatliDetayViewModel
import kotlinx.android.synthetic.main.fragment_tatli_detay.*


class TatliDetayFragment : Fragment() {

    private lateinit var viewModel : TatliDetayViewModel
    private var tatliId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tatli_detay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(TatliDetayViewModel::class.java)
        viewModel.roomVerisiniAl()

        arguments?.let {
            tatliId = TatliDetayFragmentArgs.fromBundle(it).tatliId
            println(tatliId)
        }

        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.tatliLiveData.observe(viewLifecycleOwner, Observer { tatli ->
            tatli?.let {
                tatliIsim.text = it.tatliIsim
                tatliSure.text = it.tatliSure
                tatliKalori.text = it.tatliKalori
                tatliMalzemeler.text = it.tatliMalzemeler
                tatliYapilis.text = it.tatliYapilis
            }
        })
    }
}