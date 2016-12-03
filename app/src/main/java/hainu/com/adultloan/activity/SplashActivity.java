package hainu.com.adultloan.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.umeng.analytics.MobclickAgent;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import hainu.com.adultloan.R;
import hainu.com.adultloan.application.MyApplication;
import hainu.com.adultloan.bean.Apk;

public class SplashActivity extends BaseActivity {

    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();

        initData(this);
    }


    private void initView() {


        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean isInstall = MyApplication.sharedPreferences.getBoolean("isInstall", false);
                if (isInstall){
                    startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                }
                else {
                    startActivity(new Intent(SplashActivity.this,InstallGuideActivity.class));
                    MyApplication.sharedPreferences.edit().putBoolean("isInstall",true).apply();
                }
                finish();
            }
        }.start();


    }

    private void initData(final Context context) {
        BmobQuery<Apk> apkBmobQuery = new BmobQuery<>();
        apkBmobQuery.findObjects(this, new FindListener<Apk>() {
            @Override
            public void onSuccess(List<Apk> list) {
                if (list!=null && list.size()>0){
                    Apk apk = list.get(0);
                    int versionCode = Integer.parseInt(apk.getVersionCode());
                    String apkUrl = apk.getApkUrl();
                    boolean forceWhenUpdate = apk.isForceWhenUpdate();
                    boolean updateWhenOpen = apk.isUpdateWhenOpen();
                    String qqContactInfo = apk.getQqContactInfo();
                    String contactPhone = apk.getContactPhone();

                    Log.i(TAG,"versionCode:"+versionCode+",apkUrl:"+apkUrl+",forceWhenUpdate:"+forceWhenUpdate+",updateWhenOpen:"+updateWhenOpen);
                    MyApplication.sharedPreferences.edit().putInt("versionCode",versionCode).apply();
                    MyApplication.sharedPreferences.edit().putString("apkUrl",apkUrl).apply();
                    MyApplication.sharedPreferences.edit().putString("qqContactInfo",qqContactInfo).apply();
                    MyApplication.sharedPreferences.edit().putString("contactPhone",contactPhone).apply();
                    MyApplication.sharedPreferences.edit().putBoolean("forceWhenUpdate",forceWhenUpdate).apply();
                    MyApplication.sharedPreferences.edit().putBoolean("updateWhenOpen",updateWhenOpen).apply();
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });


        //友盟统计
        String phone = MyApplication.sharedPreferences.getString("phone", "");
        if (!phone.equals("")){
            MobclickAgent.onProfileSignIn(phone);
        }

    }
}
