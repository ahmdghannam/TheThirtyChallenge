package fts.ahmed.tahady_althalathen.views.CallengeFour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import fts.ahmed.tahady_althalathen.R
import fts.ahmed.tahady_althalathen.databinding.ActivityCh42TimerBinding
import fts.ahmed.tahady_althalathen.utils.Values

class Ch4_2_timer : AppCompatActivity() {
    private lateinit var binding: ActivityCh42TimerBinding
    private lateinit var timer: CountDownTimer
    private var theTimerIsStopped = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initTextViews()
        initLiveData()
        initTimer()
        initClickListiners()
    }

    private fun initTextViews() {
        binding.tvName.text="Let him guess : ${intent.getStringExtra("NAME")}"
    }

    private fun initClickListiners() {
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
    }

    private fun initBinding() {
        binding = ActivityCh42TimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initLiveData() {
        Values.firstScore.observe(this) {
            binding.tvScore.text = Values.changeTheTitleScore()
        }
        Values.secondScore.observe(this) {
            binding.tvScore.text = Values.changeTheTitleScore()
        }
    }
    private fun initTimer() {

        fun returnBack() {
            this.finish()
        }

        timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(remaining: Long) {
                binding.tvCounter.text = (remaining / 1000).toString()
            }

            override fun onFinish() {
                returnBack()
            }

        }
    }
    override fun onStop() {
        super.onStop()
        timer.cancel()
    }
}