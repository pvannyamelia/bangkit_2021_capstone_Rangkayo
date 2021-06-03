package com.capstone.rangkayo.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.request.RequestOptions
import com.capstone.rangkayo.DetailActivty
import com.capstone.rangkayo.DetailActivty.Companion.EXTRA_LINK
import com.capstone.rangkayo.R
import com.capstone.rangkayo.data.source.local.entity.NewsEntity
import com.capstone.rangkayo.databinding.ItemNewsBinding
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class HomeAdapter :
    PagedListAdapter<NewsEntity, HomeAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsEntity>() {
            override fun areItemsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position) as NewsEntity)

    }

    inner class MovieViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(news: NewsEntity) {
            with(binding) {
                tvTitle.text = news.title
                tvDescription.text = news.summary

                val multi = MultiTransformation(
                    RoundedCornersTransformation(16, 0, RoundedCornersTransformation.CornerType.ALL)
                )
                Glide.with(itemView.context)
                    .load(news.media)
                    .apply(RequestOptions.bitmapTransform(multi))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPhoto)
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivty::class.java)
                intent.putExtra(EXTRA_LINK, news.link)
                itemView.context.startActivity(intent)
            }

        }
    }
}