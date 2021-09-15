package io.monolabs.common.utils

import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser

object TextUtil {
    fun toPrettyJson(src: Any): String {
        val str = Gson().toJson(src)
        val gson: Gson = GsonBuilder().setPrettyPrinting().create()
        val jp = JsonParser()
        val je: JsonElement = jp.parse(str)

        return gson.toJson(je)
    }

    fun convertStringToPrettyJson(str: String): String {
        val gson: Gson = GsonBuilder().setPrettyPrinting().create()
        val jp = JsonParser()
        val je: JsonElement = jp.parse(str)

        return gson.toJson(je)
    }

    fun applyDifferentText(str: String): Spanned {
        val ss1 = SpannableString(str)
        val p1 = str.indexOf("(")
        val p2 = str.indexOf(")")

        if (p1 < 0 || p2 < 0) {
            return ss1
        }
        ss1.setSpan(RelativeSizeSpan(0.8f), p1, p2 + 1, 0) // set size
//        ss1.setSpan(ForegroundColorSpan(Color.RED), p1, p2+1, 0) // set color
        return ss1
    }

    fun unicodeToChars(str: String): String {
        var str: String = str.split(" ").get(0)
        str = str.replace("\\", "")

        val arr = str.split("u").toTypedArray()
        var text = ""
        for (i in 1 until arr.size) {
            val hexVal = arr[i].toInt(16)
            text += hexVal.toChar()
        }

        return text
    }

    fun toStringFromHtml(html: String): String {
        val sb = StringBuilder("")

        var flag = true

        // <p>{pp}</p>
        for (c in html) {
            when (c) {
                '<' -> {
                    flag = false
                }
                '>' -> {
                    flag = true
                }
                else -> {
                    if (!flag) {
                        continue
                    }
                    sb.append(c)
                }
            }
        }

        return sb.toString()
    }

    fun toHtmlText(htmlStr: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(htmlStr, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(htmlStr)
        }
    }
}