package com.michaeludjiawan.arunaproject.ui

import androidx.lifecycle.ViewModel
import com.michaeludjiawan.arunaproject.data.model.Post

class HomeViewModel : ViewModel() {

    private var posts = ArrayList<Post>()

    fun getPosts(): List<Post> = posts
}