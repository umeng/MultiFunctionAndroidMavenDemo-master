package com.umeng.soexample;


import android.app.Application;

import com.quick.qt.analytics.QtTrackAgent;
import com.quick.qt.commonsdk.QtConfigure;
import com.tencent.tauth.Tencent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.PushAgent;
import com.umeng.soexample.sp.SharedPreferencesHelper;

/**
 * Created by wangfei on 2018/1/23.
 */

public class App extends Application {

    SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferencesHelper=new SharedPreferencesHelper(this,"umeng");

        //设置LOG开关，默认为false
        UMConfigure.setLogEnabled(true);
        QtConfigure.setCustomDomain("https://log-api.aplus.emas-poc.com", null);
        //QT 统计打开调试日志
        QtConfigure.setLogEnabled(true);

        //解决推送消息显示乱码的问题
        PushAgent.setup(this, "59892f08310c9307b60023d0", "669c30a9584623e70e8cd01b0381dcb4");
        //友盟预初始化
        UMConfigure.preInit(getApplicationContext(),"59892f08310c9307b60023d0","Umeng");
        QtConfigure.preInit(this, "64632267", "QuickTrack");
        QtTrackAgent.setAutoEventEnabled(true);
        QtTrackAgent.enableFragmentPageCollection(true);
        QtConfigure.setProcessEvent(true);//支持多进程 打点.默认不支持
        /**
         * 打开app首次隐私协议授权，以及sdk初始化，判断逻辑请查看SplashTestActivity
         */
        //判断是否同意隐私协议，uminit为1时为已经同意，直接初始化umsdk
        if(sharedPreferencesHelper.getSharedPreference("uminit","").equals("1")){
            //友盟正式初始化
            UmInitConfig umInitConfig=new UmInitConfig();
            umInitConfig.UMinit(getApplicationContext());
            //QQ官方sdk授权
            Tencent.setIsPermissionGranted(true);
        }


    }
}
