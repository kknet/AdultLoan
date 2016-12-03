package hainu.com.adultloan.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.listener.SaveListener;
import hainu.com.adultloan.R;
import hainu.com.adultloan.application.MyApplication;
import hainu.com.adultloan.bean.Suggestion;


public class ComplaintFeedbackActivity extends BaseActivity {

	private ProgressDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_complaint_feedback);


	}

	public void submitSuggest(View view) {
		EditText et_feedback_input = (EditText) findViewById(R.id.et_feedback_input);
		String msg = et_feedback_input.getText().toString();
		if (!msg.isEmpty()){
			showDialog("正在提交");
			String phone = MyApplication.sharedPreferences.getString("phone","");
			Suggestion suggestion = new Suggestion(phone,msg);

			suggestion.save(this, new SaveListener() {
				@Override
				public void onSuccess() {
					hideDialog();
					Toast.makeText(ComplaintFeedbackActivity.this,"提交成功", Toast.LENGTH_SHORT).show();
					finish();
				}


				@Override
				public void onFailure(int i, String s) {
					hideDialog();
					Toast.makeText(ComplaintFeedbackActivity.this,"提交失败"+s, Toast.LENGTH_SHORT).show();
				}
			});
		}
		else {
			Toast.makeText(this,"请输入内容呀", Toast.LENGTH_SHORT).show();
		}


	}

	void showDialog(String message) {
		try {
			if (dialog == null) {
				dialog = new ProgressDialog(this);
				dialog.setCancelable(true);
			}
			dialog.setMessage(message);
			dialog.show();
		} catch (Exception e) {
			// 在其他线程调用dialog会报错
		}
	}

	void hideDialog() {
		if (dialog != null && dialog.isShowing())
			try {
				dialog.dismiss();
			} catch (Exception e) {
			}
	}


}

