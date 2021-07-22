package com.zxf.basic.http

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Build.SERIAL
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.TextUtils
import com.zxf.basic.AppManager
import com.zxf.basic.utils.LogUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.UnsupportedEncodingException
import java.util.*

/**
 * 自定义拦截器添加header等
 *
 * @author Jack 2021-06-19 11:51
 */
class CustomInterceptor : Interceptor {

    @SuppressLint("MissingPermission")
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val guid = getDeviceId(AppManager.get().getApp()) ?: ""
        LogUtils.d(log = "log=$guid")
        builder.addHeader("guid", guid)
        return chain.proceed(builder.build())
    }

    private fun getDeviceId(context: Context): String? {
        val targetSdkVersion = context.applicationInfo.targetSdkVersion
        return if (targetSdkVersion > Build.VERSION_CODES.P && Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            getUniqueID(context)
        } else {
            getTelId(context)
        }
    }

    private fun getTelId(context: Context): String? {
        val manager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return manager.deviceId
    }

    @SuppressLint("HardwareIds")
    private fun getUniqueID(context: Context): String? {
        var id: String? = null
        val androidId: String =
            Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        if (!TextUtils.isEmpty(androidId) && "9774d56d682e549c" != androidId) {
            try {
                val uuid: UUID = UUID.nameUUIDFromBytes(androidId.toByteArray(charset("utf8")))
                id = uuid.toString()
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
        }
        if (TextUtils.isEmpty(id)) {
            id = getUUID()
        }
        return if (TextUtils.isEmpty(id)) UUID.randomUUID().toString() else id
    }

    private fun getUUID(): String {
        var serial: String?
        val devIDShort =
            "35" + Build.BOARD.length % 10 + Build.BRAND.length % 10 + (if (null != Build.CPU_ABI) Build.CPU_ABI.length else 0) % 10 + Build.DEVICE.length % 10 + Build.DISPLAY.length % 10 + Build.HOST.length % 10 + Build.ID.length % 10 + Build.MANUFACTURER.length % 10 + Build.MODEL.length % 10 + Build.PRODUCT.length % 10 + Build.TAGS.length % 10 + Build.TYPE.length % 10 + Build.USER.length % 10 //13 位
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            try {
                serial = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Build.getSerial()
                } else {
                    SERIAL
                }
                //API>=9 使用serial号
                return UUID(devIDShort.hashCode().toLong(), serial.hashCode().toLong()).toString()
            } catch (exception: Exception) {
                serial = "serial" + UUID.randomUUID().toString() // 随便一个初始化
            }
        } else {
            serial = Build.UNKNOWN + UUID.randomUUID().toString() // 随便一个初始化
        }

        //使用硬件信息拼凑出来的15位号码
        return UUID(devIDShort.hashCode().toLong(), serial.hashCode().toLong()).toString()
    }
}