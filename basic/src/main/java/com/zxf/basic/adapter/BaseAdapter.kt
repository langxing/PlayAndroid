package com.zxf.basic.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Jack 2021-05-01 22:46
 */
abstract class BaseAdapter : RecyclerView.Adapter<BaseAdapter.Holder>() {
    var onClickListener: ((v: View, position: Int) ->Unit)?
        get() = onClickListener
        set(value) {
            onClickListener = value
        }

    abstract fun layout(): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent.context).inflate(layout(), parent, false)
        return Holder(itemView)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val onClick = View.OnClickListener {
            onClickListener?.invoke(it, layoutPosition)
        }
        fun <V : View> getView(id: Int): V {
            return itemView.findViewById(id)
        }
        fun setText(id: Int, text: String): Holder {
            getView<TextView>(id).text = text
            return this
        }
        fun setOnClick(id: Int): Holder {
            itemView.findViewById<View>(id).setOnClickListener(onClick)
            return this
        }
    }
}