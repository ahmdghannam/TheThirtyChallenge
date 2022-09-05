package fts.ahmed.tahady_althalathen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import fts.ahmed.tahady_althalathen.databinding.ActivityMazadCh25Binding

class Mazad_ch25 : AppCompatActivity() {

    private lateinit var binding: ActivityMazadCh25Binding
    private lateinit var timer: CountDownTimer
    private var targetNumber: Int = 0
    private var counted = 0
    private var theTimerIsStopped = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initTimer()
        onClickListeners()
        initTextView()
        initLiveData()
    }

    private fun initLiveData() {

        Values.firstScore.observe(this){
            binding.tvScore.text=Values.changeTheTitleScore()
        }
        Values.secondScore.observe(this){
            binding.tvScore.text=Values.changeTheTitleScore()
        }
    }

    private fun initTextView() {
        val myIntent = intent
        val playerName = playerName(myIntent)
        binding.tvPlayer.text = "the player : ${playerName}"
        targetNumber = myIntent.getStringExtra("max raise")!!.toInt()
        binding.tvTargetNumber.text = "target number : ${targetNumber}"
        binding.tvRemainingToCount.text = "remaining to count: ${targetNumber - counted}"
        binding.tvScore.text=Values.changeTheTitleScore()

    }

    private fun playerName(myIntent: Intent): String {
        val playerName =
            if (myIntent.getStringExtra("selected player") == "1") Values.firstName else Values.secondName
        return playerName!!
    }

    private fun onClickListeners() {
        binding.stopStartTimer.setOnClickListener {
            if (theTimerIsStopped) {
                timer.start()
                theTimerIsStopped = false
                binding.stopStartTimer.text = "reset"
            } else {
                timer.cancel()
                theTimerIsStopped = true
                binding.stopStartTimer.text = "start the timer"
                binding.tvCounter.text = "30"
            }

        }
        binding.ibPlus.setOnClickListener {
            if (counted != targetNumber) {
                binding.tvCounted.text = "counted : ${(++counted).toString()}"
                binding.tvRemainingToCount.text = "remaining number : ${(targetNumber - counted)}"
            }

        }
        binding.ibMinus.setOnClickListener {
            if (counted != 0) {
                binding.tvCounted.text = "counted : ${(--counted).toString()}"
                binding.tvRemainingToCount.text = "remaining number : ${(targetNumber - counted)}"
            }
        }

    }

    private fun initTimer() {
        fun checkTheWinner() {
            val selectedPlayerWon = counted == targetNumber
            val selectedPlayerName =
                if (intent.getStringExtra("selected player") == "1") Values.firstName else Values.secondName
            val notSelected =
                if (selectedPlayerName == Values.firstName) Values.secondName else Values.firstName
            val winner = if (selectedPlayerWon) selectedPlayerName else notSelected
            if (winner == Values.firstName) Values.increaseFirstScore()
            else Values.increaseSecondScore()
            Toast.makeText(this@Mazad_ch25, "$winner gets a point", Toast.LENGTH_SHORT).show()
        }

        fun returnBack() {
            this.finish()
        }
        timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(remaining: Long) {
                binding.tvCounter.text = (remaining / 1000).toString()
            }

            override fun onFinish() {
                checkTheWinner()
                returnBack()
            }

        }
    }


    private fun initBinding() {
        binding = ActivityMazadCh25Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onStop() {
        super.onStop()
        timer.cancel()
    }
}