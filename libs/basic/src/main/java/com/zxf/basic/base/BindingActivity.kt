package com.zxf.basic.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType

abstract class BindingActivity<V : ViewBinding> : BaseActivity() {

    lateinit var mBinding: V
    private set

    override fun layout(): Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val type = javaClass.genericSuperclass as ParameterizedType
        val cls = type.actualTypeArguments[0] as Class<V>
        try {
            val inflate: Method = cls.getDeclaredMethod("inflate", LayoutInflater::class.java)
            mBinding = inflate.invoke(null, layoutInflater) as V
            setContentView(mBinding.root)
            initView()
            initData()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}