package com.xander.catfacts.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.xander.catfacts.R
import com.xander.catfacts.constant.ArgumentConstants
import com.xander.catfacts.databinding.FragmentGetFactsBinding
import com.xander.catfacts.ui.viewmodel.GetFactsViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class GetFactsFragment : Fragment() {

    private lateinit var mBinding: FragmentGetFactsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_facts, container, false)

        val viewModel by viewModel<GetFactsViewModel>()
        mBinding.viewModel = viewModel

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.viewModel?.liveDataShowFacts?.observe(viewLifecycleOwner, Observer {
            val bundle = bundleOf(ArgumentConstants.ARRAY_FACTS_DATA to it)
            view.findNavController().navigate(R.id.showFactsFragment, bundle)
        })
    }
}