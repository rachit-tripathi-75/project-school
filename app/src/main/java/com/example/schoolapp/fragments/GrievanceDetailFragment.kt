package com.example.schoolapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.schoolapp.R
import com.example.schoolapp.databinding.FragmentGrievanceDetailBinding


class GrievanceDetailFragment : Fragment() {

    private lateinit var binding: FragmentGrievanceDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentGrievanceDetailBinding.inflate(inflater, container, false)



        return binding.root
    }


}