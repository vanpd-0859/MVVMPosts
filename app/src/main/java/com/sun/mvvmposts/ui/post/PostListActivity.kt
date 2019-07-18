package com.sun.mvvmposts.ui.post

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sun.mvvmposts.R
import com.sun.mvvmposts.base.BaseActivity
import com.sun.mvvmposts.databinding.ActivityPostListBinding
import com.sun.mvvmposts.utils.extension.showError
import kotlinx.android.synthetic.main.activity_post_list.*


class PostListActivity: BaseActivity() {
    private lateinit var mBinding: ActivityPostListBinding
    private lateinit var mViewModel: PostListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_post_list)
        mBinding.postList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mViewModel = ViewModelProviders.of(this).get(PostListViewModel::class.java)
        mViewModel.errorMessage.observe(this, Observer {
            errorMessage -> cl_post_list.showError(errorMessage, Pair(R.string.retry, mViewModel.errorClickListener))
        })
        mBinding.viewModel = mViewModel
    }
}
