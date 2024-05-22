package com.example.ymediaapp.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ymediaapp.app.AppContainer
import com.example.ymediaapp.app.HomeContainer
import com.example.ymediaapp.app.DetailContainer
import com.example.ymediaapp.app.MyVideoContainer
import com.example.ymediaapp.app.YMediaApplication
import com.example.ymediaapp.databinding.FragmentHomeBinding
import com.example.ymediaapp.domain.entity.YoutubeVideoEntity
import com.example.ymediaapp.presentation.detail.DetailViewModel
import com.example.ymediaapp.presentation.main.MainViewModel
import com.example.ymediaapp.presentation.model.CategoryModel
import com.example.ymediaapp.presentation.model.YoutubeVideoModel
import com.example.ymediaapp.presentation.my_video.MyVideoViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val popularListAdapter by lazy {
        HomeVideoAdapter {
            videoOnClick(it)
        }
    }
    private val categoryVideoListAdapter by lazy {
        HomeVideoAdapter {
            videoOnClick(it)
        }
    }

    private val channelListAdapter by lazy { HomeChannelAdapter() }

    private lateinit var appContainer: AppContainer
    private lateinit var homeViewModel: HomeViewModel

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContainer()
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
        initContainer()
        fetchRecyclerView()
        initView()
        initData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        appContainer.homeContainer = null
    }

    private fun initContainer() {
        appContainer = (requireActivity().application as YMediaApplication).appContainer
        appContainer.homeContainer = HomeContainer(appContainer.searchRepository)
    }

    private fun initViewModel(){
        appContainer.homeContainer?.let {
            homeViewModel =
                ViewModelProvider(this, it.homeViewModelFactory)[HomeViewModel::class.java]
        }
    }

    private fun initContainer() {
        appContainer = (requireActivity().application as YMediaApplication).appContainer
        appContainer.homeContainer = HomeContainer(appContainer.searchRepository)
        appContainer.homeContainer?.let {
            homeViewModel =
                ViewModelProvider(this, it.homeViewModelFactory)[HomeViewModel::class.java]
        }

        mainViewModel=ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }
//여기 수정하기!!!!!

    private fun initView() {
        with(binding) {
            rvPopularVideo.apply {
                adapter = popularListAdapter
                val horizontalLinearLayoutManager = LinearLayoutManager(requireActivity())
                horizontalLinearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
                layoutManager = horizontalLinearLayoutManager
            }

            rvCategory.apply {
                adapter = categoryVideoListAdapter
                val horizontalLinearLayoutManager = LinearLayoutManager(requireActivity())
                horizontalLinearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
                layoutManager = horizontalLinearLayoutManager
            }

            rvCategoryChannel.apply {
                adapter = channelListAdapter
                val horizontalLinearLayoutManager = LinearLayoutManager(requireActivity())
                horizontalLinearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
                layoutManager = horizontalLinearLayoutManager
            }

            spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val item =
                        (parent?.adapter?.getItem(position) as? CategoryModel) ?: CategoryModel(
                            "0",
                            "Category"
                        )
                    spinnerItemSelected(item)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
    }

    private fun fetchRecyclerView() {
        with(homeViewModel) {
            popularList.observe(viewLifecycleOwner) {
                popularListAdapter.itemList = it
                popularListAdapter.notifyDataSetChanged()
            }
            categoryVideoList.observe(viewLifecycleOwner) {
                getCategoryChannelList()
                categoryVideoListAdapter.itemList = it
                categoryVideoListAdapter.notifyDataSetChanged()
            }
            categoryChannelList.observe(viewLifecycleOwner) {
                channelListAdapter.itemList = it
                channelListAdapter.notifyDataSetChanged()
            }
            categoryList.observe(viewLifecycleOwner){
                binding.spinnerCategory.adapter = HomeSpinnerAdapter(requireActivity(), it)
            }
        }
    }

    private fun initData() {
        homeViewModel.apply {
            getPopularList()
            getCategoryList()
            getCategoryVideoList()
        }

    }

    private fun videoOnClick(youtubeItemModel: YoutubeVideoModel) {
        mainViewModel.setSelectedItem(youtubeItemModel)

    }

    private fun spinnerItemSelected(categoryModel: CategoryModel) {
        homeViewModel.getCategoryVideoList(categoryModel.id)
    }

}