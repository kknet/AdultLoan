package hainu.com.adultloan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import hainu.com.adultloan.R;
import hainu.com.adultloan.adapter.ArticlelistAdapter;
import hainu.com.adultloan.application.MyApplication;
import hainu.com.adultloan.bean.ArticleCollection;
import hainu.com.adultloan.bean.StrategyArticle;

public class CollectArticleActivity extends BaseActivity {

    private static final String TAG = "CollectArticleActivity";
    private ListView lv_strategy_articlelist;
    private List<StrategyArticle> articleListLis;
    private TextView tv_null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_article);
        initView();
    }

    private void initView() {
        lv_strategy_articlelist = (ListView) findViewById(R.id.lv_collected_articlelist);
        tv_null = (TextView) findViewById(R.id.tv_null);

        articleListLis = new ArrayList<>();


        BmobQuery<ArticleCollection> collectionQuery = new BmobQuery<>();
        collectionQuery.addWhereEqualTo("type",1); // 收藏
        collectionQuery.addWhereEqualTo("userId", MyApplication.sharedPreferences.getString("userId",""));
        collectionQuery.findObjects(this, new FindListener<ArticleCollection>() {
            @Override
            public void onSuccess(List<ArticleCollection> list) {
                BmobQuery<StrategyArticle> bmobQuery = new BmobQuery<>();
                Collection<String> collection = new ArrayList<>();
                for (int i=0;i<list.size();i++){
                    collection.add(list.get(i).getArticleId());
                }
                bmobQuery.addWhereContainedIn("articleId",collection);

                bmobQuery.findObjects(CollectArticleActivity.this, new FindListener<StrategyArticle>() {
                    @Override
                    public void onSuccess(List<StrategyArticle> list) {
                        Log.i(TAG,"list.size()2:"+list.size());
                        if (list.size()>0){
                            lv_strategy_articlelist.setVisibility(View.VISIBLE);
                            tv_null.setVisibility(View.INVISIBLE);
                        }

                        for (int i=0;i<list.size();i++){
                            articleListLis.add(list.get(i));
                            Log.i(TAG,"list.get(i):"+ articleListLis.get(i).toString());

                        }
                        lv_strategy_articlelist.setAdapter(new ArticlelistAdapter(CollectArticleActivity.this,articleListLis));
                    }

                    @Override
                    public void onError(int i, String s) {
                        Toast.makeText(CollectArticleActivity.this,"失败,请检查网络"+s, Toast.LENGTH_SHORT).show();
                    }
                });


            }

            @Override
            public void onError(int i, String s) {

            }
        });


        lv_strategy_articlelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StrategyArticle article = articleListLis.get(position);
                Log.i(TAG,article.toString());
                String articleurl = article.getArticleUrl();
                String objectId = article.getObjectId();
                Intent intent = new Intent(CollectArticleActivity.this,ShowArticleDetailActivity.class);
                intent.putExtra("articleurl",articleurl);
                Log.i(TAG,"articleurl:"+articleurl);
                Log.i(TAG,"article:"+article.getTitle());
                intent.putExtra("articleId",article.getArticleId());
                intent.putExtra("objectId",objectId);
                intent.putExtra("readCount",article.getReadCount());
                startActivity(intent);
            }
        });
    }

    public void back(View view){
        finish();
    }
}
