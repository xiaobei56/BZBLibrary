package cn.gridlife.bzblibrary.b_viewpager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import cn.gridlife.bzblibrary.R;
import cn.gridlife.bzblibrary.b_viewpager.bean.ViewPagerDataBean;

/**
 * Created by BZB on 2017/9/14.
 */

public class ViewPagerShow extends Activity {

    private ViewPager viewPager;
    private TextView tvShow;
    private LinearLayout llDot;
    private ViewPagerDataBean bean;
    private List<ViewPagerDataBean> list;
    private ImageView[] tips;
    BZBViewPagerAdapter adapter;
    /**
     * 装ImageView数组
     */
    private ImageView[] mImageViews;
    /**
     * 图片资源id
     */
    private int[] imgIdArray ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_actiivty_viewpager);
        list = getData();
        viewPager = (ViewPager) findViewById(R.id.vp_show);
        tvShow = (TextView) findViewById(R.id.tv_vp_show_title);
        llDot = (LinearLayout) findViewById(R.id.ll_dot_view);

        //载入图片资源ID
        imgIdArray = new int[]{R.mipmap.photo_one,R.mipmap.photo_two,R.mipmap.photo_three,R.mipmap.photo_four,R.mipmap.photo_five};
        //将图片装载到数组中
        mImageViews = new ImageView[imgIdArray.length];
        for(int i=0; i<mImageViews.length; i++){
            ImageView imageView = new ImageView(this);
            mImageViews[i] = imageView;
            imageView.setBackgroundResource(imgIdArray[i]);
        }

        tips = new ImageView[list.size()];
        for(int i=0; i<tips.length; i++){
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(25,25));
            tips[i] = imageView;
            if(i == 0){
                tips[i].setImageResource(R.drawable.dot_selected);
            }else{
                tips[i].setImageResource(R.drawable.dot_unselected);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(15,15);
            layoutParams.leftMargin = 2;
            layoutParams.rightMargin = 2;
            llDot.addView(tips[i], layoutParams);
        }
        adapter=new BZBViewPagerAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(list.size()*500);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setImageBackground(position % mImageViews.length);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private List<ViewPagerDataBean> getData() {
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            bean = new ViewPagerDataBean();
            bean.setId(i);
            bean.setTitle("Title:::::" + i);
            bean.setBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.photo_three));
            list.add(bean);
        }
        return list;
    }

    private String pictureName(int i) {
        String name;
        switch (i) {
            case 1:
                name = "one";
                break;
            case 2:
                name = "twe";
                break;
            case 3:
                name = "three";
                break;
            case 4:
                name = "four";
                break;
            case 5:
                name = "five";
                break;
            default:
                name = "one";

        }
        return name;
    }

    /**
     * 获取图片名称获取图片的资源id的方法
     *
     * @param imageName
     * @return
     */
    public int getResource(String imageName) {
        Context ctx = getBaseContext();
        int resId = getResources().getIdentifier(imageName, "mipmap", ctx.getPackageName());
        return resId;
    }

    class BZBViewPagerAdapter extends PagerAdapter {
        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            try {
                ((ViewPager)container).addView(mImageViews[position % mImageViews.length], 0);
            }catch(Exception e){
                //handler something
            }
            return mImageViews[position % mImageViews.length];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            ((ViewPager)container).removeView(mImageViews[position % mImageViews.length]);
        }
    }
    /**
     * 设置选中的tip的背景
     * @param selectItems
     */
    private void setImageBackground(int selectItems){
        for(int i=0; i<tips.length; i++){
            if(i == selectItems){
                tips[i].setImageResource(R.drawable.dot_selected);
            }else{
                tips[i].setImageResource(R.drawable.dot_unselected);
            }
        }
    }
}
