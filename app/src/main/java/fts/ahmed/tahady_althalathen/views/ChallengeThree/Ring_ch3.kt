package fts.ahmed.tahady_althalathen.views.ChallengeThree

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.TypedValue
import android.widget.Toast
import fts.ahmed.tahady_althalathen.databinding.ActivityRingCh3Binding
import fts.ahmed.tahady_althalathen.utils.Values
import fts.ahmed.tahady_althalathen.views.CallengeFour.Ch4_1_Add

class Ring_ch3 : AppCompatActivity() {

    private lateinit var binding: ActivityRingCh3Binding
    private  var timer: CountDownTimer?=null
    private var boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intiBinding()
        initLiveData()
        initTvs()
        onClickListeners()
    }

    override fun onStop() {
        super.onStop()
        timer?.cancel()
    }
    private fun initTvs() {
        binding.tvPlayerOne.text= Values.firstName
        binding.tvPlayerTwo.text= Values.secondName

    }

    private fun intiBinding() {
        binding = ActivityRingCh3Binding.inflate(layoutInflater)
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

    private fun onClickListeners() {

        binding.cardOne.setOnClickListener {
            boolean = true
            enableCards(false)
            startTimerOnCardOne()
        }
        binding.cardTwo.setOnClickListener {
            boolean = true
            enableCards(false)
            startTimerOnCardTwo()
        }
        binding.tvNext.setOnClickListener {
            startActivity(Intent(this@Ring_ch3,Ch4_1_Add::class.java))

        }
    }

    private fun enableCards(enable: Boolean) {
        binding.cardOne.isClickable = enable
        binding.cardTwo.isClickable = enable
    }

    private fun startTimerOnCardOne() {
        binding.press1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 100f)
        binding.press1.text = "6"
        enableCards(false)
        timer = object : CountDownTimer(6000, 1000) {
            override fun onTick(remaining: Long) {
                binding.press1.text = (remaining / 1000).toString()
            }

            override fun onFinish() {
                if (boolean) startTimerOnCardTwo()
                else afterAll()
                boolean = false
            }

        }
        (timer as CountDownTimer).start()

    }

    private fun startTimerOnCardTwo() {
        binding.press2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 100f);
        binding.press2.text = "6"
        enableCards(false)
        timer = object : CountDownTimer(6000, 1000) {
            override fun onTick(remaining: Long) {
                binding.press2.text = (remaining / 1000).toString()
            }

            override fun onFinish() {
                if (boolean) startTimerOnCardOne()
                else afterAll()
                boolean = false
            }

        }
        (timer as CountDownTimer).start()
    }

    private fun afterAll() {
        alertDialog()
        resetCards()
    }

    private fun resetCards() {
        val text = "press this card to have a chance"
        binding.press1.text = text
        binding.press1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
        binding.press2.text = text
        binding.press2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
        enableCards(true)
    }

    private fun alertDialog(): Boolean {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@Ring_ch3)
        builder.setMessage("for the Referee :\n Who is the winner ?")
        builder.setCancelable(true)
        var heIsSure = false
        builder.setPositiveButton(
            Values.firstName,
            DialogInterface.OnClickListener { dialog, id ->
                heIsSure = true
                Toast.makeText(this, "${Values.firstName} gets a point", Toast.LENGTH_SHORT).show()
                Values.increaseFirstScore()
                dialog.cancel()
            })

        builder.setNegativeButton(
            Values.secondName,
            DialogInterface.OnClickListener { dialog, id ->
                Values.increaseSecondScore()
                Toast.makeText(this, "${Values.secondName} gets a point", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            })

        val alert: AlertDialog = builder.create()
        alert.show()
        return heIsSure
    }

}
