package com.example.fpm0322.wanandroid.utils

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.util.AttributeSet
import android.util.Log
import android.view.View

// FAB 行为控制器
class ScaleDownShowBehavior(context: Context, attrs: AttributeSet) : FloatingActionButton.Behavior() {

    private var isAnimateIng = false   // 是否正在动画
    private var isShow = true  // 是否已经显示

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton
                                     , directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        Log.e("ScaleDownShowBehavior","onStartNestedScroll")
        return if (axes == ViewCompat.SCROLL_AXIS_VERTICAL) {
            true
        } else return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type)
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton
                                , target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        if ((dyConsumed > 0 || dyUnconsumed > 0) && !isAnimateIng && isShow) {// 手指上滑，隐藏FAB
            Log.e("ScaleDownShowBehavior","onNestedScroll hide")
            AnimatorUtil().translateHide(child, object : StateListener() {
                override fun onAnimationStart(view: View) {
                    super.onAnimationStart(view)
                    isShow = false
                }
            })
        } else if (dyConsumed < 0 || dyUnconsumed < 0 && !isAnimateIng && !isShow) {
            Log.e("ScaleDownShowBehavior","onNestedScroll show")
            AnimatorUtil().translateShow(child, object : StateListener() {
                override fun onAnimationStart(view: View) {
                    super.onAnimationStart(view)
                    isShow = true
                }
            })// 手指下滑，显示FAB
        }
    }

    internal open inner class StateListener : ViewPropertyAnimatorListener {
        override fun onAnimationStart(view: View) {
            isAnimateIng = true
        }

        override fun onAnimationEnd(view: View) {
            isAnimateIng = false
        }

        override fun onAnimationCancel(view: View) {
            isAnimateIng = false
        }
    }
}
