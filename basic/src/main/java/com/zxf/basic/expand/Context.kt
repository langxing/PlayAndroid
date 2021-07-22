package com.zxf.basic.expand

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.widget.Toast

/**
 * Context扩展
 *
 * @author Jack 2021-05-01 18:38
 */
fun Context.toast(msg: String?) {
    msg?.apply {
        val toast = Toast.makeText(this@toast, this, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}

inline fun <reified A : Activity> Context.start() {
    val intent = Intent(this, A::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
}

inline fun <reified A : Activity> Activity.startResult(code: Int) {
    val intent = Intent(this, A::class.java)
    startActivityForResult(intent, code)
}