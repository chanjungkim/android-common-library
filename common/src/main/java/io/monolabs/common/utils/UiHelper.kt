package io.monolabs.common.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View

class UiHelper {
    private var mContext: Context? = null
    private var newUiOptions = 0

    fun enableHideStatusBar(): UiHelper? {
        if (Build.VERSION.SDK_INT >= 15) {
            newUiOptions = newUiOptions xor View.STATUS_BAR_HIDDEN
        }

        return instance
    }

    fun enableHideNavigation(): UiHelper? {
        // 아이스크림 샌드위치(4.0) 이상일 경우
        if (Build.VERSION.SDK_INT >= 14) {
            newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }

        return instance
    }

    fun enableFullScreen(): UiHelper? {
        // 젤리빈(4.1) 이상일 경우
        if (Build.VERSION.SDK_INT >= 16) {
            newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_FULLSCREEN
        }

        return instance
    }

    fun setImmerseiveSticky(): UiHelper? {
        // 킷캣(4.4) 이상일 경우
        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            return instance
        }

        return instance
    }

    fun setStatusBarColor(color: Int): UiHelper? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // finally change the color
            (mContext as Activity).window.statusBarColor = color
        }

        return instance
    }

    fun apply() {
        (mContext as Activity).window.decorView.systemUiVisibility = newUiOptions
    }

    companion object {
        @Volatile
        private var instance: UiHelper? = null

        fun getInstance(): UiHelper {
            if (instance == null) {
                synchronized(UiHelper::class.java) {
                    if (instance == null) {
                        instance = UiHelper()
                    }
                }
            }
            return instance!!
        }
    }
}