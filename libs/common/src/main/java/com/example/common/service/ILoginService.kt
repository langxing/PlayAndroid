package com.example.common.service

import androidx.activity.result.ActivityResult
import androidx.appcompat.app.AppCompatActivity

/**
 * 组件化：
 * https://juejin.cn/post/6881116198889586701#heading-19
 *
 * AutoService 组件化：https://juejin.cn/post/6862730783602409486
 * application 组件化：https://juejin.cn/post/6844903687488274445#heading-4
 * 微信组件化：https://juejin.cn/post/6945413567285821453
 * 接口组件化：https://juejin.cn/post/6844903649102004231#heading-13
 */
interface ILoginService {

    fun startLogin(activity: AppCompatActivity, callback: (result: ActivityResult) -> Unit)
}