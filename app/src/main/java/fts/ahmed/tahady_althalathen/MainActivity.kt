package fts.ahmed.tahady_althalathen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import fts.ahmed.tahady_althalathen.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        btnsClickListener()
        initLiveData()
    }

    private fun initLiveData() {
        Values.firstScore = MutableLiveData<Int>()
        Values.secondScore = MutableLiveData<Int>()
        Values.firstScore.value = 0
        Values.secondScore.value = 0
    }

    private fun btnsClickListener() {
        fun createSnackbar(view: View) {
            Snackbar.make(
                view, "Please fill the names",
                Snackbar.LENGTH_LONG
            )
                .setBackgroundTint(resources.getColor(R.color.blacky_teal))
                .setTextColor(resources.getColor(R.color.white))
                .show()
        }
        binding.btnNext.setOnClickListener {
            val firstName = binding.etFirstPlayer.text.toString()
            val secondName = binding.etSecondPlayer.text.toString()
            if (firstName.isEmpty() || secondName.isEmpty()) createSnackbar(binding.root)
            else {
                Values.firstName = firstName
                Values.secondName = secondName
                startActivity(Intent(this@MainActivity, WhatDoYouKnow_ch1::class.java))
            }
        }
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}