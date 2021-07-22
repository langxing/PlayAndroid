package com.zxf.basic.utils

import android.app.Application
import android.os.Parcelable
import com.tencent.mmkv.MMKV

class MMKVUtils private constructor() {

    companion object {
        private val instance: MMKVUtils by lazy {
            MMKVUtils()
        }

        fun get() = instance
    }

    fun init(application: Application) {
        MMKV.initialize(application)
    }

    fun put(key: String, value: Any) {
        val mmkv = MMKV.defaultMMKV()
        when (value) {
            is Int -> mmkv?.encode(key, value)
            is Boolean -> mmkv?.encode(key, value)
            is Long -> mmkv?.encode(key, value)
            is String -> mmkv?.encode(key, value)
            is Double -> mmkv?.encode(key, value)
            is Float -> mmkv?.encode(key, value)
            is ByteArray -> mmkv?.encode(key, value)
            is Parcelable -> mmkv?.encode(key, value)
            is Set<*> -> mmkv?.encode(key, value as Set<String>)
        }
    }

    inline fun <reified T> get(key: String): T? {
        val mmkv = MMKV.defaultMMKV()
        val value = when(T::class.java) {
            Int::class.java -> mmkv?.decodeInt(key, -1)
            Boolean::class.java -> mmkv?.decodeBool(key, false)
            Long::class.java -> mmkv?.decodeLong(key, 0)
            String::class.java -> mmkv?.getString(key, "")
            Double::class.java -> mmkv?.decodeDouble(key)
            Float::class.java -> mmkv?.decodeFloat(key)
            ByteArray::class.java -> mmkv?.decodeBytes(key)
            Parcelable::class.java -> mmkv?.decodeParcelable(key, Parcelable::class.java)
            Set::class.java -> mmkv?.decodeStringSet(key)
            else -> null
        }
        return value as T?
    }
}