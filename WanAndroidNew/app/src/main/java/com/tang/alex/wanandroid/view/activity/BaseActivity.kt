package com.tang.alex.wanandroid.view.activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.tang.alex.wanandroid.model.bean.BaseBean
import com.tang.alex.wanandroid.view.IView

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(),IView {
    override fun showLoadingDialog() {
    }

    override fun dismissDialog() {
    }

    override fun showData(data: BaseBean) {
    }

    override fun showErrorMessage(errMsg: String) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val localLayoutParams = window.attributes
        localLayoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or localLayoutParams.flags
    }

    /**
     * 沉浸式状态栏
     */
    fun dealStatusColor() {
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = this.window.decorView
            val option = 1280
            decorView.systemUiVisibility = option
            this.window.statusBarColor = 0
        } else if (Build.VERSION.SDK_INT >= 19) {
            val params = this.window.attributes
            params.flags = params.flags or 67108864
        }
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token 调用的view依附在哪个window的令牌
     */
    private fun hideKeyboard(token: IBinder?) {
        if (token != null) {
            val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    /**
     * Description 触摸事件处理
     * @param ev 触摸动作
     * @return  是否拦截触摸事件
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v!!.windowToken)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标想对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v 获取焦点的view
     * @param event 触摸事件
     * @return true 隐藏键盘，false 显示键盘
     */
    private fun isShouldHideKeyboard(v: View?, event: MotionEvent): Boolean {
        if (v != null && v is EditText) {
            val l = intArrayOf(0, 0)
            v.getLocationInWindow(l)
            val left = l[0]
            val top = l[1]
            val bottom = top + v.height
            val right = left + v.width
            return !(event.x > left && event.x < right
                    && event.y > top && event.y < bottom)
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false
    }
}
