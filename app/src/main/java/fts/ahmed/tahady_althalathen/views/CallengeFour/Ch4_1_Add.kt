package fts.ahmed.tahady_althalathen.views.CallengeFour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Sampler
import android.util.Log
import android.view.View
import android.widget.Toast
import fts.ahmed.diaryapp.PlayersAdapter
import fts.ahmed.tahady_althalathen.databinding.ActivityCh41AddBinding
import fts.ahmed.tahady_althalathen.models.Player
import fts.ahmed.tahady_althalathen.utils.Values
import fts.ahmed.tahady_althalathen.views.startEnd.FinalActivity
import kotlin.random.Random

class Ch4_1_Add : AppCompatActivity() {
    private lateinit var binding: ActivityCh41AddBinding
    private lateinit var adapter: PlayersAdapter
    private var isItFirstClick = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initAdapter()
        clickListeners()
        initLiveData()
    }

    private fun initLiveData() {
        Values.firstScore.observe(this) {
            binding.tvScore.text = Values.changeTheTitleScore()
        }
        Values.secondScore.observe(this) {
            binding.tvScore.text = Values.changeTheTitleScore()
        }
    }

    private fun initAdapter() {
        adapter = PlayersAdapter()
        binding.rvPlayers.adapter = adapter
        adapter.differ.submitList(Values.playersList.value)
    }

    private fun clickListeners() {
        binding.btnAdd.setOnClickListener {

            if (binding.etName.text.isEmpty()) Toast.makeText(
                this@Ch4_1_Add,
                "no text to add !",
                Toast.LENGTH_SHORT
            ).show()
            else {
                if (isItFirstClick) {
                    showRv()
                }
                Values.playersList.value?.add(
                    Player(
                        Values.playersID++,
                        binding.etName.text.toString()
                    )
                )
                binding.etName.setText("")
                Log.i("batata", "clickListeners: ${Values.playersList.value.toString()} ")
                adapter.notifyDataSetChanged()
            }
        }
        binding.tvStartGuess.setOnClickListener {
            if (Values.playersList.value?.size == 0) {
                Toast.makeText(this@Ch4_1_Add, "please add some names", Toast.LENGTH_SHORT).show()
            } else {
                val name = getRandomPlayer()
                val intent = Intent(this@Ch4_1_Add, Ch4_2_timer::class.java)
                intent.putExtra("NAME", name)
                startActivity(intent)
            }

        }
        binding.tvNext.setOnClickListener {
            val intent=Intent(this@Ch4_1_Add,FinalActivity::class.java)
            intent.putExtra("winner",Values.getWinner())
            intent.putExtra("result",Values.score(""))
            intent.putExtra("totalMatches",Values.totalMatches())
            startActivity(intent)
        }
    }

    private fun getRandomPlayer(): String {
        val random = Random.nextInt(Values.playersList.value!!.size)
        val player = Values.playersList.value!![random]
        Values.playersList.value!!.removeAt(random)
//        adapter.notifyItemInserted(Values.playersList.value!!.lastIndex)
        adapter.notifyDataSetChanged()
        return player.name

    }

    private fun initBinding() {
        binding = ActivityCh41AddBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun showRv() {
        binding.rvPlayers.visibility = View.VISIBLE
        binding.tvNoItems.visibility = View.INVISIBLE
    }

    override fun onResume() {
        super.onResume()
        if (Values.playersList.value?.isNotEmpty() == true) showRv()
    }
}