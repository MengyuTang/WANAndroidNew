package com.tang.alex.wanandroid.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.text.TextUtils
import android.transition.Fade
import android.util.Log
import android.view.View
import android.widget.Toast
import com.tang.alex.wanandroid.R
import com.tang.alex.wanandroid.model.bean.BaseBean
import com.tang.alex.wanandroid.presentor.LoginPresentor
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.layout_header.*

class LoginActivity : BaseActivity() {

    val TAG:String = "LoginActivity"

    var currentActivity: LoginActivity? = null

    private val loginPresentor = LoginPresentor()

    private var typeLoginOrReg = "login"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.enterTransition = Fade().setDuration(2000)
        window.exitTransition = null
        initView()
    }

    private fun initView() {
        currentActivity = this
        loginPresentor.onAttachView(this)
        tv_title.text = "登录"
        et_pass_word_sec.visibility = View.GONE
        iv_back.visibility = View.GONE
        btn_login.setOnClickListener {
            if (typeLoginOrReg == "login"){
                val userName = et_user_name.text.toString().trim()
                val passWord = et_pass_word.text.toString().trim()
                login(userName,passWord)
            }else if (typeLoginOrReg == "register"){
                val userName = et_user_name.text.toString().trim()
                val passWord = et_pass_word.text.toString().trim()
                val repassWord = et_pass_word_sec.text.toString().trim()
                register(userName,passWord,repassWord)
            }
        }
        btn_register.setOnClickListener {
            if (typeLoginOrReg == "register"){
                typeLoginOrReg = "login"
                et_pass_word_sec.visibility = View.GONE
                btn_login.text = "登录"
                btn_register.text = "注册"
                tv_title.text = "登录"
            }else if (typeLoginOrReg == "login"){
                typeLoginOrReg = "register"
                et_pass_word_sec.visibility = View.VISIBLE
                btn_login.text = "注册"
                btn_register.text = "登录"
                tv_title.text = "注册"
            }
        }
    }

    private fun register(userName: String, passWord: String, repassWord: String) {
        if (TextUtils.isEmpty(userName)){
            Toast.makeText(currentActivity,"用户名不能为空",Toast.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(passWord)){
            Toast.makeText(currentActivity,"密码不能为空",Toast.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(repassWord)){
            Toast.makeText(currentActivity,"密码不能为空",Toast.LENGTH_SHORT).show()
            return
        }
        val map = HashMap<String,String>()
        map["username"] = userName
        map["password"] = passWord
        map["repassword"] = repassWord
        loginPresentor.register(map)
    }

    private fun login(userName:String,passWord:String){
        if (TextUtils.isEmpty(userName)){
            Toast.makeText(currentActivity,"用户名不能为空",Toast.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(passWord)){
            Toast.makeText(currentActivity,"密码不能为空",Toast.LENGTH_SHORT).show()
            return
        }
        val map = HashMap<String,String>()
        map["username"] = userName
        map["password"] = passWord
        loginPresentor.getData(map)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun showLoadingDialog() {
        Log.e(TAG,"showLoadingDialog!!!")
    }

    override fun dismissDialog() {
        Log.e(TAG,"dismissDialog!!!")
    }

    override fun showData(data: BaseBean) {
        if (typeLoginOrReg == "register"){
            Toast.makeText(currentActivity,"恭喜您，注册成功，快去登录吧",Toast.LENGTH_SHORT).show()
            typeLoginOrReg = "login"
            et_pass_word_sec.visibility = View.GONE
            btn_login.text = "登录"
            btn_register.text = "注册"
            tv_title.text = "登录"
        }else if (typeLoginOrReg == "login"){
            val intent = Intent(currentActivity, MainActivity().javaClass)
            val compat = ActivityOptionsCompat.makeSceneTransitionAnimation(currentActivity!!)
            ActivityCompat.startActivity(currentActivity!!, intent, compat.toBundle())
            finish()
        }
    }

    override fun showErrorMessage(errMsg: String) {
        Toast.makeText(currentActivity,errMsg,Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        loginPresentor.onDetachView()
    }
}
