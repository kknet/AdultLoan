package hainu.com.adultloan.activity;import android.content.Intent;import android.os.Bundle;import android.view.View;import android.widget.RelativeLayout;import hainu.com.adultloan.R;public class CreditPlatListActivity extends BaseActivity {    private static final String TAG = "CreditPlatListActivity";    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_credit_plat_list);        initView();    }    private void initView() {        RelativeLayout rl_sucess_first = (RelativeLayout) findViewById(R.id.rl_sucess_first);        RelativeLayout rl_sucess_first1 = (RelativeLayout) findViewById(R.id.rl_sucess_first1);        RelativeLayout rl_sucess_first2 = (RelativeLayout) findViewById(R.id.rl_sucess_first2);        RelativeLayout rl_sucess_first3 = (RelativeLayout) findViewById(R.id.rl_sucess_first3);        RelativeLayout rl_sucess_first4 = (RelativeLayout) findViewById(R.id.rl_sucess_first4);        MyOnclickListener myOnclickListener = new MyOnclickListener();        rl_sucess_first.setOnClickListener(myOnclickListener);        rl_sucess_first1.setOnClickListener(myOnclickListener);        rl_sucess_first2.setOnClickListener(myOnclickListener);        rl_sucess_first3.setOnClickListener(myOnclickListener);        rl_sucess_first4.setOnClickListener(myOnclickListener);    }    class MyOnclickListener implements View.OnClickListener{        @Override        public void onClick(View view) {            switch (view.getId()){                case R.id.rl_sucess_first:                    //startActivity(new Intent(CreditPlatListActivity.this, CreditDetailActivity.class));                    break;            }        }    }    public void back(View view){        finish();    }}