package com.example.ymediaapp.presentation.my_video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.ymediaapp.app.AppContainer
import com.example.ymediaapp.app.MyVideoContainer
import com.example.ymediaapp.app.User
import com.example.ymediaapp.app.YMediaApplication
import com.example.ymediaapp.databinding.FragmentMyVideoBinding
import com.example.ymediaapp.presentation.main.MainViewModel

class MyVideoFragment : Fragment() {

    private var _binding: FragmentMyVideoBinding? = null
    private val binding get() = _binding!!

    private lateinit var appContainer: AppContainer
    private lateinit var myVideoViewModel: MyVideoViewModel
    private lateinit var user: User

    private val mainViewModel: MainViewModel by activityViewModels()

    private val rvAdapter by lazy {
        MyVideoRvAdapter {
            mainViewModel.setSelectedItem(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContainer = (requireActivity().application as YMediaApplication).appContainer
        appContainer.myVideoContainer = MyVideoContainer(appContainer.videoRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyVideoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appContainer.myVideoContainer?.let {
            myVideoViewModel = ViewModelProvider(this, it.myVideoViewModelFactory)[MyVideoViewModel::class.java]
            user = it.user
        }

        binding.apply {
            Glide.with(this@MyVideoFragment)
                .load(user.profileImage)
                .circleCrop()
                .into(ivProfile)
            Glide.with(this@MyVideoFragment)
                .load(user.backgroundImage)
                .into(ivBackground)

            tvChannelName.text = user.channelName
            tvChannelDescription.text = user.channelDescription
            rvFavoriteVideos.adapter = rvAdapter
        }

        with(myVideoViewModel) {
            favoriteList.observe(viewLifecycleOwner) {
                rvAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        appContainer.myVideoContainer = null
        super.onDestroy()
    }
}