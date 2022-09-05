package fts.ahmed.tahady_althalathen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import com.google.android.material.snackbar.Snackbar
import fts.ahmed.tahady_althalathen.databinding.ActivityMazadCh2Binding

class Mazad_ch2 : AppCompatActivity() {
    private lateinit var binding: ActivityMazadCh2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initSeekBar()
        previousNextButtons()
        clickListeners()
        setupTvs()
    }

    private fun setupTvs() {
        binding.rbFirstPlayer.text=Values.firstName
        binding.rbSecondPlayer.text=Values.secondName

        Values.firstScore.observe(this){
            binding.tvScore.text=Values.changeTheTitleScore()
        }
        Values.secondScore.observe(this){
            binding.tvScore.text=Values.changeTheTitleScore()
        }

    }

    private fun clickListeners() {
        fun createSnackBar(view: View) {
            Snackbar.make(
                view,
                "Please choose The player",
                Snackbar.LENGTH_LONG)
                .setBackgroundTint(resources.getColor(R.color.blacky_teal))
                .setTextColor(resources.getColor(R.color.white))
                .show()
        }
        fun startTimer() {
            if (binding.radioGroup.checkedRadioButtonId == -1) createSnackBar(binding.root)
            else {
                val intent=Intent(this@Mazad_ch2,Mazad_ch25::class.java)
                val chosenPlayer=if (binding.radioGroup.checkedRadioButtonId==R.id.rb_first_player) 1 else 2
                intent.putExtra("selected player",chosenPlayer.toString())
                intent.putExtra("max raise",binding.seekBar.progress.toString())
                startActivity(intent)
            }
        }
        binding.tvNext.setOnClickListener {
            startActivity(Intent(this@Mazad_ch2,Ring_ch3::class.java))
        }
        binding.ivTimer.setOnClickListener {
            startTimer()
        }
        binding.tvStartTimer.setOnClickListener {
            startTimer()
        }
    }



    private fun previousNextButtons() {
        binding.ivBefore.setOnClickListener {
            binding.seekBar.progress--
        }
        binding.ivNext.setOnClickListener {
            binding.seekBar.progress++
        }
    }

    private fun initSeekBar() {
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar, p1: Int, p2: Boolean) {
                binding.tvSeekbarProgress.text = p0.progress.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })
    }

    private fun initBinding() {
        binding = ActivityMazadCh2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}