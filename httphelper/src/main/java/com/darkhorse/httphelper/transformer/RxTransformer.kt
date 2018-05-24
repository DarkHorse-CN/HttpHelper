package com.darkhorse.httphelper.transformer

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Description:
 * Created by DarkHorse on 2018/5/15.
 */
class RxTransformer {
    companion object {
        fun <T> io_main(): ObservableTransformer<T, T> {
            return ObservableTransformer { tObservable ->
                tObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }

}
