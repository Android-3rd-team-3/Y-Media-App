package com.example.ymediaapp.presentation.my_video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ymediaapp.databinding.ItemMyVideoFavoriteBinding
import com.example.ymediaapp.presentation.model.YoutubeVideoModel

class MyVideoRvAdapter(
    private val onClick: (YoutubeVideoModel) -> Unit
) : ListAdapter<YoutubeVideoModel, MyVideoRvAdapter.MyVideoViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVideoViewHolder {
        return MyVideoViewHolder(
            ItemMyVideoFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyVideoViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class MyVideoViewHolder(
        private val binding: ItemMyVideoFavoriteBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: YoutubeVideoModel) {
            binding.apply {
                tvTitle.text = item.name
                Glide.with(binding.root)
                    .load(item.thumbnail)
                    .into(ivThumbnail)
                root.setOnClickListener {
                    onClick(item)
                }
            }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<YoutubeVideoModel>() {
            override fun areItemsTheSame(
                oldItem: YoutubeVideoModel,
                newItem: YoutubeVideoModel
            ): Boolean {
                return oldItem.videoId == newItem.videoId
            }

            override fun areContentsTheSame(
                oldItem: YoutubeVideoModel,
                newItem: YoutubeVideoModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}