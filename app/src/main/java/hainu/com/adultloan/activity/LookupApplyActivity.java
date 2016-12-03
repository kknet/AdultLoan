package hainu.com.adultloan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import hainu.com.adultloan.R;
import hainu.com.adultloan.application.MyApplication;
import hainu.com.adultloan.bean.ApplyInfo;

public class LookupApplyActivity extends BaseActivity {

    private TextView tv_histery_name;
    private TextView tv_histery_certif;
    private TextView tv_histery_qq;
    private TextView tv_histery_time;
    private TextView tv_histery_money;
    private TextView tv_histery_weixin;
    private TextView tv_histery_phone;
    private TextView tv_histery_area;
    private String applyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookup_apply);

        initView();
    }

    private void initView() {
        TextView tv_lookup_histery = (TextView) findViewById(R.id.tv_lookup_histery);
        ScrollView sv_lookup_svro = (ScrollView) findViewById(R.id.sv_lookup_svro);

        applyId = MyApplication.sharedPreferences.getString("applyId", "");
        if (!applyId.equals("")){
            tv_lookup_histery.setVisibility(View.GONE);
            sv_lookup_svro.setVisibility(View.VISIBLE);
            tv_histery_name = (TextView) findViewById(R.id.tv_histery_name);
            tv_histery_qq = (TextView) findViewById(R.id.tv_histery_qq);
            tv_histery_certif = (TextView) findViewById(R.id.tv_histery_certif);
            tv_histery_money = (TextView) findViewById(R.id.tv_histery_money);
            tv_histery_time = (TextView) findViewById(R.id.tv_histery_time);
            tv_histery_weixin = (TextView) findViewById(R.id.tv_histery_weixin);
            tv_histery_phone = (TextView) findViewById(R.id.tv_histery_phone);
            tv_histery_area = (TextView) findViewById(R.id.tv_histery_area);

            loadData();
        }
    }
    private void loadData() {
        BmobQuery<ApplyInfo> applyInfoBmobQuery = new BmobQuery<>();

        applyInfoBmobQuery.addWhereEqualTo("applyId",applyId);
        applyInfoBmobQuery.findObjects(this, new FindListener<ApplyInfo>() {
            @Override
            public void onSuccess(List<ApplyInfo> list) {
                if (list.size()>0){
                    ApplyInfo applyInfo = list.get(0);
                    tv_histery_name.setText(applyInfo.getName());
                    tv_histery_qq.setText(applyInfo.getQq());
                    tv_histery_certif.setText(applyInfo.getCertificate());
                    tv_histery_weixin.setText(applyInfo.getWeixin());
                    tv_histery_money.setText(applyInfo.getMoney());
                    tv_histery_time.setText(applyInfo.getMouths());
                    tv_histery_area.setText(applyInfo.getProvince());
                    tv_histery_phone.setText(applyInfo.getPhone());
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }

    public void back(View view){
        finish();
    }
}

	