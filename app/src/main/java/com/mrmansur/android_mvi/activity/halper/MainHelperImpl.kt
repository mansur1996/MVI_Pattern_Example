package com.mrmansur.android_mvi.activity.halper

import com.mrmansur.android_mvi.model.Post
import com.mrmansur.android_mvi.network.PostService

class MainHelperImpl(private val postService: PostService) : MainHelper {

    override suspend fun allPosts(): ArrayList<Post> {
        return postService.allPosts()
    }

    override suspend fun deletePost(id: Int): Post {
        return postService.deletePost(id)
    }
}