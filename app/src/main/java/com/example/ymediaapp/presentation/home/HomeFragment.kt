package com.example.ymediaapp.presentation.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.ymediaapp.app.YMediaApplication
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ymediaapp.databinding.FragmentHomeBinding
import com.example.ymediaapp.domain.entity.CategoryEntity
import com.example.ymediaapp.domain.entity.YoutubeVideoEntity

//
interface FragmentDataListener{
    fun onDataReceived(data: YoutubeVideoEntity)
}
//

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val popularListAdapter by lazy {
        HomeVideoAdapter {
            videoOnClick(it)
        }
    }
    //
    private var listener: FragmentDataListener? = null
    //

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

    override fun onAttach(context: Context){
        super.onAttach(context)

        if(context is FragmentDataListener){
            listener = context
        } else{
            throw RuntimeException("$context must implement FragmentDataListener")
        }
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
                        val item =  (parent?.adapter?.getItem(position) as? CategoryEntity) ?:
                        CategoryEntity(
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
                Log.d("bottomSheet","$it")
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

    private fun videoOnClick(youtubeItemEntity: YoutubeVideoEntity) {
        (activity as? FragmentDataListener)?.onDataReceived(youtubeItemEntity)
    }

    private fun spinnerItemSelected(categoryEntity: CategoryEntity){
        homeViewModel.getCategoryVideoList(categoryEntity.id)
    }

}