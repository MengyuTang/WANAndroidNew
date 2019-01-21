package com.tang.alex.wanandroid.utils

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import kotlin.math.min

class CircleImageView(context: Context?, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {
        private var mWidth: Float? = null
        private var mHeight: Float? = null
        private var mRadius: Float? = null
        private var mPaint: Paint? = null
        private var mMatix: Matrix? = null

    override fun onMeasure(widthMeasureSpec: Int,heightMeasureSpec: Int){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth =  measuredWidth.toFloat()
        mHeight = measuredWidth.toFloat()
        mRadius = min(mWidth!!,mHeight!!)/2
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPaint = Paint()
        mPaint!!.isAntiAlias = true
        mMatix = Matrix()
        mPaint!!.shader = initShader()
        canvas!!.drawCircle(mWidth!!.div(2),mHeight!!.div(2),mRadius!!,mPaint)
    }

    fun initShader():BitmapShader{
        var drawable: BitmapDrawable = drawable as BitmapDrawable
        var bitmap: Bitmap = drawable.bitmap
        var bitmapShader = BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP)
        var scale = Math.max(mWidth!!.div(bitmap.width),mHeight!!.div(bitmap.height))
        mMatix!!.setScale(scale,scale)
        bitmapShader.setLocalMatrix(mMatix)
        return bitmapShader
    }
}