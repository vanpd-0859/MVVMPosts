package com.sun.mvvmposts.base

import androidx.lifecycle.ViewModel
import com.sun.mvvmposts.injection.component.DaggerViewModelInjector
import com.sun.mvvmposts.injection.component.ViewModelInjector
import com.sun.mvvmposts.injection.module.NetworkModule
import com.sun.mvvmposts.ui.post.PostListViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel: ViewModel() {
    var disposable: CompositeDisposable? = null

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
        disposable = CompositeDisposable()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when(this) {
            is PostListViewModel -> injector.inject(this)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()

    }

    protected fun rx(block: () -> Disposable?) {
        if (disposable == null) throw IllegalArgumentException(
            "No worry! You've just forgot inject disposable, DO IT!")
        block()?.let { disposable?.add(it) }
    }
}
