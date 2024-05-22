package com.example.ymediaapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ymediaapp.app.AppContainer
import com.example.ymediaapp.app.HomeContainer
import com.example.ymediaapp.app.YMediaApplication
import com.example.ymediaapp.databinding.FragmentHomeBinding
import com.example.ymediaapp.presentation.model.CategoryModel
import com.example.ymediaapp.presentation.model.YoutubeVideoModel


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
    private val spinnerAdapter by lazy {
        HomeSpinnerAdapter(requireActivity(), mutableListOf(CategoryModel("0", "Category")))
    }

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
        initViewModel()
        updateRecyclerView()
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

    private fun initViewModel() {
        appContainer.homeContainer?.let {
            homeViewModel =
                ViewModelProvider(this, it.homeViewModelFactory)[HomeViewModel::class.java]
        }
    }

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

            spinnerCategory.apply {
                adapter = spinnerAdapter
                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
    }

    private fun updateRecyclerView() {
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
            categoryList.observe(viewLifecycleOwner) {
                spinnerAdapter.updateItems(it)
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
        //todo Detail Fragment 여는 작업
    }

    private fun spinnerItemSelected(categoryModel: CategoryModel) {
        homeViewModel.getCategoryVideoList(categoryModel.id)
    }

}