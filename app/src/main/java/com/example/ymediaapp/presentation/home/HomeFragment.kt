package com.example.ymediaapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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

    private val homeViewModel by lazy {
        (requireActivity().application as YMediaApplication)
            .appContainer
            .homeViewModelFactory
            .create()
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

            spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val item =  (parent?.adapter?.getItem(position) as? CategoryModel) ?:
                        CategoryModel(
                            "0",
                            "Category"
                        )
                        spinnerItemSelected(item)
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                }
        }
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

    private fun initData(){
        homeViewModel.apply{
            getPopularList()
            getCategoryList()
            getCategoryVideoList()
        }

    }

    private fun videoOnClick(youtubeItemModel: YoutubeVideoModel) {
        //todo Detail Fragment 여는 작업
    }

    private fun spinnerItemSelected(categoryModel: CategoryModel){
        homeViewModel.getCategoryVideoList(categoryModel.id)
    }

}