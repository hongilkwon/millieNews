package com.example.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.presentation.adapter.ArticleListAdapter
import com.example.presentation.databinding.FragmentNewsListBinding
import com.example.presentation.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsListFragment : Fragment() {

    private var _binding: FragmentNewsListBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: NewsViewModel by viewModels()

    private val articleListAdapter by lazy {
        ArticleListAdapter { url ->
            findNavController().navigate(
                NewsListFragmentDirections.actionNewsListFragmentToNewsDetailWebViewFragment(
                    url
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.rvArticles.adapter = articleListAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.articlesStateFlow.collectLatest { list ->
                articleListAdapter.submitList(list)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}