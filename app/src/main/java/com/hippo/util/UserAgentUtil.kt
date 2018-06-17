package com.hippo.util

import android.content.Context
import android.webkit.WebSettings

object UserAgentUtil {

    fun getUserAgent(context: Context): String {
        var userAgent = ""
        userAgent = try {
            WebSettings.getDefaultUserAgent(context)
        } catch (e: Exception) {
            System.getProperty("http.agent")
        }

        val sb = StringBuilder()
        var i = 0
        val length = userAgent.length
        while (i < length) {
            val c = userAgent[i]
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", c.toInt()))
            } else {
                sb.append(c)
            }
            i++
        }
        return sb.toString()
    }
}