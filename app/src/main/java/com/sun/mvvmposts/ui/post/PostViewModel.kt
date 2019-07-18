package com.sun.mvvmposts.ui.post

import androidx.lifecycle.MutableLiveData
import com.sun.mvvmposts.base.BaseViewModel
import com.sun.mvvmposts.model.Post

class PostViewModel: BaseViewModel() {
    private val mPostTitle = MutableLiveData<String>()
    private val mPostBody = MutableLiveData<String>()

    fun bind(post: Post){
        mPostTitle.value = post.title
        mPostBody.value = post.body
    }

    fun getPostTitle():MutableLiveData<String>{
        return mPostTitle
    }

    fun getPostBody():MutableLiveData<String>{
        return mPostBody
    }
}
