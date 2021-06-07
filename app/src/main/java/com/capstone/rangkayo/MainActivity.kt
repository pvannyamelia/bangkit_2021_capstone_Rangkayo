package com.capstone.rangkayo

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.capstone.rangkayo.chat.ChatActivity
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
        supportActionBar?.title = ""

        viewModel = ViewModelProvider(
            this@MainActivity,
            factory
        )[HomeViewModel::class.java]


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when (item.itemId) {
            R.id.chat -> startActivity(Intent(this, ChatActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}
