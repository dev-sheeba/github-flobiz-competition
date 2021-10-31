package com.hfad.fobizlcompetition.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.fobizlcompetition.QuestionViewModel
import com.hfad.fobizlcompetition.R
import com.hfad.fobizlcompetition.adapter.QuestionAdapter
import com.hfad.fobizlcompetition.data.HomeItem
import com.hfad.fobizlcompetition.databinding.FragmentHomeBinding
import com.hfad.fobizlcompetition.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class HomeFragment : Fragment(), QuestionAdapter.OnLinkClickListener {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: QuestionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        val questionAdapter = QuestionAdapter(this)

        binding.apply {
            recyclerView.apply {
                adapter = questionAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
            viewModel.questions.observe(viewLifecycleOwner) { result ->
                if (result.data != null) {
                    val items = result.data.toMutableList()
                    if (items.size > 3 ) {
                        items.add(2,HomeItem.Advertisement)
                    }
                    questionAdapter.submitList(items)
                }
                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                textViewError.text = result.error?.localizedMessage
                if (result.error != null) {
                    Log.e("Test", "Testing", result.error)
                }
            }
        }
        binding.rvSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
            private var searchJob: Job? = null
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchJob?.cancel()
                searchJob = coroutineScope.launch {
                    newText?.let {
                        delay(500)
                        viewModel.searchData(it)
                    }
                }
                return false
            }

        })
        binding.rvSearchView.setOnCloseListener {
            viewModel.searchData(null)
            true
        }
        binding.icFilter.setOnClickListener {
            FilterBottomSheetFragment()
                .show(childFragmentManager, "FilterBottomSheetFragment")
        }
        return binding.root

    }

    override fun onLinkClicked(link: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }
}