package com.umeng.soexample.push;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.umeng.message.UmengMessageService;
import org.android.agoo.common.AgooConstants;

public class UmengNotificationService extends UmengMessageService {
    @Override
    public void onMessage(Context context, Intent intent) {
        Log.i("UPush", "onMessage()");
        try {
            String message = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
            Intent i = new Intent();
            i.setClass(context, MyNotificationService.class);
            i.putExtra("UmengMsg", message);
            context.startService(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
