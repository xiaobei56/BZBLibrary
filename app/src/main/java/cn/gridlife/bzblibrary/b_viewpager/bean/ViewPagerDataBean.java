package cn.gridlife.bzblibrary.b_viewpager.bean;

import android.graphics.Bitmap;

/**
 * Created by BZB on 2017/9/14.
 */

public class ViewPagerDataBean {
    int id;
    String title;
    Bitmap bitmap;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
