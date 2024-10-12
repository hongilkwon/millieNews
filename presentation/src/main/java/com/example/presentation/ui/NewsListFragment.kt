package com.example.presentation.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
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

    private val viewModel: NewsViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    private val articleListAdapter by lazy {
        ArticleListAdapter { id, url ->
            if (isNetworkConnected(requireContext())) {
                findNavController().navigate(
                    NewsListFragmentDirections.actionNewsListFragmentToNewsDetailWebViewFragment(
                        id = id, url = url
                    )
                )
            } else {
                Toast.makeText(requireContext(), getString(R.string.network_connection_warning_message), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
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

    private fun initUI() {
        val gridLayoutManager = object : GridLayoutManager(context, 1) {
            override fun onLayoutChildren(
                recycler: RecyclerView.Recycler?,
                state: RecyclerView.State?
            ) {
                super.onLayoutChildren(recycler, state)
                val width = width
                spanCount = if (width > 600.dpToPx()) 3 else 1
            }
        }

        binding.rvArticles.apply {
            layoutManager = gridLayoutManager
            adapter = articleListAdapter
            itemAnimator = null
        }
    }

    private fun Int.dpToPx(): Int {
        return (this * resources.displayMetrics.density).toInt()
    }

    private fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }
}