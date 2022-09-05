package fts.ahmed.tahady_althalathen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.TypedValue
import fts.ahmed.tahady_althalathen.databinding.ActivityRingCh3Binding

class Ring_ch3 : AppCompatActivity() {

    private lateinit var binding: ActivityRingCh3Binding
    private lateinit var timer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intiBinding()
        initLiveData()
        onClickListeners()
    }

    private fun onClickListeners() {
        fun enableCards(enable:Boolean) {
            binding.cardOne.isClickable=enable
            binding.cardOne.isClickable=enable
        }


        fun startTimerOnCardOne() {
            binding.press1.text="5"
            enableCards(false)
            binding.press1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 100f);
            timer = object : CountDownTimer(5000, 1000) {
                override fun onTick(remaining: Long) {
                    binding.press1.text = (remaining / 1000).toString()
                }

                override fun onFinish() {

                }

            }
            timer.start()

        }

        fun startTimerOnCardTwo() {
            binding.press2.text="5"
            enableCards(false)
            binding.press2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 100f);
            timer = object : CountDownTimer(5000, 1000) {
                override fun onTick(remaining: Long) {
                    binding.press2.text = (remaining / 1000).toString()
                }

                override fun onFinish() {
                    startTimerOnCardOne()
                }

            }
            timer.start()
        }

        binding.cardOne.setOnClickListener {
            startTimerOnCardOne()
            enableCards(true)
        }
        binding.cardTwo.setOnClickListener {
            startTimerOnCardTwo()

            enableCards(true)
        }
    }


    private fun initLiveData() {
        Values.firstScore.observe(this){
            binding.tvScore.text=Values.changeTheTitleScore()
        }
        Values.secondScore.observe(this){
            binding.tvScore.text=Values.changeTheTitleScore()
        }

    }

    private fun intiBinding() {
        binding= ActivityRingCh3Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}