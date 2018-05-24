package com.example.httphelper.bean

/**
 * Description:
 * Created by DarkHorse on 2018/5/16.
 */
data class BaseResponse<T>(var status: String, var code: Int, var msg: String, var data: T)
