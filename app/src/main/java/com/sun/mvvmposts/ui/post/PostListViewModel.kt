package com.sun.mvvmposts.ui.post

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.sun.mvvmposts.R
import com.sun.mvvmposts.base.BaseViewModel
import com.sun.mvvmposts.model.Post
import com.sun.mvvmposts.model.PostDao
import com.sun.mvvmposts.network.PostApi
import com.sun.mvvmposts.utils.extension.async
import io.reactivex.Single
import javax.inject.Inject

class PostListViewModel(private val postDao: PostDao): BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi

    val loadingVisibility: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }
    val postListAdapter: PostListAdapter = PostListAdapter()

    init {
        loadPosts()
    }

    private fun loadPosts() {
        showLoading()
        val posts = Single.fromCallable { postDao.all }
            .flatMap { dbPostList ->
                if (dbPostList.isEmpty())
                    postApi.getPosts().flatMap { apiPostList ->
                        postDao.insertAll(*apiPostList.toTypedArray())
                        Single.just(apiPostList)
                    }
                else
                    Single.just(dbPostList)
            }
            .async()
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { onRetrievePostListError() }
            )
        composite.add(posts)
    }

    private fun showLoading(){
        loadingVisibility.value = true
        errorMessage.value = null
    }

    private fun hideLoading(){
        loadingVisibility.value = false
    }

    private fun onRetrievePostListSuccess(postList: List<Post>){
        postListAdapter.updatePostList(postList)
        hideLoading()
    }

    private fun onRetrievePostListError(){
        errorMessage.value = R.string.post_error
        hideLoading()
    }
}