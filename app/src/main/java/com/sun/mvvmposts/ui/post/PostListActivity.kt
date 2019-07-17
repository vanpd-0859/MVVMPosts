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
import com.sun.mvvmposts.utils.extension.hideError
import kotlinx.android.synthetic.main.activity_post_list.*


class PostListActivity: BaseActivity() {
    private lateinit var binding: ActivityPostListBinding
    private lateinit var viewModel: PostListViewModel
    private var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_list)
        binding.postList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this).get(PostListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
            errorMessage ->
            if (errorMessage != null) {
                snackbar = content.showError(R.string.post_error,
                    Pair(R.string.retry, viewModel.errorClickListener))
            } else {
                content.hideError(snackbar)
            }
        })
        binding.viewModel = viewModel
    }
}