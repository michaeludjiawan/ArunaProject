package com.michaeludjiawan.arunaproject.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.michaeludjiawan.arunaproject.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel by viewModel<HomeViewModel>()

    private lateinit var postAdapter: PostAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initObservers()
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
            when (result) {
                is UiResult.Loading -> {

                }
                is UiResult.Success -> {
                    viewModel.setCurrentPosts(result.data)
                    updateDataSetChanged()
                }
                is UiResult.Error -> {

                }
            }
        })
    }

    private fun updateDataSetChanged() {
        rv_home_posts.adapter?.notifyDataSetChanged()
    }

}