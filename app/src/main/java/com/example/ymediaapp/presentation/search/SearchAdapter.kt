package com.example.ymediaapp.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ymediaapp.databinding.SearchItemBinding
import com.example.ymediaapp.presentation.entity.SearchVideoEntity

class SearchAdapter(
    private val onClick: (SearchVideoEntity) -> Unit
): RecyclerView.Adapter<SearchAdapter.SearchItemViewHolder>() {
    var itemList = listOf<SearchVideoEntity>()
    class SearchItemViewHolder(
        private val binding: SearchItemBinding,
        private val onClick: (SearchVideoEntity) -> Unit
    ): RecyclerView.ViewHolder(binding.root){


        fun bind(searchItemEntity: SearchVideoEntity){
            with(binding){
                Glide.with(binding.root).load(searchItemEntity.thumbnail).into(itemImageView)
                itemTitleTextView.text = searchItemEntity.title
                itemChannelTextView.text = searchItemEntity.channel
                itemDateTimeTextView.text = searchItemEntity.dateTime
                itemView.setOnClickListener {
                    onClick(searchItemEntity)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchItemViewHolder(binding, onClick)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
}