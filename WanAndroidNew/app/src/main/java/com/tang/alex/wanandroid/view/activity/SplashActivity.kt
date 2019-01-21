package com.tang.alex.wanandroid.view.activity

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import com.tang.alex.wanandroid.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    var currentActivity: SplashActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initView()
    }

    private fun initView() {
        currentActivity = this
        val animator:ObjectAnimator = ObjectAnimator.ofFloat(iv_logo,"rotation", 0f, 360f, 0f)
        animator.target = iv_logo
        animator.duration = 4000
        animator.start()
        animator.addListener(object: Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                var compat = ActivityOptionsCompat.makeSceneTransitionAnimation(currentActivity!!)
                val intent = Intent(currentActivity!!, LoginActivity().javaClass)
                ActivityCompat.startActivity(currentActivity!!, intent, compat.toBundle())
                finish()
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })
    }
}
