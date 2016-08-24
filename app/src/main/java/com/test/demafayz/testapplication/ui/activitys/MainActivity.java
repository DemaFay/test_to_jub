package com.test.demafayz.testapplication.ui.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.test.demafayz.testapplication.R;
import com.test.demafayz.testapplication.utils.sync.ApiHelper;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String result = ApiHelper.getBanks();
    }
}
