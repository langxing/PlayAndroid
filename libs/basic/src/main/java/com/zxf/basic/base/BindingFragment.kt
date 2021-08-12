package com.zxf.basic.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.zxf.basic.expand.getVmClazz
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType

abstract class BindingFragment<V : ViewBinding, M : ViewModel> : BaseFragment() {

    lateinit var mBinding: V
    private set

    lateinit var mViewModel: M
    private set

    override fun layout(): Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewModel = ViewModelProvider(this).get(getVmClazz(this))
        val type = javaClass.genericSuperclass as ParameterizedType
        val cls = type.actualTypeArguments[0] as Class<V>
        try {
            val inflate: Method = cls.getDeclaredMethod("inflate",
                LayoutInflater::class.java,
                ViewGroup::class.java,
                Boolean::class.java)
            mBinding = inflate.invoke(null, inflater, container, false) as V
            return mBinding.root
        } catch (e: Exception) {
            throw NullPointerException("必须先初始化binding")
        }
    }

}