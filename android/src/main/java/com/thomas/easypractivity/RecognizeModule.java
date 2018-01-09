package com.thomas.easypractivity;

import android.app.Activity;
import android.content.Intent;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RecognizeModule extends ReactContextBaseJavaModule implements ActivityEventListener {

    public RecognizeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        reactContext.addActivityEventListener(this);
    }

    @Override
    public String getName() {
        return "RecognizeModule";
    }

    @ReactMethod
    public void recognize(Callback callback){
        NativeCallback.getInstance().setCallBack(callback);
        Activity currentActivity = getCurrentActivity();
        Intent intent = new Intent(currentActivity, EasyPRActivity.class);
        currentActivity.startActivity(intent);


    }


    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onNewIntent(Intent intent) {

    }
}
