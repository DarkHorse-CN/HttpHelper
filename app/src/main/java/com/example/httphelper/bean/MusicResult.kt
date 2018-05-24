package com.example.httphelper.bean

/**
 * Created by DarkHorse on 2018/2/4.
 */

class MusicResult {
    var count: Int = 0
    var start: Int = 0
    var total: Int = 0
    var musics: List<Music>? = null

    override fun toString(): String {
        return "MusicResult{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", musics=" + musics +
                '}'.toString()
    }
}
