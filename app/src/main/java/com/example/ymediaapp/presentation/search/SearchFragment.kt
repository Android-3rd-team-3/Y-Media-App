package com.example.ymediaapp.presentation.search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ymediaapp.databinding.FragmentSearchBinding
import com.example.ymediaapp.domain.entity.SearchVideoEntity


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
                searchViewModel.getSearchList(query)
            } else {
                Toast.makeText(requireContext(), "검색어를 입력해 주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.chip.setOnClickListener {
            val query = binding.chip.text.toString()
            searchViewModel.getSearchList(query)
        }

        binding.chip2.setOnClickListener {
            val query = binding.chip2.text.toString()
            searchViewModel.getSearchList(query)
        }

        binding.chip3.setOnClickListener {
            val query = binding.chip3.text.toString()
            searchViewModel.getSearchList(query)
        }

        binding.chip4.setOnClickListener {
            val query = binding.chip4.text.toString()
            searchViewModel.getSearchList(query)
        }

        binding.btnVoice.setOnClickListener {
            startSpeechToText()
        }

    }
    private fun videoOnClick(searchItemEntity: SearchVideoEntity) {
        //Detail Fragment 여는 작업
    }

    private val speechResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val results = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (results != null && results.isNotEmpty()) {
                binding.searchEditText.setText(results[0])
            }
        } else {
            Toast.makeText(requireContext(), "인식 실패", Toast.LENGTH_SHORT).show()
        }
    }
    private fun startSpeechToText() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR") // 기본 언어를 한국어로 설정
        intent.putExtra(RecognizerIntent.EXTRA_SUPPORTED_LANGUAGES, arrayOf("en-US", "ko-KR"))

        try {
            speechResultLauncher.launch(intent)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "STT를 지원하지 않는 기기입니다.", Toast.LENGTH_SHORT).show()
        }
    }
}