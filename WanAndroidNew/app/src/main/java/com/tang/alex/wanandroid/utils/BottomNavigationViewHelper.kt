package com.tang.alex.wanandroid.utils

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import com.tang.alex.wanandroid.R
import java.lang.reflect.Field

class BottomNavigationViewHelper {
    companion object {
        /**
         * BottomNavigationView取消点击动画效果
         */
        @SuppressLint("RestrictedApi")
        fun disableShiftMode(view: BottomNavigationView){
            val menuItem: BottomNavigationMenuView = view.getChildAt(0) as BottomNavigationMenuView
            val shiftingMode: Field = menuItem.javaClass.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuItem,false)
            shiftingMode.isAccessible = false
            for (i in 0 until menuItem.childCount){
                var itemView: BottomNavigationItemView = menuItem.getChildAt(i) as BottomNavigationItemView
                itemView.setShiftingMode(false)
                itemView.setChecked(itemView.itemData.isChecked)
            }
        }
    }
}