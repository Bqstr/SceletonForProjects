package com.example.base_for_project_mvvm.views.currentColor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import foundation.views.BaseFragment
import foundation.views.BaseScreen
import com.example.base_for_project_mvvm.databinding.FragmentCurrentColorBinding
import foundation.views.screenViewModel


class CurrentColorFragment : BaseFragment() {

    override val viewModel by screenViewModel<CurrentColorViewModel>()
    lateinit var binding:FragmentCurrentColorBinding

    class Screen(): BaseScreen {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding =FragmentCurrentColorBinding.inflate(layoutInflater)
        viewModel.currentColor.observe(viewLifecycleOwner){
            binding.squareView.setBackgroundColor(it.value)
        }
        binding.goToChange.setOnClickListener{
            viewModel.goToChange()
        }
        // Inflate the layout for this fragment
        return binding.root
    }
}