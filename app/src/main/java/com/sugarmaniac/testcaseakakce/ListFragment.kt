package com.sugarmaniac.testcaseakakce

import HorizontalItemAdapter
import VerticalItemAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
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
        initPager()
        initObservers()
        return binding.root
    }

    private fun initPager() {

        val layoutManager = binding.verticalRv.layoutManager as GridLayoutManager
        binding.verticalRv.setOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0
                    ) {
                        sharedViewModel.loadMore()
                    }
            }
        })

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
        horizontalAdapter.registerAdapterDataObserver(indicator.adapterDataObserver)
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