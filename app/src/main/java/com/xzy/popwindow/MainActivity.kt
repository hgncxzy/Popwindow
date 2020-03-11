package com.xzy.popwindow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Popwindow.Callback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_test.setOnClickListener {
            val popwindow = Popwindow(this, this)
            popwindow.showAtBottom(btn_test)
        }
    }

    override fun callback(view: View) {
        when (view.id) {
            R.id.ll_chinese -> Toast.makeText(this, "中文", Toast.LENGTH_SHORT).show()
            R.id.ll_english -> Toast.makeText(this, "english", Toast.LENGTH_SHORT).show()
        }
    }
}
