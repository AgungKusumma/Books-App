package com.agungkusuma.features.books.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agungkusuma.core.data.remote.model.BookItem
import com.agungkusuma.features.databinding.ItemBookBinding
import com.bumptech.glide.Glide

class BookAdapter(
    private val onItemClick: (BookItem) -> Unit
) : ListAdapter<BookItem, BookAdapter.BookViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return BookViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BookViewHolder(
        private val binding: ItemBookBinding, private val onItemClick: (BookItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: BookItem) = with(binding) {
            tvTitle.text = book.volumeInfo.title
            tvAuthor.text = book.volumeInfo.authors?.joinToString(", ") ?: "-"
            tvPublishedDate.text = book.volumeInfo.publishedDate
            Glide.with(imgThumbnail).load(book.volumeInfo.imageLinks?.thumbnail).into(binding.imgThumbnail)

            root.setOnClickListener { onItemClick(book) }
        }
    }

    class BookDiffCallback : DiffUtil.ItemCallback<BookItem>() {
        override fun areItemsTheSame(oldItem: BookItem, newItem: BookItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: BookItem, newItem: BookItem): Boolean = oldItem == newItem
    }
}