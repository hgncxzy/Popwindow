package com.xzy.popwindow

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams
import android.view.WindowManager
import android.widget.PopupWindow
import kotlin.math.abs

/**
 * 弹窗视图
 */
class Popwindow(private val context: Context, callback: Callback) : PopupWindow(context),
    View.OnClickListener {
    private var layoutChinese: View? = null
    private var layoutEnglish: View? = null
    private var _callback: Callback? = callback

    interface Callback {
        fun callback(view: View)
    }

    init {
        initialize()
    }

    @SuppressLint("InflateParams")
    private fun initialize() {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.popwindow, null)
        layoutChinese = view.findViewById(R.id.ll_chinese)
        layoutEnglish = view.findViewById(R.id.ll_english)
        layoutChinese!!.setOnClickListener(this)
        layoutEnglish!!.setOnClickListener(this)
        contentView = view
        initWindow()
    }

    private fun initWindow() {
//        val d = context.resources.displayMetrics
//        this.width = (d.widthPixels * 0.25).toInt()
        this.width = LayoutParams.WRAP_CONTENT
        this.height = LayoutParams.WRAP_CONTENT
        this.isFocusable = true
        this.isOutsideTouchable = true
        this.update()
        // 实例化一个ColorDrawable颜色为半透明
        val dw = ColorDrawable(0x00000000)
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw)
        backgroundAlpha(context as Activity, 0.8f) // 0.0-1.0
        this.setOnDismissListener { backgroundAlpha(context, 1f) }
    }

    // 设置添加屏幕的背景透明度
    fun backgroundAlpha(context: Activity, bgAlpha: Float) {
        val lp = context.window.attributes
        lp.alpha = bgAlpha
        context.window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        context.window.attributes = lp
    }

    // todo 根据需要设置显示的位置参数
    fun showAtBottom(view: View) {
        // 弹窗位置设置
        showAsDropDown(view, abs((view.width - width) / 2), abs((view.height - height) / 2))
        // 下面这种方式有偏差
        // showAtLocation(view, Gravity.CENTER or Gravity.END, 448, -40)
    }

    override fun onClick(view: View) {
        this.dismiss()
        _callback?.callback(view)
    }
}
