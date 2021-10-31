package com.hfad.fobizlcompetition.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hfad.fobizlcompetition.QuestionViewModel
import com.hfad.fobizlcompetition.R
import com.hfad.fobizlcompetition.adapter.FilterAdapter
import com.hfad.fobizlcompetition.databinding.FragmentFilterBottomSheetBinding

class FilterBottomSheetFragment : BottomSheetDialogFragment(), FilterAdapter.OnTagActionListener {
    private lateinit var binding: FragmentFilterBottomSheetBinding
    private val viewModel: QuestionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFilterBottomSheetBinding.inflate(layoutInflater, container, false)
        val filterAdapter = FilterAdapter(this)
        binding.apply {
            filterRecyclerView.apply {
                adapter = filterAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
            viewModel.tags.observe(viewLifecycleOwner) {
                filterAdapter.submitList(it.toList())
            }
        }
        binding.clearButton.setOnClickListener {
            viewModel.setFilter(null)
            dismiss()
        }
        return binding.root
    }

    override fun onTagClicked(tag: String) {
        viewModel.setFilter(tag)
        dismiss()
    }

}