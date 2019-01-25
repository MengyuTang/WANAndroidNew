package com.tang.alex.wanandroid.view.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.transition.Fade
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.tang.alex.wanandroid.R
import com.tang.alex.wanandroid.model.bean.BaseBean
import com.tang.alex.wanandroid.presentor.LoginPresentor
import com.tang.alex.wanandroid.utils.BottomNavigationViewHelper
import com.tang.alex.wanandroid.view.fragment.HomeFragment
import com.tang.alex.wanandroid.view.IView
import com.tang.alex.wanandroid.view.fragment.GuideFragment
import com.tang.alex.wanandroid.view.fragment.KnowledgeFragment
import com.tang.alex.wanandroid.view.fragment.ProjectsFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),IView {

    private val loginPresentor = LoginPresentor()

    private var currentActivity:MainActivity? = null

    private val TAG = "MainActivity"

    private var homeFragment = HomeFragment()
    private var knowledgeFragment = KnowledgeFragment()
    private var guideFragment = GuideFragment()
    private var projectsFragment = ProjectsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.enterTransition = Fade().setDuration(1000)
        window.exitTransition = null
        val localLayoutParams = window.attributes
        localLayoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or localLayoutParams.flags
        initView()
        initFragment(savedInstanceState)
    }

    private fun initView() {
        currentActivity = this
        loginPresentor.onAttachView(this)
        toolBar.title = resources.getString(R.string.home_page)
        toolBar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolBar)
        val mDrawerToggle =
                ActionBarDrawerToggle(this,drawerLayout,toolBar, R.string.drawer_open, R.string.drawer_close)
        mDrawerToggle.syncState()
        //修改ToolBar 默认左边图标
        mDrawerToggle.isDrawerIndicatorEnabled = false
        //要替换的图标
        mDrawerToggle.setHomeAsUpIndicator(R.mipmap.more)
        //给图标设置点击事件，用于开启DrawerLayout侧边布局
        mDrawerToggle.setToolbarNavigationClickListener {
            if (!drawerLayout.isDrawerOpen(navigationView)){
                mDrawerToggle.setHomeAsUpIndicator(R.mipmap.back)
                drawerLayout.openDrawer(navigationView)
                //打开之后，不可自动关闭，只能主动调用closeDrawer方法关闭
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN)
            }else{
                drawerLayout.closeDrawers()
                //关闭之后，不锁定，可以滑动手势呼出，也可以调用openDrawer方法呼出
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                mDrawerToggle.setHomeAsUpIndicator(R.mipmap.more)
            }
        }
        drawerLayout.addDrawerListener(mDrawerToggle)
        //侧边栏
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.collection -> consume {
                    toolBar.setTitle(R.string.collection)}
                R.id.todo_tools -> consume{
                    toolBar.setTitle(R.string.todo_tools)}
                R.id.dark_mode -> consume {
                    toolBar.setTitle(R.string.dark_mode)}
                R.id.settings -> consume {
                    toolBar.setTitle(R.string.settings)}
                R.id.about_us -> consume {
                    toolBar.setTitle(R.string.about_us)}
                R.id.logout -> consume {
                    loginPresentor.logout()
                     }
                else -> consume {
                    toolBar.setTitle(R.string.home_page)}
            }
            drawerLayout.closeDrawers()
            mDrawerToggle.setHomeAsUpIndicator(R.mipmap.more)
            true
        }
        //底部导航栏
        BottomNavigationViewHelper.disableShiftMode(design_bottom_sheet)
        design_bottom_sheet.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home_page -> consume {
                    toolBar.title = "首页"
                    showFragment(homeFragment)

                }
                R.id.knowledge_system -> consume{
                    toolBar.title = "知识体系"
                    showFragment(knowledgeFragment)

            }
                R.id.guide -> consume {
                    toolBar.title = "导航"
                    showFragment(guideFragment)

                }
                R.id.projects -> consume {
                    toolBar.title = "项目"
                    showFragment(projectsFragment)

                }
                else -> consume {
                    toolBar.title = "首页"
                    showFragment(homeFragment)
                }
            }
        }
        btnSearch.setOnClickListener {
            val intent = Intent(currentActivity, SearchViewActivity().javaClass)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            val compat = ActivityOptionsCompat.makeSceneTransitionAnimation(currentActivity!!)
            ActivityCompat.startActivity(currentActivity!!, intent, compat.toBundle())
        }
    }

    private inline fun consume(f: () -> Unit): Boolean {
        f()
        return true
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

    override fun showData(data: BaseBean,type:String) {
        if (type == "logout"){
            val compat = ActivityOptionsCompat.makeSceneTransitionAnimation(currentActivity!!)
            val intent = Intent(currentActivity!!, LoginActivity().javaClass)
            ActivityCompat.startActivity(currentActivity!!, intent, compat.toBundle())
        }
    }

    override fun showErrorMessage(errMsg: String) {
        Toast.makeText(currentActivity,errMsg, Toast.LENGTH_SHORT).show()
    }

    /**
     * 第一次打开，新建Fragment,后面再打开，从保存的状态中恢复数据
     * @param savedInstanceState 恢复数据来源
     */
    private fun initFragment(savedInstanceState: Bundle?){
        addFragment(homeFragment,"homeFragment")
        homeFragment.initView()
        addFragment(knowledgeFragment,"knowledgeFragment")
        knowledgeFragment.initView()
        addFragment(guideFragment,"guideFragment")
        guideFragment.initView()
        addFragment(projectsFragment,"projectsFragment")
        projectsFragment.initView()
        showFragment(homeFragment)
    }

    private fun addFragment(fragment: Fragment, tag: String){
        val ft: FragmentTransaction? = supportFragmentManager.beginTransaction()
        ft?.add(R.id.frameLayout,fragment,tag)
        ft?.commitAllowingStateLoss()
    }

    private fun showFragment(fragment: Fragment?){
        var ft: FragmentTransaction? = supportFragmentManager.beginTransaction()
        ft?.hide(homeFragment)
        ft?.hide(knowledgeFragment)
        ft?.hide(guideFragment)
        ft?.hide(projectsFragment)
        ft?.show(fragment)
        ft?.commitAllowingStateLoss()
    }

    override fun onDestroy() {
        super.onDestroy()
        loginPresentor.onDetachView()
    }
}
