package com.example.ymediaapp.presentation.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.ymediaapp.R
import com.example.ymediaapp.app.YMediaApplication
import com.example.ymediaapp.databinding.FragmentDetailBinding
import com.example.ymediaapp.presentation.model.YoutubeVideoModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
//
//    private val detailViewModel by lazy {
//        (requireActivity().application as YMediaApplication).appContainer.detailViewModelFactory.create()
//    }

    private val detailViewModel by activityViewModels<DetailViewModel> {
        (requireActivity().application as YMediaApplication).appContainer.detailViewModelFactory
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnItemIsLike.setOnClickListener {
            detailViewModel.toggleLike()
        }

        binding.btnShare.setOnClickListener {
            detailViewModel.selectedItem.value?.let { it1: YoutubeVideoModel -> shareVideo(it1) }
        }

        detailViewModel.selectedItem.observe(viewLifecycleOwner) {

            Log.d("it check", "$it")
            if (it != null) {
                with(binding) {
                    Glide.with(binding.root).load(it.thumbnail).into(ivThumbnail)
                    tvItemTitle.text = it.name
                    tvItemDescription.text = it.description
                    tvItemId.text = it.videoId
                    tvItemChannelId.text = it.channelId

                }
                updateLikeButton(it.isLike)
            }else{
                Log.d("selected Null","isNull")
            }
        }
        detailViewModel.shareItem.observe(viewLifecycleOwner) {
            if (it != null) {
                try {
                    shareVideo(it)
                } finally {
                    detailViewModel.setShareItem(null)
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

    private fun shareVideo(item: YoutubeVideoModel) {
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