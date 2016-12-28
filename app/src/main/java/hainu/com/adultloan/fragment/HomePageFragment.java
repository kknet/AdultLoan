package hainu.com.adultloan.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import hainu.com.adultloan.R;
import hainu.com.adultloan.activity.CommitMaterialStep1Activity;
import hainu.com.adultloan.activity.CommitMaterialStudentActivity;
import hainu.com.adultloan.activity.CreditPlatListActivity;
import hainu.com.adultloan.activity.HomeActivity;
import hainu.com.adultloan.application.MyApplication;

/**
 * Created by Administrator on 2016/7/7.
 */
public class HomePageFragment  extends Fragment {

    private static final String TAG = "StrategyPageFragment";
    private View inflate;
    private ViewPager vp_home_msg;
    int previousSelectedPosition = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.frgament_page_home, new LinearLayout(getActivity()),false);

        initView();



        return inflate;
    }

    private void initView() {
        initViewPage();

        RelativeLayout rl_home_creditClass = (RelativeLayout) inflate.findViewById(R.id.rl_home_creditClass);
        RelativeLayout rl_home_consult = (RelativeLayout) inflate.findViewById(R.id.rl_home_consult);

        LinearLayout ll_type_car = (LinearLayout) inflate.findViewById(R.id.ll_type_car);
        LinearLayout ll_type_house = (LinearLayout) inflate.findViewById(R.id.ll_type_house);
        LinearLayout ll_type_xindai = (LinearLayout) inflate.findViewById(R.id.ll_type_xindai);
        LinearLayout ll_type_card = (LinearLayout) inflate.findViewById(R.id.ll_type_card);
        LinearLayout ll_type_student = (LinearLayout) inflate.findViewById(R.id.ll_type_student);
        LinearLayout ll_type_other = (LinearLayout) inflate.findViewById(R.id.ll_type_other);

        HomeOnClickListener homeOnClickListener = new HomeOnClickListener();
        rl_home_creditClass.setOnClickListener(homeOnClickListener);
        rl_home_consult.setOnClickListener(homeOnClickListener);

        ll_type_car.setOnClickListener(homeOnClickListener);
        ll_type_house.setOnClickListener(homeOnClickListener);
        ll_type_xindai.setOnClickListener(homeOnClickListener);
        ll_type_card.setOnClickListener(homeOnClickListener);
        ll_type_student.setOnClickListener(homeOnClickListener);
        ll_type_other.setOnClickListener(homeOnClickListener);

    }
    private void initViewPage() {
        vp_home_msg = (ViewPager) inflate.findViewById(R.id.vp_home_msg);

        final LinearLayout ll_home_point = (LinearLayout) inflate.findViewById(R.id.ll_home_point);

        for (int i = 0; i < 4; i++){
            View pointView = new View(getActivity());
            pointView.setBackgroundResource(R.drawable.selector_bg_point);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30, 30);
            if(i != 0)
                layoutParams.leftMargin = 10;
            pointView.setEnabled(false);
            ll_home_point.addView(pointView, layoutParams);
        }

        vp_home_msg.setAdapter(new MyViewPageAdapter());

        vp_home_msg.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int newPosition = position % 4;
                ll_home_point.getChildAt(previousSelectedPosition).setEnabled(false);
                ll_home_point.getChildAt(newPosition).setEnabled(true);
                previousSelectedPosition  = newPosition;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                vp_home_msg.setCurrentItem(vp_home_msg.getCurrentItem() + 1);
                            }
                        });
                    }
                }
            }
        }.start();
    }

    class MyViewPageAdapter extends PagerAdapter {
        int []iamges = new int[]{R.drawable.viewpage02,R.drawable.viewpage01,R.drawable.viewpage03,R.drawable.viewpage04};

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int newPosition = position % 4;
            ImageView imageView = new ImageView(getActivity());
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inSampleSize = 2;
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), iamges[newPosition], options);
            imageView.setImageBitmap(bitmap);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
    class HomeOnClickListener implements View.OnClickListener{


        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.rl_home_creditClass:
                    getActivity().startActivity(new Intent(getActivity(), CreditPlatListActivity.class));
                    break;

                case R.id.rl_home_consult:
                    HomeActivity activity = (HomeActivity) getActivity();
                    activity.setCurrentItem(1);
                    ConsultPageFragment consultPageFragment = (ConsultPageFragment) activity.pageFragments.get(1);
                    consultPageFragment.switchState(1);
                    break;

                case R.id.ll_type_car:
                    Intent intent1 = new Intent(getActivity(), CommitMaterialStep1Activity.class);
                    intent1.putExtra("applyType","车贷");
                    getActivity().startActivity(intent1);
                    break;
                case R.id.ll_type_house:
                    Intent intent2 = new Intent(getActivity(), CommitMaterialStep1Activity.class);
                    intent2.putExtra("applyType","房贷");
                    getActivity().startActivity(intent2);;
                    break;
                case R.id.ll_type_xindai:
                    Intent intent3 = new Intent(getActivity(), CommitMaterialStep1Activity.class);
                    intent3.putExtra("applyType","信贷");
                    getActivity().startActivity(intent3);
                    break;
                case R.id.ll_type_card:
                    Intent intent4 = new Intent(getActivity(), CommitMaterialStep1Activity.class);
                    intent4.putExtra("applyType","信用卡申请");
                    getActivity().startActivity(intent4);
                    break;
                case R.id.ll_type_student:
                    getActivity().startActivity(new Intent(getActivity(), CommitMaterialStudentActivity.class));
                    break;
                case R.id.ll_type_other:
                    Intent intent5 = new Intent(getActivity(), CommitMaterialStep1Activity.class);
                    intent5.putExtra("applyType","其他");
                    getActivity().startActivity(intent5);
                    break;
            }
        }
    }



}