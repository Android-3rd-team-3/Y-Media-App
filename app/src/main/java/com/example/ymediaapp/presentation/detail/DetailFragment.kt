package com.example.ymediaapp.presentation.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.ymediaapp.R
import com.example.ymediaapp.data.repository.VideoRepositoryImpl
import com.example.ymediaapp.databinding.FragmentDetailBinding
import com.example.ymediaapp.presentation.entity.YoutubeVideoEntity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailFragment : BottomSheetDialogFragment() {

    private lateinit var viewModel: DetailViewModel
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(DetailViewModel::class.java)


        binding.btnItemIsLike.setOnClickListener {
            viewModel.toggleLike()
        }

        binding.btnShare.setOnClickListener {
            viewModel.selectedItem.value?.let { it1 -> shareVideo(it1) }
        }
        //djfij

        viewModel.selectedItem.observe(viewLifecycleOwner) {
            if (it != null) {
                with(binding) {
                    Glide.with(binding.root).load(it.thumbnail).into(ivThumbnail)
                    tvItemTitle.text = it.name
                    tvItemDescription.text = it.description
                    tvItemId.text = it.videoId
                    tvItemChannelId.text = it.channelId

                }
                updateLikeButton(it.isLike)
            }
        }
        viewModel.shareItem.observe(viewLifecycleOwner) {
            if (it != null) {
                try {
                    shareVideo(it)
                } finally {
                    viewModel.setShareItem(null)
                }
            }
        }


    }

    private fun updateLikeButton(isLiked: Boolean) {
        if (isLiked) {
            binding.btnItemIsLike.text = "Dislike"
            binding.btnItemIsLike.setBackgroundResource(R.drawable.bg_btn_dislike)
        } else {
            binding.btnItemIsLike.text = "Like"
            binding.btnItemIsLike.setBackgroundResource(R.drawable.bg_btn_like)
        }
    }

    private fun shareVideo(item: YoutubeVideoEntity) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Check out this video: ${item.name}")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share video via"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}