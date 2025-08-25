package com.agungkusuma.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agungkusuma.common.R
import com.agungkusuma.core.data.remote.model.BookItem
import com.agungkusuma.core.utils.Constants
import com.agungkusuma.features.databinding.FragmentBookDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailFragment : Fragment() {

    private var _binding: FragmentBookDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val book = arguments?.getParcelable<BookItem>(Constants.KeyParam.KEY_BOOK)

        setupData(book)
    }

    private fun setupData(book: BookItem?) = with(binding) {
        book?.let {
            tvTitle.text = book.volumeInfo.title
            tvAuthor.text = book.volumeInfo.authors?.joinToString(", ") ?: "-"
            tvPublishedDate.text = book.volumeInfo.publishedDate
            tvDescription.text = book.volumeInfo.description
            tvDescription.text = if (!it.volumeInfo.description.isNullOrBlank()) {
                it.volumeInfo.description
            } else {
                getString(R.string.no_description)
            }

            Glide.with(requireContext())
                .load(book.volumeInfo.imageLinks?.thumbnail)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgThumbnail)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}