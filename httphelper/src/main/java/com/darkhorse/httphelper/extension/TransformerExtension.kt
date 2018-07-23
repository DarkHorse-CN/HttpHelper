package com.darkhorse.httphelper.extension

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Description:
 * Created by DarkHorse on 2018/7/23.
 */
fun <T> Observable<T>.io_main() = subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())