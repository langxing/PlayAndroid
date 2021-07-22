package com.zxf.basic.http

import com.zxf.basic.base.BaseViewModel
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class BindService<T>(private val clazz: Class<T>) : ReadOnlyProperty<BaseViewModel, T> {
    override fun getValue(thisRef: BaseViewModel, property: KProperty<*>): T {
        return RetrofitHelper.get().create(clazz)
    }
}