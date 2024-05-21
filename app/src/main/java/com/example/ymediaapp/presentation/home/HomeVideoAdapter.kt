package com.example.ymediaapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ymediaapp.databinding.RvItemHomeBinding
import com.example.ymediaapp.domain.entity.YoutubeVideoEntity
import com.example.ymediaapp.presentation.model.YoutubeVideoModel

class HomeVideoAdapter(
    private val onClick: (YoutubeVideoModel) -> Unit
): RecyclerView.Adapter<HomeVideoAdapter.HomeVideoViewHolder>() {
    var itemList = listOf<YoutubeVideoModel>()
    class HomeVideoViewHolder(
        private val binding: RvItemHomeBinding,
        private val onClick: (YoutubeVideoModel) -> Unit
    ): RecyclerView.ViewHolder(binding.root){


        fun bind(youtubeItemModel: YoutubeVideoModel){
            with(binding){
                Glide.with(binding.root).load(youtubeItemModel.thumbnail).into(ivTitle)
                tvTitle.text = youtubeItemModel.name
                itemView.setOnClickListener {
                    onClick(youtubeItemModel)
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