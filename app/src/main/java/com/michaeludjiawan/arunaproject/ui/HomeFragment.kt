package com.michaeludjiawan.arunaproject.ui

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.michaeludjiawan.arunaproject.R
import com.michaeludjiawan.arunaproject.data.model.Post
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel by viewModel<HomeViewModel>()

    private lateinit var postAdapter: PostAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSearch()
        initRecyclerView()
        initObservers()
    }

    private fun initSearch() {
        et_home_search_input.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                btn_home_search.performClick()
                true
            } else {
                false
            }
        }

        btn_home_search.setOnClickListener {
            val query = et_home_search_input.text.toString()
            viewModel.getPosts(query)
        }
    }

    private fun initRecyclerView() {
        postAdapter = PostAdapter(viewModel.getCurrentPosts())

        rv_home_posts.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = postAdapter
        }
    }

    private fun initObservers() {
        viewModel.postsResult.observe(viewLifecycleOwner, { result ->
            handleStateVisibility(result)

            if (result is UiResult.Success) {
                handleStateVisibility(result)
                viewModel.setCurrentPosts(result.data)
                updateDataSetChanged()
            }
        })

        btn_home_error.setOnClickListener {
            et_home_search_input.text = null
            viewModel.getPosts()
        }
    }

    private fun handleStateVisibility(result: UiResult<List<Post>>) {
        pb_home_loader.isVisible = result is UiResult.Loading
        btn_home_error.isVisible = result is UiResult.Error
        rv_home_posts.isVisible = result is UiResult.Success
        tv_home_empty.isVisible = result is UiResult.Success && result.data.isEmpty()
    }

    private fun updateDataSetChanged() {
        rv_home_posts.adapter?.notifyDataSetChanged()
    }

}