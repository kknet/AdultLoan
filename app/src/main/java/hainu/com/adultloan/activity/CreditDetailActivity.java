package hainu.com.adultloan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hainu.com.adultloan.R;

public class CreditDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_detail);

    }


    public void back(View view){
        finish();
    }

    //联系客服
    public void contactService(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("isContant",true);
        startActivity(intent);
    }
}
