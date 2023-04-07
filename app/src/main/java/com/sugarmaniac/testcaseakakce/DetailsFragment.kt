package com.sugarmaniac.testcaseakakce

import CapasityOptionAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.sugarmaniac.testcaseakakce.databinding.FragmentDetailsBinding
import com.sugarmaniac.testcaseakakce.databinding.FragmentListBinding


class DetailsFragment : Fragment() {

    private val sharedViewModel: ItemsViewModel by activityViewModels()
    private lateinit var binding : FragmentDetailsBinding
    private lateinit var adapter: CapasityOptionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        initAdapter()
        initObservers()
        return binding.root
    }

    private fun initAdapter() {
        adapter = CapasityOptionAdapter(emptyList(), {})
        binding.optionsRv.adapter = adapter
    }

    private fun initObservers() {
        sharedViewModel.currentItem.observe(viewLifecycleOwner){
            binding.name.text = it.mkName
            binding.lastUpdate.text = getString(R.string.last_update, it.lastUpdate)
            binding.price.text = getString(R.string.pricing, it.price)
            Glide.with(requireContext())
                .load(it.imageUrl) to binding.image
            binding.mostPopular.text = it.badge
            if (it.badge.isNotBlank()) binding.mostPopular.isVisible = true
            binding.sellerNumber.text = getString(R.string.satici_icinde_kargo_dahil_en_ucuz_fiyat_secenegi, it.countOfPrices)
            binding.shipping.isVisible = it.freeShipping
            adapter.setList(it.storageOptions)
        }
    }

}