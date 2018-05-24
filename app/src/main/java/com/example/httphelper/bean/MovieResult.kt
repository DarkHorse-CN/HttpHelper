package com.example.httphelper.bean

/**
 * Created by DarkHorse on 2018/2/4.
 */

class MovieResult {
    var count: Int = 0
    var total: Int = 0
    var title: String? = null
    var subjects: List<Movie>? = null

    override fun toString(): String {
        return "MovieResult{" +
                "count=" + count +
                ", total=" + total +
                ", title='" + title + '\''.toString() +
                ", subjects=" + subjects +
                '}'.toString()
    }
}
