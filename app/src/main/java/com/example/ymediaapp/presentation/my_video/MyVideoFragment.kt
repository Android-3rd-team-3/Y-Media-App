package com.example.ymediaapp.presentation.my_video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.ymediaapp.databinding.FragmentMyVideoBinding

class MyVideoFragment : Fragment() {

    private var _binding: FragmentMyVideoBinding? = null
    private val binding get() = _binding!!

    private val user = DummyAuth.getUser()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyVideoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}