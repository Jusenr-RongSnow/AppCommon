package com.myself.appcommon.activity;

import android.os.Bundle;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.myself.appcommon.BuildConfig;
import com.myself.appcommon.R;

import java.util.Arrays;
import java.util.List;

public class ReactNative2Activity extends ReactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react_native2);
    }

    @Override
    protected String getMainComponentName() {
        return "TestHello";
    }

    @Override
    protected boolean getUseDeveloperSupport() {
        return BuildConfig.DEBUG;
    }

    @Override
    protected List<ReactPackage> getPackages() {
        return Arrays.<ReactPackage>asList(
                new MainReactPackage()
        );
    }
}
