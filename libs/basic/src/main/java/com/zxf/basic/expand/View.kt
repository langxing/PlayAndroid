package com.zxf.basic.expand

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zxf.basic.view.PageStateLayoutConfig
import com.zxf.basic.view.SelectorBuilder
import com.zxf.basic.view.ShapeBuilder
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * View的扩展属性
 *
 * @author Jack 2021-05-01 16:51
 */
var View.drawable: Drawable
    get() = background
    set(value) {
        background = value
    }

fun shapeDrawable(bilider: ShapeBuilder.() -> Unit): Drawable {
    return ShapeBuilder().also(bilider).build()
}

fun selectorDrawable(builder: SelectorBuilder.() -> Unit): Drawable {
    return SelectorBuilder().also(builder).build()
}

/**
 * 防止重复点击
 */
fun View.signClick(clickListener: (v: View) -> Unit) {
    isEnabled = false
    postDelayed({
        setOnClickListener {
            clickListener.invoke(it)
        }
        isEnabled = true
    }, 300)
}

fun pageStateConfig(builder: PageStateLayoutConfig.Builder.() -> Unit): PageStateLayoutConfig {
    return PageStateLayoutConfig.Builder().also(builder).build()
}

/**
 * 委托的方式获取view
 *
 * @author Jack 2021-05-02 22:21
 */
class FindView<V : View>(private val id: Int) : ReadOnlyProperty<Activity, V> {

    /**
     * @param thisRef 委托所属对象
     */
    override fun getValue(thisRef: Activity, property: KProperty<*>): V {
        return thisRef.findViewById(id)
    }

}

/**
 * 委托的方式获取view
 *
 * @author Jack 2021-05-02 22:21
 */
class GetView<V : View>(private val id: Int) : ReadOnlyProperty<RecyclerView.ViewHolder, V> {

    /**
     * @param thisRef 委托所属对象
     */
    override fun getValue(thisRef: RecyclerView.ViewHolder, property: KProperty<*>): V {
        return thisRef.itemView.findViewById(id)
    }

}
