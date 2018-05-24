package com.example.httphelper.retrofit

import com.example.httphelper.bean.BaseResponse
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Description:
 * Created by DarkHorse on 2018/5/16.
 */
abstract class HttpObserver<T> : Observer<BaseResponse<T>> {

    abstract fun onSuccess(bean: T, msg: String)

    abstract fun onFailure(code: Int, msg: String)

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: BaseResponse<T>) {
        if (API.SUCCESS_CODE == t.code) {
            onSuccess(t.data, t.msg)
        } else {
            onFailure(t.code, t.msg)
        }
    }

    override fun onError(e: Throwable) {
    }
}
