package com.agungkusuma.features.books

import android.os.Bundle
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
import com.agungkusuma.common.navigation.FeaturesNavigation
import com.agungkusuma.core.utils.Constants
import com.agungkusuma.features.books.adapter.BookAdapter
import com.agungkusuma.features.databinding.FragmentBookListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
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
                Timber.e("Book clicked: $it")
                featuresNavigation.openDetailPage(
                    bundleOf(Constants.KeyParam.KEY_BOOK to it)
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
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.bookState.collect { books ->
                    bookAdapter.submitList(books)
                }
            }
        }
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