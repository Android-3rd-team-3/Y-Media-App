package com.example.ymediaapp.presentation.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.ymediaapp.R
import com.example.ymediaapp.app.AppContainer
import com.example.ymediaapp.app.DetailContainer
import com.example.ymediaapp.app.YMediaApplication
import com.example.ymediaapp.databinding.FragmentDetailBinding
import com.example.ymediaapp.presentation.model.YoutubeVideoModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailFragment : BottomSheetDialogFragment() {

    private lateinit var appContainer: AppContainer
    private var _binding: FragmentDetailBinding? = null
    private lateinit var detailViewModel: DetailViewModel
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContainer()
    }

    private fun initContainer() {
        appContainer = (requireActivity().application as YMediaApplication).appContainer
        appContainer.detailContainer = DetailContainer(appContainer.videoRepository)
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


        initViewModel()

        arguments?.let {
            val item = it.getParcelable("clickItem", YoutubeVideoModel::class.java)
            Log.d("detail it", "$it")
            if (item != null) {
                detailViewModel.setSelectedItem(item)
            }
        }

        binding.btnItemIsLike.setOnClickListener {
            detailViewModel.toggleLike()
        }

        binding.btnShare.setOnClickListener {
            //detailViewModel.selectedItem.value?.let { it1: YoutubeVideoModel -> shareVideo(it1) }
            detailViewModel.shareItem()
        }

        detailViewModel.selectedItem.observe(viewLifecycleOwner) {

            if (it != null) {
                with(binding) {
                    Glide.with(binding.root).load(it.thumbnail).into(ivThumbnail)
                    tvItemTitle.text = it.name
                    tvItemDescription.text = it.description
                }
                updateLikeButton(it.isLike)
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

    private fun initViewModel() {
        appContainer.detailContainer?.let {
            detailViewModel = ViewModelProvider(
                requireActivity(),
                it.detailViewModelFactory
            )[DetailViewModel::class.java]
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

    override fun onDestroy() {
        super.onDestroy()
        appContainer.detailContainer = null
    }

}
