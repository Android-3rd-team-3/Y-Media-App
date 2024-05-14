package com.example.ymediaapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ymediaapp.databinding.RvItemHomeBinding
import com.example.ymediaapp.presentation.entity.YoutubeVideoEntity

class HomeAdapter(
    private val onClick: (YoutubeVideoEntity) -> Unit
): RecyclerView.Adapter<HomeAdapter.HomeItemViewHolder>() {
    var itemList = listOf<YoutubeVideoEntity>()
    class HomeItemViewHolder(
        private val binding: RvItemHomeBinding,
        private val onClick: (YoutubeVideoEntity) -> Unit
    ): RecyclerView.ViewHolder(binding.root){
        private var currentItem: YoutubeVideoEntity? = null

        init {
            itemView.setOnClickListener {
                currentItem?.let {
                    onClick(it)
                }
            }
        }

        fun bind(youtubeItemEntity: YoutubeVideoEntity){
            with(binding){
                currentItem = youtubeItemEntity
                Glide.with(binding.root).load(youtubeItemEntity.thumbnail).into(ivTitle)
                tvTitle.text = youtubeItemEntity.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder {
        val binding = RvItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeItemViewHolder(binding, onClick)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
}