package com.zxf.basic.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.forEach

class PageStateLayout : FrameLayout {
    var emptyView: View? = null
    private set
    var errorView: View? = null
    private set
    var contentView: View? = null
    private set
    var networkErrorView: View? = null
    private set

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)

    fun addEmptyView(view: View) {
        emptyView = view
    }

    fun addErrorView(view: View) {
        errorView = view
    }

    fun addNetworkErrorView(view: View) {
        networkErrorView = view
    }

    fun bind() {
        contentView = getChildAt(0)
        if (emptyView != null) {
            addChild(emptyView)
        } else if (config?.emptyView != null){
            emptyView = config?.emptyView
            addChild(emptyView)
        }
        if (errorView != null) {
            addChild(errorView)
        } else if (config?.errorView != null) {
            errorView = config?.errorView
            addChild(errorView)
        }
        if (networkErrorView != null) {
            addChild(networkErrorView)
        } else if (config?.networkErrorView != null) {
            networkErrorView = config?.networkErrorView
            addChild(networkErrorView)
        }
        emptyView?.visibility = View.GONE
        errorView?.visibility = View.GONE
        networkErrorView?.visibility = View.GONE
    }

    private fun addChild(view: View?, index: Int = 0) {
        if (view != null) {
            (view.parent as? ViewGroup)?.removeView(view)
            addView(view, index)
        }
    }

    fun showEmpty() {
        if (emptyView != null) {
            forEach {
                it.visibility = View.GONE
            }
            emptyView?.visibility = View.VISIBLE
        }
    }

    fun showError() {
        if (errorView != null) {
            forEach {
                it.visibility = View.GONE
            }
            errorView?.visibility = View.VISIBLE
        }
    }

    fun showNetworkError() {
        if (networkErrorView != null) {
            forEach {
                it.visibility = View.GONE
            }
            errorView?.visibility = View.VISIBLE
        }
    }

    fun showContent() {
        if (contentView != null) {
            forEach {
                it.visibility = View.GONE
            }
            contentView?.visibility = View.VISIBLE
        }
    }

    fun hideContent() {
        contentView?.visibility = View.GONE
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var config: PageStateLayoutConfig? = null
    }
}