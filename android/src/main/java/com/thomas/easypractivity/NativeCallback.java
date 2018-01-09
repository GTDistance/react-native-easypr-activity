package com.thomas.easypractivity;

import com.facebook.react.bridge.Callback;

/**
 * Created by Thomas.Wang on 2018/1/3.
 */

public class NativeCallback {
    private Callback callback;
    private static NativeCallback nativeCallback = new NativeCallback();
    private NativeCallback(){}
    public static NativeCallback getInstance(){
        return nativeCallback;
    }

    public void setCallBack(Callback callback){
        this.callback = callback;
    }

    public Callback getCallback(){
        return callback;
    }
}
