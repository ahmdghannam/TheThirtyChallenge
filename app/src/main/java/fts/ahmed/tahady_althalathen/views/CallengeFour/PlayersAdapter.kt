package fts.ahmed.diaryapp;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import fts.ahmed.tahady_althalathen.databinding.RvItemBinding
import fts.ahmed.tahady_althalathen.models.Player

class PlayersAdapter : RecyclerView.Adapter<PlayersAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: RvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Player) {
            binding.tvId.text=(item.id + 1).toString()
            binding.tvName.text=item.name
        }
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<Player>() {
        override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        // create this method which customize the view of single item
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}