package com.zxf.basic.utils;

import androidx.annotation.IntDef;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@IntDef({PageState.STATE_NOMAL, PageState.STATE_LOADING, PageState.STATE_EMPTY,
         PageState.STATE_ERROR, PageState.STATE_NETWORK_ERROR})
@Target(ElementType.PARAMETER)
public @interface PageState {
    /**
     * 页面正常状态
     */
    int STATE_NOMAL = 1;

    /**
     * 页面加载状态
     */
    int STATE_LOADING = 2;

    /**
     * 页面数据为空时的状态
     */
    int STATE_EMPTY = 3;

    /**
     * 页面加载错误的状态
     */
    int STATE_ERROR = 4;

    /**
     * 网络异常的状态
     */
    int STATE_NETWORK_ERROR = 5;
}
