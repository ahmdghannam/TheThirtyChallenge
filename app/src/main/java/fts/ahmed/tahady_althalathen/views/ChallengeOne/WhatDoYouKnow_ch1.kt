package fts.ahmed.tahady_althalathen.views.ChallengeOne

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import fts.ahmed.tahady_althalathen.views.ChallengeTwo.Mazad_ch2
import fts.ahmed.tahady_althalathen.databinding.ActivityWhatDoYouKnowCh1Binding
import fts.ahmed.tahady_althalathen.utils.Values

class WhatDoYouKnow_ch1 : AppCompatActivity() {
    private lateinit var binding: ActivityWhatDoYouKnowCh1Binding
    private var missP1 = 0
    private var missP2 = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        tvsInit()
        ClickListeners()
    }

    private fun tvsInit() {


        binding.tvPlayerOne.text = Values.firstName
        binding.tvPlayerTwo.text = Values.secondName

        Values.firstScore.observe(this){
            binding.tvScore.text= Values.changeTheTitleScore()
        }
        Values.secondScore.observe(this){
            binding.tvScore.text= Values.changeTheTitleScore()
        }


    }


    private fun ClickListeners() {

        fun showReset() {
            binding.tvReset.text = "Reset "

//            binding.tvReset.setCompoundDrawablesWithIntrinsicBounds(
//               0 ,0, R.drawable.ic_reset, 0
//            )

        }

        fun disableEnableCards(boolean: Boolean) {
            binding.cardOne.isClickable = boolean
            binding.cardTwo.isClickable = boolean
        }

        binding.cardOne.setOnClickListener {
            binding.press1.visibility = View.INVISIBLE
            missP1++
            if (missP1 == 1) binding.ivr1.visibility = View.VISIBLE
            if (missP1 == 2) binding.ivr2.visibility = View.VISIBLE
            if (missP1 == 3) {
                binding.ivr3.visibility = View.VISIBLE
                Values.increaseSecondScore()
                disableEnableCards(false)
                showReset()

            }

        }
        binding.cardTwo.setOnClickListener {
            binding.press2.visibility = View.INVISIBLE
            missP2++
            if (missP2 == 1) binding.iv1.visibility = View.VISIBLE
            if (missP2 == 2) binding.iv2.visibility = View.VISIBLE
            if (missP2 == 3) {
                binding.iv3.visibility = View.VISIBLE
                Values.increaseFirstScore()
                disableEnableCards(false)
                showReset()

            }
        }
        binding.tvReset.setOnClickListener {
            if (binding.tvReset.text.toString() == "Reset ") {
                missP1 = 0;missP2 = 0
                binding.press1.visibility = View.VISIBLE
                binding.press2.visibility = View.VISIBLE
                binding.ivr1.visibility = View.INVISIBLE
                binding.ivr2.visibility = View.INVISIBLE
                binding.ivr3.visibility = View.INVISIBLE
                binding.iv1.visibility = View.INVISIBLE
                binding.iv2.visibility = View.INVISIBLE
                binding.iv3.visibility = View.INVISIBLE
                disableEnableCards(true)
                binding.tvReset.text = "CH1: What Do You Know ?"
//                binding.tvReset.setCompoundDrawablesWithIntrinsicBounds(
//                    0 ,0, 0, 0
//                )

            }
        }
        binding.tvNext.setOnClickListener {
            startActivity(Intent(this@WhatDoYouKnow_ch1, Mazad_ch2::class.java))
        }

    }

    private fun initBinding() {
        binding = ActivityWhatDoYouKnowCh1Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}