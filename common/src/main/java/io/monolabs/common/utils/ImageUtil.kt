package io.monolabs.common.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

object ImageUtil {
    private lateinit var src: String

    //    private var bitmap: Bitmap? = null
    private val mHandler = Handler()
    private val networkRunnable: Runnable = Runnable {
        val url = URL(src)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        connection.doInput = true
        connection.connect()
        val input: InputStream = connection.inputStream
        val myBitmap = BitmapFactory.decodeStream(input)
        Log.e("Bitmap", "returned")
        myBitmap
    }

    fun getBitmapFromUrl(url: String?): Bitmap? {
        var bitmap: Bitmap? = null
        src = url ?: ""

        try {
            mHandler.removeCallbacks(networkRunnable)
            mHandler.postDelayed(networkRunnable, 0)
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("Exception", e.toString())
            null
        }

        return bitmap
    }
}