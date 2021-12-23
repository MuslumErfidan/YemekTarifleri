package com.example.yemeklerkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.yemeklerkitabi.R
import kotlinx.android.synthetic.main.fragment_yemek_turu.*


class YemekTuruFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_yemek_turu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        corba_liste_button.setOnClickListener {
            val action = YemekTuruFragmentDirections.actionYemekTuruFragmentToYemekSecimFragment()
            Navigation.findNavController(it).navigate(action)
        }

        tatli_liste_button.setOnClickListener {
            val action = YemekTuruFragmentDirections.actionYemekTuruFragmentToTatliListeFragment()
            Navigation.findNavController(it).navigate(action)
        }

        salata_liste_button.setOnClickListener {
            val action = YemekTuruFragmentDirections.actionYemekTuruFragmentToSalataListeFragment()
            Navigation.findNavController(it).navigate(action)
        }

        anayemek_liste_button.setOnClickListener {
            val action = YemekTuruFragmentDirections.actionYemekTuruFragmentToAnayemekListeFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}