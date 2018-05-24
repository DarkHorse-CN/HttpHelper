package com.example.httphelper

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.darkhorse.httphelper.transformer.RxTransformer
import com.darkhorse.preferencesmanager.PreferencesHelper
import com.example.httphelper.bean.BillBean
import com.example.httphelper.bean.TestBean
import com.example.httphelper.retrofit.API
import com.example.httphelper.retrofit.HttpObserver

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        API.shopService.getSetting()
                .compose(RxTransformer.io_main())
                .subscribe(object : HttpObserver<BillBean>() {
                    override fun onSuccess(bean: BillBean, msg: String) {
                        Log.i("tag", bean.toString())
                    }

                    override fun onFailure(code: Int, msg: String) {

                    }
                })

    }

}

