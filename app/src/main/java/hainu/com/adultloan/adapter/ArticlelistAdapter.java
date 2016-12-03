package hainu.com.adultloan.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;

import java.util.List;

import hainu.com.adultloan.R;
import hainu.com.adultloan.bean.StrategyArticle;


/**
 * Created by Administrator on 2016/7/26.
 */
public class ArticlelistAdapter extends BaseAdapter {
    private BitmapUtils bitmapUtils;
    private Activity activity;
    private List<StrategyArticle> data;

    public ArticlelistAdapter(Activity activity, List<StrategyArticle> data) {
        bitmapUtils = new BitmapUtils(activity);
        this.data = data;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final StrategyArticle article = data.get(position);
        View inflate = View.inflate(activity, R.layout.list_article_item,null);
        ImageView iv_readlist_artcileimage = (ImageView) inflate.findViewById(R.id.iv_readlist_artcileimage);
        TextView tv_readlist_title = (TextView) inflate.findViewById(R.id.tv_readlist_title);
        TextView tv_readlist_time = (TextView) inflate.findViewById(R.id.tv_readlist_time);
        TextView tv_readlist_count = (TextView) inflate.findViewById(R.id.tv_readlist_count);

        String imageurl = article.getimageUrl();
        bitmapUtils.display(iv_readlist_artcileimage,imageurl);

        tv_readlist_title.setText(article.getTitle());
        tv_readlist_time.setText(article.getTime());
        tv_readlist_count.setText("阅读量："+ article.getReadCount()+"");

        return inflate;
    }
}

