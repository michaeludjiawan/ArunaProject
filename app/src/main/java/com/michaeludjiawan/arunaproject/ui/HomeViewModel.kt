package com.michaeludjiawan.arunaproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michaeludjiawan.arunaproject.data.model.Post
import com.michaeludjiawan.arunaproject.data.repository.PostRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val postRepository: PostRepository
) : ViewModel() {

    private val mutablePostsResult = MutableLiveData<UiResult<List<Post>>>()
    val postsResult: LiveData<UiResult<List<Post>>> = mutablePostsResult

    private var currentPosts = ArrayList<Post>()

    fun getCurrentPosts(): List<Post> = currentPosts

    init {
        getPosts()
    }

    fun getPosts(forceRefresh: Boolean = false) {
        mutablePostsResult.value = UiResult.Loading()

        viewModelScope.launch {
            mutablePostsResult.value = postRepository.getPosts(forceRefresh).toUiResult()
        }
    }

    fun setCurrentPosts(data: List<Post>) {
        currentPosts.clear()
        currentPosts.addAll(data)
    }

}