package com.example.ymediaapp.presentation.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ymediaapp.databinding.FragmentSearchBinding
import com.example.ymediaapp.presentation.entity.SearchVideoEntity


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val searchListAdapter by lazy {
        SearchAdapter {
            videoOnClick(it)
        }
    }


    private val searchViewModel by viewModels<SearchViewModel> {
        SearchViewModelFactory()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        initView()
        setupListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        with(binding) {
            searchRecyclerView.apply {
                adapter = searchListAdapter
                layoutManager = GridLayoutManager(requireContext(), 2)
            }

        }
        with(searchViewModel) {
            searchList.observe(viewLifecycleOwner) {
                searchListAdapter.itemList = it
                searchListAdapter.notifyDataSetChanged()
            }
        }
    }


    private fun setupListeners() {
        binding.searchButton.setOnClickListener {
            val query = binding.searchEditText.text.toString()
            if (query.isNotEmpty()) {
                searchViewModel.getSearchList()
            } else {
                Toast.makeText(requireContext(), "검색어를 입력해 주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.chip.setOnClickListener {
            val chipKid = binding.chip.text.toString()
            searchViewModel.getSearchList()
        }

        binding.chip2.setOnClickListener {
            val chipEnglishTraining = binding.chip2.text.toString()
            searchViewModel.getSearchList()
        }

        binding.chip3.setOnClickListener {
            val chipSleepMusic = binding.chip3.text.toString()
            searchViewModel.getSearchList()
        }

        binding.chip4.setOnClickListener {
            val chipMovie = binding.chip4.text.toString()
            searchViewModel.getSearchList()
        }

    }
    private fun videoOnClick(searchItemEntity: SearchVideoEntity) {
        //Detail Fragment 여는 작업
    }
}