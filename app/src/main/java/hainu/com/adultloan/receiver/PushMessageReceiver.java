package hainu.com.adultloan.receiver;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.push.PushConstants;
import cn.bmob.v3.listener.SaveListener;
import hainu.com.adultloan.R;
import hainu.com.adultloan.activity.ShowArticleDetailActivity;
import hainu.com.adultloan.application.MyApplication;
import hainu.com.adultloan.bean.ArticleCollection;

public class PushMessageReceiver extends BroadcastReceiver {
    private static final String TAG = "MyPushMessageReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra(PushConstants.EXTRA_PUSH_MESSAGE_STRING);

        /*推送的数据格式，json格式，alert为标题，articleurl为传递为要显示的网页内容
        {
            "articleId":"xxx",
            "alert": "xxxx",
            "articleurl": "http://xxx"
        }*/

        Log.i(TAG,"message:"+message);


        try {
            JSONObject jsonObject = new JSONObject(message);
            String alert = jsonObject.getString("alert");
            String articleurl = jsonObject.getString("articleurl");
            String articleId = jsonObject.getString("articleId");

            createNotify(alert,articleurl,context);
            insertToPushArticle(articleId,context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void createNotify(String alert, String articleurl, Context context) {
        // 发送通知
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);

        builder.setSmallIcon(R.drawable.logo); //设置图标
        builder.setTicker("宜人分期贷");
        builder.setContentTitle("宜人分期贷"); //设置标题
        builder.setContentText(alert); //消息内容
        builder.setWhen(System.currentTimeMillis()); //发送时间
        builder.setDefaults(Notification.DEFAULT_ALL); //设置默认的提示音，振动方式，灯光
        builder.setAutoCancel(true);//打开程序后图标消失


        Intent intent1 = new Intent(context, ShowArticleDetailActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent1.putExtra("articleurl",articleurl);


        //FLAG_UPDATE_CURRENT  更新当前的通知信息
        PendingIntent activity = PendingIntent.getActivity(context, 130, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(activity);

        Notification notification = builder.build();
        nm.notify(0, notification);
    }

    private void insertToPushArticle(String articleId, Context context) {
        final ArticleCollection articleCollection = new ArticleCollection();
        articleCollection.setUserId(MyApplication.sharedPreferences.getString("userId",""));
        articleCollection.setArticleId(articleId);
        articleCollection.setType(2);  //类型为推送
        articleCollection.setCollectId("cc" + System.currentTimeMillis());

        articleCollection.save(context, new SaveListener() {
            @Override
            public void onSuccess() {
                Log.i(TAG,"ok");
            }

            @Override
            public void onFailure(int i, String s) {
                Log.i(TAG,"fail"+i+","+s);
            }
        });

    }
}
