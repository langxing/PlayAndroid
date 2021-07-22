package com.zxf.basic.annotation;

import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@IntDef({LoadingState.SHOW_LOADING, LoadingState.HIDE_LOADING})
@Target(ElementType.PARAMETER)
public @interface LoadingState {
    int SHOW_LOADING = 1;
    int HIDE_LOADING = 2;
}
