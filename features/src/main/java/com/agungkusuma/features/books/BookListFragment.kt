package com.agungkusuma.features.books

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.agungkusuma.common.extensions.showSnackbar
import com.agungkusuma.common.navigation.FeaturesNavigation
import com.agungkusuma.common.state.UiState
import com.agungkusuma.core.data.mapper.toBookItem
import com.agungkusuma.core.utils.Constants
import com.agungkusuma.core.utils.network.NetworkErrorHandler
import com.agungkusuma.features.books.adapter.BookAdapter
import com.agungkusuma.features.databinding.FragmentBookListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BookListFragment : Fragment() {

    private var _binding: FragmentBookListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var featuresNavigation: FeaturesNavigation

    private val viewModel: BookListViewModel by viewModels()

    private val bookAdapter: BookAdapter by lazy {
        BookAdapter(
            onItemClick = {
                val item = it.toBookItem()
                featuresNavigation.openDetailPage(
                    bundleOf(Constants.KeyParam.KEY_BOOK to item)
                )
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeData()
        setupSearch()
        setupSwipeRefresh()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.bookState.collect { state ->
                    when (state) {
                        is UiState.Idle -> binding.swipeRefresh.isRefreshing = false
                        is UiState.Loading -> binding.swipeRefresh.isRefreshing = true
                        is UiState.Success -> {
                            bookAdapter.submitList(state.data)
                            binding.swipeRefresh.isRefreshing = false
                        }
                        is UiState.Error -> {
                            val message = NetworkErrorHandler.getErrorMessage(
                                requireContext(),
                                state.throwable
                            )
                            showSnackbar(message)
                            binding.swipeRefresh.isRefreshing = false
                        }
                    }
                }
            }
        }
    }

    private fun setupSearch() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchBooks(s?.toString() ?: "")
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener { viewModel.refresh() }
    }

    private fun setupRecyclerView() {
        with(binding.rvBooks) {
            adapter = bookAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvBooks.adapter = null
        _binding = null
    }
}
