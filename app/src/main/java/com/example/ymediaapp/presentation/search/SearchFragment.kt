package com.example.ymediaapp.presentation.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ymediaapp.app.AppContainer
import com.example.ymediaapp.app.SearchContainer
import com.example.ymediaapp.app.YMediaApplication
import com.example.ymediaapp.databinding.FragmentSearchBinding
import com.example.ymediaapp.domain.entity.SearchVideoEntity


interface FragmentDataListener{
    fun onDataReceived(data: SearchVideoEntity)
}

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null

    private lateinit var appContainer: AppContainer
    private lateinit var searchViewModel: SearchViewModel

    private val binding get() = _binding!!
    private val searchListAdapter by lazy {
        SearchAdapter {
            videoOnClick(it)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appContainer = (requireActivity().application as YMediaApplication).appContainer
        appContainer.searchContainer = SearchContainer(appContainer.searchRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initView()
        observeViewModel()
        setupListeners()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViewModel(){
        appContainer.searchContainer?.let {
            searchViewModel = ViewModelProvider(this, it.searchViewModelFactory)[SearchViewModel::class.java]
        }
    }

    private fun initView() {
        binding.searchRecyclerView.apply {
            adapter = searchListAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun observeViewModel() {
        searchViewModel.searchList.observe(viewLifecycleOwner) {
                searchListAdapter.itemList = it
                searchListAdapter.notifyDataSetChanged()
            }
    }

    private fun setupListeners() {
        with(binding) {
        searchButton.setOnClickListener {
            val query = searchEditText.text.toString()
            if (query.isNotEmpty()) {
                searchViewModel.getSearchList(query)
            } else {
                Toast.makeText(requireContext(), "검색어를 입력해 주세요.", Toast.LENGTH_SHORT).show()
            }
        }
            chip.setOnClickListener {
                val query = chip.text.toString()
                searchEditText.setText(query)
                searchViewModel.getSearchList(query)
            }
            chip2.setOnClickListener {
                val query = chip2.text.toString()
                searchEditText.setText(query)
                searchViewModel.getSearchList(query)
            }
            chip3.setOnClickListener {
                val query = chip3.text.toString()
                searchEditText.setText(query)
                searchViewModel.getSearchList(query)
            }
            chip4.setOnClickListener {
                val query = chip4.text.toString()
                searchEditText.setText(query)
                searchViewModel.getSearchList(query)
            }
        }
    }
    private fun videoOnClick(searchItemEntity: SearchVideoEntity) {
        (activity as? FragmentDataListener)?.onDataReceived(searchItemEntity)
    }
}
