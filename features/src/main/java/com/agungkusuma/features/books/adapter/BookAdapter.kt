package com.agungkusuma.features.books.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agungkusuma.common.R
import com.agungkusuma.core.data.local.BookEntity
import com.agungkusuma.features.databinding.ItemBookBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class BookAdapter(
    private val onItemClick: (BookEntity) -> Unit
) : ListAdapter<BookEntity, BookAdapter.BookViewHolder>(BookDiffCallback()) {

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
        private val binding: ItemBookBinding, private val onItemClick: (BookEntity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: BookEntity) = with(binding) {
            tvTitle.text = book.title
            tvAuthor.text = book.authors
            tvPublishedDate.text = book.publishedDate
            Glide.with(imgThumbnail)
                .load(book.thumbnail)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .into(binding.imgThumbnail)

            root.setOnClickListener { onItemClick(book) }
        }
    }

    class BookDiffCallback : DiffUtil.ItemCallback<BookEntity>() {
        override fun areItemsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean =
            oldItem == newItem
    }
}