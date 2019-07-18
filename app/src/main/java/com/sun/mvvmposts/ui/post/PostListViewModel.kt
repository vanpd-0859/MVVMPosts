package com.sun.mvvmposts.ui.post

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.sun.mvvmposts.R
import com.sun.mvvmposts.base.BaseViewModel
import com.sun.mvvmposts.model.Post
import com.sun.mvvmposts.network.PostApi
import com.sun.mvvmposts.utils.extension.async
import com.sun.mvvmposts.utils.extension.loading
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException
import javax.inject.Inject

class PostListViewModel: BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi

    val loadingVisibility: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage:MutableLiveData<Throwable> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }
    val postListAdapter: PostListAdapter = PostListAdapter()

    init {
        loadPosts()
    }

    private fun loadPosts() {
        rx {
            postApi.getPosts()
                .async()
                .loading(loadingVisibility)
                .subscribe(
                    {
                            postList -> postListAdapter.updatePostList(postList)
                    },
                    {
                            errorMessage.value = it
                    }
                )
        }
    }
}
