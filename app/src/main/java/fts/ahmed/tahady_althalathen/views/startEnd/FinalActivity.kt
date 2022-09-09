package fts.ahmed.tahady_althalathen.views.startEnd

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import fts.ahmed.tahady_althalathen.databinding.ActivityFinalBinding


class FinalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFinalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        hideStatusBar()
        setupTvs()
        clickListeners()
    }

    private fun clickListeners() {
        binding.tvRestartGame.setOnClickListener {
            restartAlertDialog()
        }
    }

    private fun initBinding() {
        binding=ActivityFinalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupTvs() {
        val intent=intent
        val winner=intent.getStringExtra("winner")
        if (winner=="Draw")
            binding.tvCongrates.text= "This was an Epic game it is a draw "
        else
        binding.tvCongrates.text= "congratulations ${winner} for winning"
        binding.tvResult.text="${winner} ${intent.getStringExtra("result")}"

    }

    private fun hideStatusBar() {
        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        } else {
            val decorView: View = window.decorView
            // Hide the status bar.
            val uiOptions: Int = View.SYSTEM_UI_FLAG_FULLSCREEN
            decorView.setSystemUiVisibility(uiOptions)
        }
    }
    private fun restartAlertDialog(): Boolean {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@FinalActivity)
        builder.setMessage("Do you want to restart the game ?")
        builder.setCancelable(true)

        var heIsSure = false

        builder.setPositiveButton(
            "Yes",
            DialogInterface.OnClickListener{ dialog, id->
                heIsSure = true
                startActivity(Intent(this@FinalActivity,MainActivity::class.java))
                finish()
                dialog.cancel()
            })

        builder.setNegativeButton(
            "No",
            DialogInterface.OnClickListener{ dialog, id->dialog.cancel()})

        val alert: AlertDialog = builder.create()
        alert.show()
        return heIsSure
    }
}