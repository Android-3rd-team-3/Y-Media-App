package com.example.ymediaapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ymediaapp.databinding.RvItemHomeBinding
import com.example.ymediaapp.presentation.model.YoutubeChannelModel

//채널은 클릭 이벤트 없음
class HomeChannelAdapter(
): RecyclerView.Adapter<HomeChannelAdapter.HomeChannelViewHolder>() {
    var itemList = listOf(
        YoutubeChannelModel(
            "",
            "",
            "",
            ""
        )
    )
    class HomeChannelViewHolder(
        private val binding: RvItemHomeBinding,
    ): RecyclerView.ViewHolder(binding.root){


        fun bind(youtubeChannelModel: YoutubeChannelModel){
            with(binding){
                Glide.with(binding.root).load(youtubeChannelModel.thumbnail).into(ivTitle)
                tvTitle.text = youtubeChannelModel.name
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