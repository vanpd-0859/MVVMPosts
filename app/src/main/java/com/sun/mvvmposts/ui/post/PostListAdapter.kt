package com.sun.mvvmposts.ui.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sun.mvvmposts.R
import com.sun.mvvmposts.databinding.RowPostItemBinding
import com.sun.mvvmposts.model.Post

class PostListAdapter: RecyclerView.Adapter<PostListAdapter.ViewHolder>() {
    private lateinit var mPostList:List<Post>

    override fun getItemCount(): Int {
        return if (::mPostList.isInitialized) mPostList.size else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RowPostItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_post_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mPostList[position])
    }

    fun updatePostList(postList:List<Post>){
        this.mPostList = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: RowPostItemBinding): RecyclerView.ViewHolder(binding.root) {
        private val viewModel = PostViewModel()

        fun bind(post:Post){
            viewModel.bind(post)
            binding.viewModel = viewModel
        }
    }
}
