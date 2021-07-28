package com.example.common

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dalvik.system.BaseDexClassLoader
import dalvik.system.DexFile
import java.lang.reflect.Field

/**
 * 组件化：
 * https://juejin.cn/post/6881116198889586701#heading-19
 *
 * AutoService 组件化：https://juejin.cn/post/6862730783602409486
 * application 组件化：https://juejin.cn/post/6844903687488274445#heading-4
 * 微信组件化：https://juejin.cn/post/6945413567285821453
 * 接口组件化：https://juejin.cn/post/6844903649102004231#heading-13
 */
class ClassHelper {

    fun getDexFiles(context: Context): Sequence<DexFile> {
        val classLoader = context.classLoader as BaseDexClassLoader
        val pathListField = field("dalvik.system.BaseDexClassLoader", "pathList")
        // Type is DexPathList
        val pathList = pathListField.get(classLoader)
        val dexElementsField = field("dalvik.system.DexPathList", "dexElements")
        @Suppress("UNCHECKED_CAST")
        // Type is Array<DexPathList.Element>
        val dexElements = dexElementsField.get(pathList) as Array<Any>
        val dexFileField = field("dalvik.system.DexPathList\$Element", "dexFile")
        return dexElements.map {
            dexFileField.get(it) as DexFile
        }.asSequence()
    }

    private fun field(className: String, fieldName: String): Field {
        val clazz = Class.forName(className)
        val field = clazz.getDeclaredField(fieldName)
        field.isAccessible = true
        return field
    }

}