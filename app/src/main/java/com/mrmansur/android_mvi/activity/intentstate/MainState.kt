package com.mrmansur.android_mvi.activity.intentstate

import com.mrmansur.android_mvi.model.Post

sealed class MainState {
    object Init : MainState()
    object Loading : MainState()

    data class AllPosts(val posts: ArrayList<Post>) : MainState() // on success
    data class DeletePost(val post: Post) : MainState() // on success

    data class Error(val error: String?) : MainState()
}