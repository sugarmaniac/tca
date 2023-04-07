package com.sugarmaniac.testcaseakakce

import HorizontalItemAdapter
import VerticalItemAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import com.sugarmaniac.testcaseakakce.databinding.FragmentListBinding


class ListFragment : Fragment() {

    private val sharedViewModel: ItemsViewModel by activityViewModels()
    private lateinit var binding : FragmentListBinding
    private lateinit var verticalAdapter : VerticalItemAdapter
    private lateinit var horizontalAdapter : HorizontalItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater)
        initAdapters()
        initObservers()
        return binding.root
    }

    private fun initAdapters() {
        verticalAdapter = VerticalItemAdapter(emptyList(), listener = {showItem(it)})
        horizontalAdapter = HorizontalItemAdapter(emptyList(), listener = {showItem(it)})

        binding.verticalRv.adapter = verticalAdapter
        binding.horizontalRv.adapter = horizontalAdapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.horizontalRv)

        val indicator = binding.indicator
        indicator.attachToRecyclerView(binding.horizontalRv, snapHelper)
        verticalAdapter.registerAdapterDataObserver(indicator.adapterDataObserver)

    }

    private fun initObservers(){
        sharedViewModel.currentHorizontalItems.observe(viewLifecycleOwner){
            if (it.isNotEmpty())
                horizontalAdapter.setList(it)
        }

        sharedViewModel.currentVerticalItems.observe(viewLifecycleOwner){
            if (it.isNotEmpty())
                verticalAdapter.setList(it)
        }
    }

    private fun showItem(code : String){
        sharedViewModel.fetchDevice(code)
        findNavController().navigate(R.id.action_listFragment_to_detailsFragment)
    }

}