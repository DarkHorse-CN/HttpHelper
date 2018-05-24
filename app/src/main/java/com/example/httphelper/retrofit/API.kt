package com.example.httphelper.retrofit

import com.darkhorse.httphelper.HttpHelper

/**
 * Created by DarkHorse on 2018/2/4.
 */

class API {
    companion object {
        const val MUSIC = "https://api.douban.com/"
        const val MOVIE = "https://api.douban.com/"
        const val SHOP = "http://kj.goodbooy.cn/api/"
        const val HHH = "http://hhh.ljzchris.cn/api/"
        const val SUCCESS_CODE = 1
        const val SINGLE_TOKEN = "token"
        const val SHORT_TOKEN = "sign_api"
        const val LONG_TOKEN = "sign_login"

        val movieService = HttpHelper.getService(MovieService::class.java)
        val musicService = HttpHelper.getService(MusicService::class.java)
        val shopService = HttpHelper.getService(HHHService::class.java)
    }
}
