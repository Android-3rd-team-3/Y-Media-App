package com.example.ymediaapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ymediaapp.databinding.FragmentHomeBinding
import com.example.ymediaapp.presentation.entity.YoutubeVideoEntity


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val popularListAdapter by lazy {
        HomeVideoAdapter {
            videoOnClick(it)
        }
    }

    private val categoryListAdapter by lazy {
        HomeVideoAdapter {
            videoOnClick(it)
        }
    }

    private val channelListAdapter by lazy { HomeChannelAdapter() }

    private val homeViewModel by viewModels<HomeViewModel> {
        HomeViewModelFactory()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        with(binding) {
            rvPopularVideo.apply {
                adapter = popularListAdapter
                layoutManager = LinearLayoutManager(requireActivity()).apply { orientation =  LinearLayoutManager.HORIZONTAL }
            }

            rvCategory.apply {
                adapter = categoryListAdapter
                layoutManager = LinearLayoutManager(requireActivity()).apply { orientation =  LinearLayoutManager.HORIZONTAL }
            }

            rvCategoryChannel.apply {
                adapter = channelListAdapter
                layoutManager = LinearLayoutManager(requireActivity()).apply { orientation =  LinearLayoutManager.HORIZONTAL }
            }

            ivPopUp.setOnClickListener {

            }
        }
        with(homeViewModel) {
            popularList.observe(viewLifecycleOwner) {
                popularListAdapter.itemList = it
                popularListAdapter.notifyDataSetChanged()
            }
            categoryVideoList.observe(viewLifecycleOwner) {
                categoryListAdapter.itemList = it
                categoryListAdapter.notifyDataSetChanged()
            }
            categoryChannelList.observe(viewLifecycleOwner) {
                channelListAdapter.itemList = it
                channelListAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun initData(){
        homeViewModel.getLists()
    }

    private fun videoOnClick(youtubeItemEntity: YoutubeVideoEntity) {
        //Detail Fragment 여는 작업
    }
}