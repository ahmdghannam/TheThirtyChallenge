package fts.ahmed.tahady_althalathen.views.startEnd

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import fts.ahmed.tahady_althalathen.R
import fts.ahmed.tahady_althalathen.views.ChallengeOne.WhatDoYouKnow_ch1
import fts.ahmed.tahady_althalathen.databinding.ActivityMainBinding
import fts.ahmed.tahady_althalathen.models.Player
import fts.ahmed.tahady_althalathen.utils.Values


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
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
                Values.playersList =MutableLiveData<MutableList<Player>>()
                Values.playersList.value= mutableListOf()
                startActivity(Intent(this@MainActivity, WhatDoYouKnow_ch1::class.java))
                finish()
            }

        }
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        alertDialog()
    }
    private fun alertDialog(): Boolean {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        builder.setMessage("Do you want to end the game ?")
        builder.setCancelable(true)

        var heIsSure = false

        builder.setPositiveButton(
            "Yes",
            DialogInterface.OnClickListener{ dialog, id->
                heIsSure = true
                super.onBackPressed()
                dialog.cancel()
            })

        builder.setNegativeButton(
            "No",
            DialogInterface.OnClickListener{dialog, id->dialog.cancel()})

        val alert: AlertDialog = builder.create()
        alert.show()
        return heIsSure
    }

}