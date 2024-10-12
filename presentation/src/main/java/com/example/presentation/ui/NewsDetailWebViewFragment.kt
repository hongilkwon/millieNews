package com.example.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.example.presentation.databinding.FragmentNewsDetailWebViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailWebViewFragment : Fragment() {

    private val TAG = "NewsDetailWebViewFragment"

    private val safeArgs: NewsDetailWebViewFragmentArgs by navArgs()

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

        Log.d(TAG, "onCreateView::url::${safeArgs.url}")

        return binding.root
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