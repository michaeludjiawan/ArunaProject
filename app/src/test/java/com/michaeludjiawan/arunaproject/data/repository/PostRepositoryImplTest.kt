package com.michaeludjiawan.arunaproject.data.repository

import com.michaeludjiawan.arunaproject.data.api.ApiService
import com.michaeludjiawan.arunaproject.data.local.AppDb
import com.michaeludjiawan.arunaproject.data.local.AppPreferences
import com.michaeludjiawan.arunaproject.data.local.PostDao
import com.michaeludjiawan.arunaproject.util.Constants
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.Silent::class)
class PostRepositoryImplTest {

    private lateinit var postRepositoryImpl: PostRepositoryImpl

    private val apiService = mock<ApiService>()
    private val appDb = mock<AppDb>()
    private val repoDao = mock<PostDao>()
    private val appPreferences = mock<AppPreferences>()

    @Before
    fun setup() {
        postRepositoryImpl = PostRepositoryImpl(apiService, appDb, appPreferences)
    }

    @Test
    fun getPosts_cacheInvalid_fetchServer() = runBlockingTest {
        setupCacheValid(false)

        postRepositoryImpl.getPosts("")

        verify(apiService).getPosts()
    }

    @Test
    fun getPosts_cacheValid_getLocal() = runBlockingTest {
        setupCacheValid(true)
        whenever(appDb.postDao()).thenReturn(repoDao)
        whenever(repoDao.getPosts("")).thenReturn(listOf())

        postRepositoryImpl.getPosts("")

        verify(repoDao).getPosts("")
    }

    private fun setupCacheValid(isValid: Boolean) {
        val lastFetchTimestamp = if (isValid) System.currentTimeMillis() + 100000 else 0
        whenever(appPreferences.contains(Constants.API_LAST_FETCH_TIMESTAMP)).thenReturn(true)
        whenever(appPreferences.getLong(Constants.API_LAST_FETCH_TIMESTAMP)).thenReturn(
            lastFetchTimestamp
        )
    }

}