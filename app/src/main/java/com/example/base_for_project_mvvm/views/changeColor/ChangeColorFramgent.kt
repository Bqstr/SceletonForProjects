package com.example.base_for_project_mvvm.views.changeColor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import foundation.views.BaseFragment
import foundation.views.HasScreenTitle
import foundation.views.BaseScreen
import com.example.base_for_project_mvvm.databinding.FragmentChangeColorFramgentBinding
import foundation.views.screenViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [ChangeColorFramgent.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChangeColorFramgent : BaseFragment() , HasScreenTitle {
    //to pass adition information to aoother framgents we can add it in Screen as new fields
    class Screen(
        val currentColorId: Long
    ) : BaseScreen

    lateinit var adapter: ColorAdapter
    lateinit var binding: FragmentChangeColorFramgentBinding
    override val viewModel by screenViewModel<ChangeColorViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentChangeColorFramgentBinding.inflate(layoutInflater)


        adapter = ColorAdapter(viewModel)
        setupLayoutManager(binding, adapter)

        binding.saveButton.setOnClickListener { viewModel.onSavePressed() }
        binding.cancelButton.setOnClickListener { viewModel.onCancelPressed() }

        viewModel.screenTitle.observe(viewLifecycleOwner) {
            notifyScreenUpdates()
        }
        viewModel.colorsList.observe(viewLifecycleOwner) {
            adapter.items = it

        }

        return binding.root
    }

    override fun getScreenTitle(): String? = viewModel.screenTitle.value


    private fun setupLayoutManager(
        binding: FragmentChangeColorFramgentBinding,
        adapter: ColorAdapter
    ) {
        // waiting for list width


//        binding.recyclerView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
//            override fun onGlobalLayout() {
//                binding.colorsRecyclerView.viewTreeObserver.removeOnGlobalLayoutListener(this)
//                val width = binding.colorsRecyclerView.width
//                val itemWidth = resources.getDimensionPixelSize(R.dimen.item_width)
//                val columns = width / itemWidth
//                binding.colorsRecyclerView.adapter = adapter
//                binding.colorsRecyclerView.layoutManager = GridLayoutManager(requireContext(), columns)
//            }
//        })
//    }



        val layoutManager =GridLayoutManager(context,2)


        binding.recyclerView.adapter=adapter
        binding.recyclerView.layoutManager=layoutManager

    }
}