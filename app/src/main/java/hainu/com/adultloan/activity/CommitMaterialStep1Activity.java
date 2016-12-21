package hainu.com.adultloan.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.listener.SaveListener;
import hainu.com.adultloan.R;
import hainu.com.adultloan.application.MyApplication;
import hainu.com.adultloan.bean.ApplyInfo;
import hainu.com.adultloan.util.MyUtils;

public class CommitMaterialStep1Activity extends BaseActivity {

    private static final String TAG = "CommitMaterialStep1";
    private EditText et_commit_count;
    private EditText et_commit_mouth;
    private EditText et_commit_name;
    private EditText et_commit_phone;
    private ProgressDialog dialog;
    private TextView tv_commit_contactinf;
    private EditText et_commit_area;
    private EditText et_commit_qq;
    private EditText et_commit_weixin;
    private EditText et_commit_certicate;
    private String applyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_material_step1);

        initView();
    }

    private void initView() {
        et_commit_name = (EditText) findViewById(R.id.et_commit_name);
        et_commit_count = (EditText) findViewById(R.id.et_commit_count);
        et_commit_mouth = (EditText) findViewById(R.id.et_commit_mouth);
        et_commit_phone = (EditText) findViewById(R.id.et_commit_phone);
        et_commit_area = (EditText) findViewById(R.id.et_commit_area);
        et_commit_qq = (EditText) findViewById(R.id.et_commit_qq);
        et_commit_weixin = (EditText) findViewById(R.id.et_commit_weixin);
        et_commit_certicate = (EditText) findViewById(R.id.et_commit_certicate);

        tv_commit_contactinf = (TextView) findViewById(R.id.tv_commit_contactinf);

        String qqContactInfo = MyApplication.sharedPreferences.getString("qqContactInfo", "");
        if (!qqContactInfo.equals("")){
            tv_commit_contactinf.setText(qqContactInfo);
        }
    }


    public void netStep(View view){
        String name = et_commit_name.getText().toString();
        String count = et_commit_count.getText().toString();
        String mouth = et_commit_mouth.getText().toString();
        String phone = et_commit_phone.getText().toString();

        String area = et_commit_area.getText().toString();
        String qq = et_commit_qq.getText().toString();
        String weixin = et_commit_weixin.getText().toString();
        String certicate = et_commit_certicate.getText().toString();

        Log.i(TAG,","+name+","+count+","+mouth);
        if (count.equals("")){
            Toast.makeText(CommitMaterialStep1Activity.this, "请输入金额", Toast.LENGTH_SHORT).show();
        }
        else if (mouth.equals("")){
            Toast.makeText(CommitMaterialStep1Activity.this, "请输入贷款时间", Toast.LENGTH_SHORT).show();
        }
        else if (name.equals("")){
            Toast.makeText(CommitMaterialStep1Activity.this, "请输入姓名", Toast.LENGTH_SHORT).show();
        }
        else if (phone.equals("")){
            Toast.makeText(CommitMaterialStep1Activity.this, "请输入电话", Toast.LENGTH_SHORT).show();
        }
        else if (et_commit_area.equals("")){
            Toast.makeText(CommitMaterialStep1Activity.this, "请输入工作省市", Toast.LENGTH_SHORT).show();
        }
        else {
            applyId = "appid"+ System.currentTimeMillis();
            ApplyInfo applyInfo = new ApplyInfo(applyId,name,qq,certicate,area,weixin,count,mouth,phone);
            applyInfo.setPhone(phone);
            uploadData(applyInfo);
        }
    }

    private void uploadData(ApplyInfo applyInfo) {
        MyUtils.showDialog("正在提交",this);
        applyInfo.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                MyUtils.hideDialog();
                Toast.makeText(CommitMaterialStep1Activity.this, "申请成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CommitMaterialStep1Activity.this,ApplySuccessActivity.class));
                MyApplication.sharedPreferences.edit().putString("applyId",applyId).commit();
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                MyUtils.hideDialog();
                Toast.makeText(CommitMaterialStep1Activity.this, "申请失败"+s, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void back(View view){
        finish();
    }
}
