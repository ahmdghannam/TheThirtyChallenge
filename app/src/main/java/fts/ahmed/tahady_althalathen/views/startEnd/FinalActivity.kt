package fts.ahmed.tahady_althalathen.views.startEnd

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import fts.ahmed.tahady_althalathen.R
import fts.ahmed.tahady_althalathen.databinding.ActivityFinalBinding


class FinalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFinalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        hideStatusBar()
        setupTvs()
    }

    private fun initBinding() {
        binding=ActivityFinalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupTvs() {
        val intent=intent
        val winner=intent.getStringExtra("winner")
        binding.tvCongrates.text= "congratulations ${winner} for winning"
        binding.tvResult.text="${winner} ${intent.getStringExtra("result")}"
        binding.tvTotalMatches.text="Total Matches Played ${intent.getStringExtra("totalMatches")}"

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
}