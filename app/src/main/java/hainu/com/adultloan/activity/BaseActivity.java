package hainu.com.adultloan.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.umeng.analytics.MobclickAgent;

import hainu.com.adultloan.R;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public void back(View view){
        finish();
    }
}
