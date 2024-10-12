package com.example.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.navArgs
import com.example.presentation.R
import com.example.presentation.databinding.FragmentNewsDetailWebViewBinding
import com.example.presentation.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailWebViewFragment : Fragment() {

    private val TAG = "NewsDetailWebViewFragment"

    private val safeArgs: NewsDetailWebViewFragmentArgs by navArgs()

    private val viewModel: NewsViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    private var _binding: FragmentNewsDetailWebViewBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentNewsDetailWebViewBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.readArticle(safeArgs.id)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.webView.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(safeArgs.url)
        }
    }

    override fun onDestroyView() {
        (activity as AppCompatActivity).supportActionBar?.show()
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}