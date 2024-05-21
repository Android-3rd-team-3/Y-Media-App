package com.example.ymediaapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ymediaapp.databinding.RvItemHomeBinding
import com.example.ymediaapp.domain.entity.YoutubeChannelEntity

//채널은 클릭 이벤트 없음
class HomeChannelAdapter(
    //private val onClick: (YoutubeChannelEntity) -> Unit
): RecyclerView.Adapter<HomeChannelAdapter.HomeChannelViewHolder>() {
    var itemList = listOf<YoutubeChannelEntity>()
    class HomeChannelViewHolder(
        private val binding: RvItemHomeBinding,
        //private val onClick: (YoutubeChannelEntity) -> Unit
    ): RecyclerView.ViewHolder(binding.root){


        fun bind(youtubeChannelEntity: YoutubeChannelEntity){
            with(binding){
                Glide.with(binding.root).load(youtubeChannelEntity.thumbnail).into(ivTitle)
                tvTitle.text = youtubeChannelEntity.name
/*                itemView.setOnClickListener {
                    onClick(youtubeChannelEntity)
                }*/
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeChannelViewHolder {
        val binding = RvItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeChannelViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: HomeChannelViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
}