package club.multilab.music.utils.log

import io.github.aakira.napier.Antilog
import io.github.aakira.napier.Napier

fun interface Logger {

    fun logTag(): String

    fun logI(message: String) {
        Napier.i(getLogRow(message, tag()))
    }

    fun logD(message: String) {
        Napier.d(getLogRow(message, tag()))
    }

    fun logE(message: String, throwable: Throwable? = null) {
        throwable?.printStackTrace()
        Napier.e(getLogRow(message, tag()))
    }

    fun logW(message: String, throwable: Throwable? = null) {
        throwable?.printStackTrace()
        Napier.w(getLogRow(message, tag()))
    }

    private fun spacingTag() = "\n\n${logTag()}"

    private fun tag() = spacingTag()

    private fun getLogRow(message: String, tag: String = "", mainTag: String = LOG_TAG_MAIN) = "- - - $mainTag - $tag - - - $message"
}

fun initLogger(mainLogTag: String, antilog: Antilog) {
    Napier.base(antilog)
    LOG_TAG_MAIN += "/$mainLogTag"
}

private var LOG_TAG_MAIN = "MusicService"