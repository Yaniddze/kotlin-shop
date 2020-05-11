package com.example.kotlin_shop.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.MvpView

abstract class BasePresenter<T : MvpView> : MvpPresenter<T>() {

    private val job = SupervisorJob()

    private val scope = CoroutineScope(Dispatchers.Main + job)

    protected fun launch(action: suspend () -> Unit) {
        scope.launch {
            action()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}