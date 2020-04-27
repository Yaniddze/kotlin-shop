package com.example.kotlin_shop.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import moxy.MvpPresenter
import moxy.MvpView

abstract class BasePresenter<T : MvpView>: MvpPresenter<T>() {

    private val job = SupervisorJob()

    protected val scope = CoroutineScope(Dispatchers.Main + job)

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}