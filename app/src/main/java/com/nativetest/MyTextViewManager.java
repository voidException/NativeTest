package com.nativetest;

/**
 * http://blog.csdn.net/wxq888/article/details/52702983
 * 待继续完善
 */

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class MyTextViewManager extends SimpleViewManager<TextView> {
    @Override
    public String getName() {
        return "MyTextView";
    }

    @Override
    protected TextView createViewInstance( final ThemedReactContext reactContext) {
        //TextView textView = new TextView(reactContext)
         final TextView textView = new TextView(reactContext);
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    WritableMap nativeEvent= Arguments.createMap();
                    nativeEvent.putString("message", "MyMessage");
                    reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                            textView.getId(), "topChange",nativeEvent
                    );
                    return true;
                }else{
                    return false;
                }
            }
        });;
        return textView;
    }


    @ReactProp(name = "text")
    public void setText(TextView view, String text) {

        view.setText(text);
    }

    @ReactProp(name = "textSize")
    public void setTextSize(TextView view, float fontSize) {
        view.setTextSize(fontSize);
    }

    @ReactProp(name = "textColor", defaultInt = Color.BLACK)
    public void setTextColor(TextView view, int textColor) {
        view.setTextColor(textColor);
    }
    @ReactProp(name = "isAlpha", defaultBoolean = false)
    public void setTextAlpha(TextView view, boolean isAlpha) {
        if (isAlpha) {
            view.setAlpha(0.5f);
        }
    }
}