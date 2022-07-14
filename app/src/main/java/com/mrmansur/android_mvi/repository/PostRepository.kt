package com.mrmansur.android_mvi.repository

import com.mrmansur.android_mvi.activity.halper.MainHelper

class PostRepository(private val mainHelper: MainHelper)  {
    suspend fun allPosts() = mainHelper.allPosts()
    suspend fun deletePost(id : Int) = mainHelper.deletePost(id)
}