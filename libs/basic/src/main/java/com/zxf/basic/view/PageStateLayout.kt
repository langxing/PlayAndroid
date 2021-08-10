package com.zxf.basic.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.forEach

class PageStateLayout : FrameLayout {
    private var emptyView: View? = null
    private var errorView: View? = null
    private var contentView: View? = null
    private var networkErrorView: View? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        contentView = getChildAt(0)
    }

    fun addEmptyView(view: View) {
        emptyView = view
    }

    fun addErrorView(view: View) {
        errorView = view
    }

    fun addNetworkErrorView(view: View) {
        networkErrorView = view
    }

    private fun addChild(view: View?, index: Int = 0) {
        if (view != null) {
            (view.parent as? ViewGroup)?.removeView(view)
            addView(view, index)
        }
    }

    fun showEmpty() {
        if (emptyView != null) {
            if (indexOfChild(emptyView) != -1) {
                forEach {
                    it.visibility = View.GONE
                }
                emptyView?.visibility = View.VISIBLE
            } else {
                addChild(emptyView)
            }
        } else if (config?.emptyView != null) {
            addEmptyView(config?.emptyView!!)
            addChild(config?.emptyView!!)
        }
    }

    fun showError() {
        if (errorView != null) {
            if (indexOfChild(errorView) != -1) {
                forEach {
                    it.visibility = View.GONE
                }
                errorView?.visibility = View.VISIBLE
            } else {
                addChild(errorView)
            }
        } else if (config?.errorView != null) {
            addErrorView(config?.errorView!!)
            addChild(config?.errorView!!)
        }
    }

    fun showNetworkError() {
        if (networkErrorView != null) {
            if (indexOfChild(networkErrorView) != -1) {
                forEach {
                    it.visibility = View.GONE
                }
                networkErrorView?.visibility = View.VISIBLE
            } else {
               addChild(networkErrorView)
            }
        } else if (config?.networkErrorView != null) {
            addNetworkErrorView(config?.networkErrorView!!)
            addChild(config?.networkErrorView!!)
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

    companion object {
        @SuppressLint("StaticFieldLeak")
        var config: PageStateLayoutConfig? = null
    }
}