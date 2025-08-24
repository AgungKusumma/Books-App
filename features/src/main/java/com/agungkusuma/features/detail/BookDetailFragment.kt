package com.agungkusuma.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agungkusuma.core.domain.model.Book
import com.agungkusuma.core.utils.Constants
import com.agungkusuma.features.databinding.FragmentBookDetailBinding
import com.bumptech.glide.Glide
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

        val book = arguments?.getParcelable<Book>(Constants.KeyParam.KEY_BOOK)

        book?.let {
            binding.tvTitle.text = book.title
            binding.tvAuthor.text = book.authors
            binding.tvPublishedDate.text = book.publishedDate

            Glide.with(this)
                .load(book.thumbnail)
                .into(binding.imgThumbnail)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}