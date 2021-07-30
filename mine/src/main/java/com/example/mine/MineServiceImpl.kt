package com.example.mine

import android.app.Activity
import com.example.common.IMineService
import com.google.auto.service.AutoService
import com.zxf.basic.expand.start

@AutoService(IMineService::class)
class MineServiceImpl : IMineService {

    override fun startMine(activity: Activity) {
        activity.start<MineActivity>()
    }

}