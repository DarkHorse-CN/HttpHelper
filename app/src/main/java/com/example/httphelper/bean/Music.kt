package com.example.httphelper.bean

/**
 * Created by DarkHorse on 2018/2/4.
 */

class Music {
    var id: Long = 0
    var title: String? = null
    var image: String? = null
    var alt: String? = null

    override fun toString(): String {
        return "Music{" +
                "id=" + id +
                ", title='" + title + '\''.toString() +
                ", image='" + image + '\''.toString() +
                ", alt='" + alt + '\''.toString() +
                '}'.toString()
    }
}
