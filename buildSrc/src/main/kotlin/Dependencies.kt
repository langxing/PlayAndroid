object Versions {
    const val kotlin = "1.4.32"
    const val retrofit = "2.3.0"
    const val rxandroid = "2.0.2"
    const val material = "1.2.1"
    const val androidxCore = "1.3.0"
    const val androidxAppcompat = "1.2.0"
    const val constraintlayout = "2.0.4"
    const val activityKtx = "1.3.0"
    const val permission = "11.5"
    const val titlebar = "8.6"
    const val mmkv = "1.2.9"
    const val rxjava = "2.2.2"
    const val logInterceptor = "3.8.0"
    const val converterGson = "2.3.0"
    const val viewmodel = "2.3.1"
    const val immersionbar = "3.0.0"
    const val leakcanary = "2.7"
    const val svga = "2.5.15"
    const val glide = "4.11.0"
    const val util = "1.30.6"
    const val billing = "4.0.0"
    const val xpopup = "2.4.3"
    const val fragment = "1.4.0-beta03"
    const val firebase = "17.4.1"
    const val crashlytics = "2.10.1"
    const val tingyun = "2.14.10"
    const val navigation = "2.3.0"
    const val livedata = "2.2.0"
    const val annotation = "1.2.0"
    const val autoService = "1.0"
    const val sonic = "3.1.0"
    const val dynamic = "1.0.0"
}

object AndroidX {
    const val core = "androidx.core:core-ktx:${Versions.androidxCore}"
    const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewmodel}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.activityKtx}"
    const val fragment = "androidx.fragment:fragment:${Versions.fragment}"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.androidxAppcompat}"
    const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.livedata}"
    const val annotation = "androidx.annotation:annotation:${Versions.annotation}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
}

object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val retrofitRxjavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid}"
    const val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    const val logInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.logInterceptor}"
    // 腾讯开源的缓存框架
    const val mmkv = "com.tencent:mmkv-static:${Versions.mmkv}"
    // 动态权限请求框架
    const val permission = "com.github.getActivity:XXPermissions:${Versions.permission}"
    // 标题栏框架
    const val titlebar = "com.github.getActivity:TitleBar:${Versions.titlebar}"
    // 沉浸式状态栏
    const val immersionbar = "com.gyf.immersionbar:immersionbar:${Versions.immersionbar}"
    // 内存泄漏检测
    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}"
    // svga动画框架
    const val svgaplayer = "com.github.yyued:SVGAPlayer-Android:${Versions.svga}"
    // Glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    // 开源的帮助类框架 https://github.com/Blankj/AndroidUtilCode/blob/master/lib/utilcode/README-CN.md
    const val util = "com.blankj:utilcodex:${Versions.util}"
    // google支付
    const val googlePay = "com.android.billingclient:billing-ktx:${Versions.billing}"
    // 弹窗框架
    const val xpopup = "com.github.li-xiaojun:XPopup:${Versions.xpopup}"
    // firebase 监测崩溃
    const val firebase = "com.google.firebase:firebase-core:${Versions.firebase}"
    const val crashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.crashlytics}"
    // 听云
    const val tingyun = "com.networkbench.newlens.agent.android:nbs.newlens.agent:${Versions.tingyun}"
    const val autoService = "com.google.auto.service:auto-service:${Versions.autoService}"
    // 腾讯网页加速
    const val sonic = "com.tencent.sonic:sdk:${Versions.sonic}"

    const val dynamic = "androidx.dynamicanimation:dynamicanimation:${Versions.dynamic}"
}
