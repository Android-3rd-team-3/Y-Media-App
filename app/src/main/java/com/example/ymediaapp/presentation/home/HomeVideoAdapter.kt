package com.example.ymediaapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ymediaapp.databinding.RvItemHomeBinding
import com.example.ymediaapp.domain.entity.YoutubeVideoEntity

class HomeVideoAdapter(
    private val onClick: (YoutubeVideoEntity) -> Unit
): RecyclerView.Adapter<HomeVideoAdapter.HomeVideoViewHolder>() {
    var itemList = listOf<YoutubeVideoEntity>()
    class HomeVideoViewHolder(
        private val binding: RvItemHomeBinding,
        private val onClick: (YoutubeVideoEntity) -> Unit
    ): RecyclerView.ViewHolder(binding.root){


        fun bind(youtubeItemEntity: YoutubeVideoEntity){
            with(binding){
                Glide.with(binding.root).load(youtubeItemEntity.thumbnail).into(ivTitle)
                tvTitle.text = youtubeItemEntity.name
                itemView.setOnClickListener {
                    onClick(youtubeItemEntity)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVideoViewHolder {
        val binding = RvItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeVideoViewHolder(binding, onClick)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: HomeVideoViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
}