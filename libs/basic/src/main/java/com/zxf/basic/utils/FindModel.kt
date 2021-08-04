package com.zxf.basic.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FindModel<M : ViewModel>(private val clazz: Class<M>) : ReadOnlyProperty<ViewModelStoreOwner, M> {
    override fun getValue(thisRef: ViewModelStoreOwner, property: KProperty<*>): M {
        return ViewModelProvider(thisRef).get(clazz)
    }
}