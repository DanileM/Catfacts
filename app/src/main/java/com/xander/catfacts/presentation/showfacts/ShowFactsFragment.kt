package com.xander.catfacts.presentation.showfacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.xander.catfacts.R
import com.xander.catfacts.data.model.CatFact
import com.xander.catfacts.databinding.FragmentShowFactsBinding
import com.xander.catfacts.util.constant.ArgumentConstants
import com.xander.catfacts.util.extension.dialogFact
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ShowFactsFragment : Fragment() {

    private lateinit var mBinding: FragmentShowFactsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_show_facts, container, false)

        val viewModel by viewModel<ShowFactsViewModel> {
            parametersOf(requireArguments().getParcelableArrayList<CatFact>(ArgumentConstants.ARRAY_FACTS_DATA))
        }

        mBinding.viewModel = viewModel

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        mBinding.viewModel?.liveDataShowDialogWithFact?.observe(viewLifecycleOwner, Observer {
            dialogFact(it)
        })
    }
}