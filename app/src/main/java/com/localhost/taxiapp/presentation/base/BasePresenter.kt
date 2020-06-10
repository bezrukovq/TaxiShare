package com.md.nails.presentation.basemvp

import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<T : BaseView> : MvpPresenter<T>(), CoroutineScope {

    val compositeDisposable = CompositeDisposable()

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = job + Dispatchers.Main
    val handler = CoroutineExceptionHandler{_,exception->
        viewState.showMessage(exception.message.toString())
    }

    override fun destroyView(view: T) {
        super.destroyView(view)
        compositeDisposable.clear()
        job.cancelChildren()
    }

    protected fun Disposable.disposeWhenDestroy() {
        compositeDisposable.add(this)
    }

}