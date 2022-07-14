package com.mrmansur.android_mvi.activity.halper

import com.mrmansur.android_mvi.model.Post

interface MainHelper {
    suspend fun allPosts(): ArrayList<Post>
    suspend fun deletePost(id: Int): Post
}