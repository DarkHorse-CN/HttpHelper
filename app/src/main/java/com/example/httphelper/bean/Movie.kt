package com.example.httphelper.bean

/**
 * Created by DarkHorse on 2018/2/4.
 */

class Movie {
    var id: Long = 0
    var title: String? = null
    var year: Int = 0
    var alt: String? = null

    override fun toString(): String {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\''.toString() +
                ", year=" + year +
                ", alt='" + alt + '\''.toString() +
                '}'.toString()
    }
}
