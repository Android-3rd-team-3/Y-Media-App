package com.example.ymediaapp.presentation.search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ymediaapp.app.AppContainer
import com.example.ymediaapp.app.SearchContainer
import com.example.ymediaapp.app.YMediaApplication
import com.example.ymediaapp.databinding.FragmentSearchBinding
import com.example.ymediaapp.presentation.main.MainViewModel
import com.example.ymediaapp.presentation.model.SearchVideoModel
import com.example.ymediaapp.presentation.model.YoutubeVideoModel


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null

    private lateinit var appContainer: AppContainer


    private val availableLanguages = arrayOf("English", "한국어")
    private val languageCodes = arrayOf("en-US", "ko-KR")
    private var selectedLanguageCode = "ko-KR"

    private val binding get() = _binding!!


    private val searchListAdapter by lazy {
        SearchAdapter {
            videoOnClick(it)        }
    }

    private lateinit var searchViewModel: SearchViewModel

    //
    private lateinit var mainViewModel: MainViewModel
    //

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

        //
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        //
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        appContainer.searchContainer = null
    }

    private fun initViewModel(){
        appContainer.searchContainer?.let {
            searchViewModel = ViewModelProvider(this, it.searchViewModelFactory)[SearchViewModel::class.java]
        }
        mainViewModel=ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
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
                clearChipSelection()
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

            binding.btnVoice.setOnClickListener {
                showSelectionDialog()
            }
        }

    }

    private fun clearChipSelection() {
            binding.chipGroup.clearCheck()
    }

    private fun showSelectionDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("검색할 언어를 선택해 주세요.")
            .setItems(availableLanguages) { dialog, which ->
                selectedLanguageCode = languageCodes[which]
                startSpeechToText()
            }
            .show()
    }

    private fun startSpeechToText() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, selectedLanguageCode)

        try {
            speechResultLauncher.launch(intent)
        } catch (e: Exception) {
            Toast.makeText(
                requireContext(),
                "STT를 지원하지 않는 기기입니다.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    private val speechResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
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


    private fun videoOnClick(searchItemModel: SearchVideoModel) {
//        mainViewModel.setSelecteSearchdItem(searchItemModel)
    }

//    private fun videoOnClick(youtubeItemModel: YoutubeVideoModel) {
//        mainViewModel.setSelectedItem(youtubeItemModel)
//
//    }
}

