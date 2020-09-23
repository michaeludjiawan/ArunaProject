package com.michaeludjiawan.arunaproject.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.michaeludjiawan.arunaproject.data.api.Result
import com.michaeludjiawan.arunaproject.data.model.Post
import com.michaeludjiawan.arunaproject.data.repository.PostRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.Silent::class)
class HomeViewModelTest {

    private val postRepository = mock<PostRepository>()
    private lateinit var viewModel: HomeViewModel

    private val dummyPosts = listOf(
        Post(1, 1, "Title 1", "Body 1"),
        Post(2, 3, "Title 2", "Body 2"),
        Post(2, 3, "Title 3", "Body 3")
    )

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        runBlockingTest {
            whenever(postRepository.getPosts("")).thenReturn(Result.Success(listOf()))
        }

        viewModel = HomeViewModel(postRepository)
    }

    @Test
    fun fetchOnCreated_verifyCalled() = runBlockingTest {
        verify(postRepository).getPosts("")
    }

    @Test
    fun getPosts_fetchFromRepo_verifyCalled() = runBlockingTest {
        verify(postRepository).getPosts("")

        viewModel.getPosts("")

        verify(postRepository, times(2)).getPosts("")
    }

    @Test
    fun currentPosts_addSuccess() {
        val posts = viewModel.getCurrentPosts()

        assert(posts.isEmpty())

        viewModel.setCurrentPosts(dummyPosts)

        assert(posts.size == dummyPosts.size)
    }

    @Test
    fun currentPosts_clearOnReset() {
        val posts = viewModel.getCurrentPosts()
        viewModel.setCurrentPosts(dummyPosts)

        assert(posts.isNotEmpty())

        viewModel.setCurrentPosts(dummyPosts)

        assert(posts.size == dummyPosts.size)
    }
}