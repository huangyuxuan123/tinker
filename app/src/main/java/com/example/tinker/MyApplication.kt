package com.example.tinker

import android.app.Application
import android.content.Context
import android.util.Log
import com.tencent.tinker.anno.DefaultLifeCycle
import com.tencent.tinker.loader.app.ApplicationLike
import com.tencent.tinker.loader.shareutil.ShareConstants
import com.tinkerpatch.sdk.TinkerPatch
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike

class MyApplication:Application {
    var tinkerApplicationLike:ApplicationLike? = null

    constructor()

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

    }


    override fun onCreate() {
        super.onCreate()
        initTinkerPatch()
    }


    /**
     * 我们需要确保至少对主进程跟patch进程初始化 TinkerPatch
     */
    private fun initTinkerPatch() {
        if(BuildConfig.TINKER_ENABLE){
            // 我们可以从这里获得Tinker加载过程的信息
            tinkerApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike()
            //初始化TinkerPatch SDK
            TinkerPatch.init(tinkerApplicationLike)
                .reflectPatchLibrary()
                .setPatchRestartOnSrceenOff(true)
                .setPatchRollbackOnScreenOff(true)
                    //每隔3个小时(通过setFetchPatchIntervalByHours设置)去访问后台时候有更新,通过handler实现轮训的效果
                .setFetchPatchIntervalByHours(3)

            //获取当前的补丁版本
            Log.e("eee","Current patch version is"+TinkerPatch.with().patchVersion)

            TinkerPatch.with().fetchPatchUpdateAndPollWithInterval()
        }
    }

}