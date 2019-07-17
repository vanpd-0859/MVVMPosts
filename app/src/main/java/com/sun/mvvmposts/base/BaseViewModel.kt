package com.sun.mvvmposts.base

import androidx.lifecycle.ViewModel
import com.sun.mvvmposts.injection.component.DaggerViewModelInjector
import com.sun.mvvmposts.injection.component.ViewModelInjector
import com.sun.mvvmposts.injection.module.NetworkModule
import com.sun.mvvmposts.ui.post.PostListViewModel

abstract class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when(this) {
            is PostListViewModel -> injector.inject(this)
        }
    }
}