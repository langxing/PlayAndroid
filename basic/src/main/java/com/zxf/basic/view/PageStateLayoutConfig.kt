package com.zxf.basic.view

import android.view.View

class PageStateLayoutConfig private constructor() {
    var emptyView: View? = null
    private set
    var errorView: View? = null
    private set
    var networkErrorView: View? = null
    private set

    class Builder {
        private val pageStateLayoutConfig = PageStateLayoutConfig()

        var emptyView: View?
        get() = pageStateLayoutConfig.emptyView
        set(value) {
            pageStateLayoutConfig.emptyView = value
        }

        var errorView: View?
        get() = pageStateLayoutConfig.errorView
        set(value) {
            pageStateLayoutConfig.errorView = value
        }

        var networkErrorView: View?
        get() = pageStateLayoutConfig.networkErrorView
        set(value) {
            pageStateLayoutConfig.networkErrorView = value
        }

        fun build(): PageStateLayoutConfig {
            return pageStateLayoutConfig
        }
    }
}