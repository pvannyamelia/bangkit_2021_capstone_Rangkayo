package com.capstone.rangkayo

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.capstone.rangkayo.databinding.ActivityMainBinding
import com.capstone.rangkayo.ui.main.HomeViewModel
import com.capstone.rangkayo.viewModel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.elevation = 0f

        viewModel = ViewModelProvider(
            this@MainActivity,
            factory
        )[HomeViewModel::class.java]


    }
}
