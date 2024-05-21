package com.example.ymediaapp.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ymediaapp.databinding.SearchItemBinding
import com.example.ymediaapp.presentation.entity.SearchVideoEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

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
                itemDateTimeTextView.text = formatDate(searchItemEntity.dateTime.toString())
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
fun formatDate(inputDateStr: String): String? {
    val inputFormat = "EEE MMM dd HH:mm:ss zzz yyyy"
    val sdf = SimpleDateFormat(inputFormat, Locale.ENGLISH)

    return try {
        val inputDate = sdf.parse(inputDateStr)
        val currentDate = Date()
        val diffInMillis = currentDate.time - inputDate.time

        val diffInYears = TimeUnit.MILLISECONDS.toDays(diffInMillis) / 365
        val diffInMonths = TimeUnit.MILLISECONDS.toDays(diffInMillis) / 30
        val diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis)
        val diffInHours = TimeUnit.MILLISECONDS.toHours(diffInMillis)
        val diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis)

        when {
            diffInYears > 0 -> "${diffInYears}년 전"
            diffInMonths > 0 -> "${diffInMonths}개월 전"
            diffInDays > 0 -> "${diffInDays}일 전"
            diffInHours > 0 -> "${diffInHours}시간 전"
            diffInMinutes > 0 -> "${diffInMinutes}분 전"
            else -> "방금 전"
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}