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
import com.example.ymediaapp.databinding.FragmentDetailBinding
import com.example.ymediaapp.presentation.entity.YoutubeVideoEntity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.example.ymediaapp.R
//
//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// * Use the [DetailFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
//class DetailFragment : Fragment() {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_detail, container, false)
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment DetailFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            DetailFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
//}

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

        // ViewModelProvider를 통해 ViewModel 가져오기
        viewModel = ViewModelProvider(requireActivity()).get(DetailViewModel::class.java)

        val selectedItem = arguments?.getParcelable<YoutubeVideoEntity>("ARG_ITEM")

        if (selectedItem != null) {
            viewModel.setSelectedItem(selectedItem)
        } else {
            parentFragmentManager.popBackStack()
        }


//            fun bind(youtubeItemEntity: YoutubeVideoEntity){
//                with (binding){
//                    Glide.with(binding.root).load(youtubeItemEntity.thumbnail).into(ivThumbnail)
//                    tvItemTitle.text = youtubeItemEntity.name
//                    tvItemDescription.text = youtubeItemEntity.description
//                    tvItemId.text = youtubeItemEntity.videoId
//                    tvItemChannelId.text = youtubeItemEntity.channelId
//
//                    btnItemIsLike.setOnClickListener {
//                        viewModel.toggleLike(selectedItem)
//                    }
//
//                    binding.btnShare.setOnClickListener {
//                        shareVideo(selectedItem)
//                    }
//                }
//


        binding.btnItemIsLike.setOnClickListener {
            viewModel.toggleLike()
        }

//        binding.btnShare.setOnClickListener {
//            viewModel.selectedItem.value?.let { it1 -> shareVideo(it1) }
//        }

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
        viewModel.shareItem.observe(viewLifecycleOwner){
            if(it!=null){
                try {
                    shareVideo(it)
                }
                finally {
                    viewModel.setShareItem(null)
                }
            }
        }


    }


    // Like 버튼 UI 업데이트 함수
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


//    private fun showBottomSheet(selectedItem: YoutubeVideoEntity) {
//        val bottomSheetFragment = DetailFragment().apply{
//            arguments = bundleOf("ARG_ITEM" to YoutubeVideoEntity())
//        }
//        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}