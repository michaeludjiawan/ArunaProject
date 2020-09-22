package com.michaeludjiawan.arunaproject.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.michaeludjiawan.arunaproject.R
import com.michaeludjiawan.arunaproject.data.model.Post
import kotlinx.android.synthetic.main.item_post.view.*

class PostAdapter(
    private val posts: List<Post>
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int = posts.size

    inner class PostViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(post: Post) = with(view) {
            tv_post_title.text = post.title
            tv_post_body.text = post.body
        }
    }
}