package hainu.com.adultloan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.listener.SaveListener;
import hainu.com.adultloan.R;
import hainu.com.adultloan.application.MyApplication;
import hainu.com.adultloan.bean.StudentApplyInfo;
import hainu.com.adultloan.util.MyUtils;

public class CommitMaterialStudentActivity extends BaseActivity {
    private EditText et_commit_count;
    private EditText et_commit_mouth;
    private EditText et_commit_name;
    private EditText et_commit_phone;
    private EditText et_commit_qq;
    private EditText et_commit_certif;
    private EditText et_commit_acount;
    private EditText et_commit_password;

    private TextView tv_commit_contactinf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_material_student);
        initView();
    }

    private void initView() {
        et_commit_name = (EditText) findViewById(R.id.et_commit_name);
        et_commit_count = (EditText) findViewById(R.id.et_commit_count);
        et_commit_mouth = (EditText) findViewById(R.id.et_commit_mouth);
        et_commit_phone = (EditText) findViewById(R.id.et_commit_phone);
        tv_commit_contactinf = (TextView) findViewById(R.id.tv_commit_contactinf);
        et_commit_qq = (EditText) findViewById(R.id.et_commit_qq);
        et_commit_certif = (EditText) findViewById(R.id.et_commit_certif);
        et_commit_acount = (EditText) findViewById(R.id.et_commit_acount);
        et_commit_password = (EditText) findViewById(R.id.et_commit_password);

        String qqContactInfo = MyApplication.sharedPreferences.getString("qqContactInfo", "");
        if (!qqContactInfo.equals("")){
            tv_commit_contactinf.setText(""+qqContactInfo);
        }
    }

    public void commitInfo(View view) {
        String name = et_commit_name.getText().toString();
        String count = et_commit_count.getText().toString();
        String mouth = et_commit_mouth.getText().toString();
        String phone = et_commit_phone.getText().toString();
        String qq = et_commit_qq.getText().toString();
        String certificate = et_commit_certif.getText().toString();
        String account = et_commit_acount.getText().toString();
        String password = et_commit_password.getText().toString();

        if (count.equals("")){
            Toast.makeText(CommitMaterialStudentActivity.this, "请输入金额", Toast.LENGTH_SHORT).show();
        }
        else if (mouth.equals("")){
            Toast.makeText(CommitMaterialStudentActivity.this, "请输入月数", Toast.LENGTH_SHORT).show();
        }
        else if (name.equals("")){
            Toast.makeText(CommitMaterialStudentActivity.this, "请输入姓名", Toast.LENGTH_SHORT).show();
        }

        else if (phone.equals("")){
            Toast.makeText(CommitMaterialStudentActivity.this, "请输入电话", Toast.LENGTH_SHORT).show();
        }
        else if (qq.equals("")){
            Toast.makeText(CommitMaterialStudentActivity.this, "请输入qq", Toast.LENGTH_SHORT).show();
        }
        else if (certificate.equals("")){
            Toast.makeText(CommitMaterialStudentActivity.this, "请输入身份证号", Toast.LENGTH_SHORT).show();
        }

        final String applyId = "appid"+System.currentTimeMillis();
        StudentApplyInfo applyInfo = new StudentApplyInfo(applyId,name,qq,certificate,account,password,count,mouth,phone);
        applyInfo.setPhone(phone);


        MyUtils.showDialog("正在提交",this);
        applyInfo.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                MyUtils.hideDialog();
                Toast.makeText(CommitMaterialStudentActivity.this, "申请成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CommitMaterialStudentActivity.this,ApplySuccessActivity.class));
                MyApplication.sharedPreferences.edit().putString("applyId",applyId).commit();
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                MyUtils.hideDialog();
                Toast.makeText(CommitMaterialStudentActivity.this, "申请失败"+s, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
