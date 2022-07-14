package com.mrmansur.android_mvi.network

import com.mrmansur.android_mvi.model.Post
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostService {
    @GET("posts")
    suspend fun allPosts() : ArrayList<Post>

    @DELETE("post/{id}")
    suspend fun deletePost(@Path("id") id : Int) : Post
}