package com.capstone.rangkayo.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.rangkayo.databinding.FragmentHomeBinding
import com.capstone.rangkayo.viewModel.ViewModelFactory
import com.capstone.rangkayo.vo.Status
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFragment : DaggerFragment() {
    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var binding: FragmentHomeBinding

    private lateinit var mAdapter: HomeAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            mAdapter = HomeAdapter()

            prepareRecycler()
            activity?.let { setUpViewModel(it) }

            viewModel.getMovies().observe(viewLifecycleOwner, {
                when (it.status) {
                    Status.SUCCESS -> {
                        mAdapter.submitList(it.data)
                        showLoading(false)
                    }
                    Status.LOADING -> {
                        showLoading(true)
                    }
                    Status.ERROR -> {
                        showErrorMessage(it.message as String)
                        showLoading(false)
                    }
                }
            })


        }
    }

    private fun setUpViewModel(fragmentActivity: FragmentActivity) {
        viewModel = ViewModelProvider(fragmentActivity, factory)[HomeViewModel::class.java]
    }

    private fun prepareRecycler() {
        with(binding.rvNews) {
            layoutManager =
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    GridLayoutManager(activity, 2)
                } else {
                    GridLayoutManager(activity, 4)
                }
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }


    private fun showLoading(state: Boolean) {
        binding.apply {
            if (state) {
                shimmerViewContainer.visibility = View.VISIBLE
                shimmerViewContainer.startShimmer()
            } else {
                shimmerViewContainer.stopShimmer()
                shimmerViewContainer.visibility = View.GONE
            }
        }
    }

    private fun showErrorMessage(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_LONG)
            .show()
    }
}