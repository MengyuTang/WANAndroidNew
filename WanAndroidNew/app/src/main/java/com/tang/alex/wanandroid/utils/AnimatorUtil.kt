package com.example.fpm0322.wanandroid.utils

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator

class AnimatorUtil {

    private val FAST_OUT_SLOW_IN_INTERPOLATOR = LinearOutSlowInInterpolator()

    private val LINER_INTERPOLATOR = AccelerateInterpolator()

    // 显示view
    fun scaleShow(view: View, viewPropertyAnimatorListener: ViewPropertyAnimatorListener) {
        view.visibility = View.VISIBLE
        ViewCompat.animate(view)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .alpha(1.0f)
                .setDuration(800)
                .setListener(viewPropertyAnimatorListener)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                .start()
    }

    // 隐藏view
    fun scaleHide(view: View, viewPropertyAnimatorListener: ViewPropertyAnimatorListener) {
        ViewCompat.animate(view)
                .scaleX(0.0f)
                .scaleY(0.0f)
                .alpha(0.0f)
                .setDuration(800)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                .setListener(viewPropertyAnimatorListener)
                .start()
    }

    // 显示view
    fun translateShow(view: View, viewPropertyAnimatorListener: ViewPropertyAnimatorListener) {
        Log.e("AnimatorUtil","translateShow show")
        view.visibility = View.VISIBLE
        ViewCompat.animate(view)
                .translationY(0f)
                .setDuration(200)
                .setListener(viewPropertyAnimatorListener)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                .start()
    }

    // 隐藏view
    fun translateHide(view: View, viewPropertyAnimatorListener: ViewPropertyAnimatorListener) {
        Log.e("AnimatorUtil","translateShow hide")
        view.visibility = View.VISIBLE
        ViewCompat.animate(view)
                .translationY(320f)
                .setDuration(200)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                .setListener(viewPropertyAnimatorListener)
                .start()
    }
}
