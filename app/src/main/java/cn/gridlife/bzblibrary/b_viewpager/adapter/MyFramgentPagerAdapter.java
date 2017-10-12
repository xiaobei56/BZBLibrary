package cn.gridlife.bzblibrary.b_viewpager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by BZB on 2017/9/29.
 */
///////////////////////////////////////////////////////////////////////////
// FragmentPagerAdapter中的fragment实例在destroyItem的时候并没有真正释放fragment对象只是detach，
// 所以FragmentPagerAdapter消耗更多的内存，带来的好处就是效率更高一些。
// 所以得出这样的结论：FragmentPagerAdapter适用于页面比较少的情况，
// FragmentStatePagerAdapter适用于页面比较多的情况，
// 因此不同的场合选择合适的适配器才是正确的做法
///////////////////////////////////////////////////////////////////////////
public class MyFramgentPagerAdapter<T extends Fragment> extends FragmentPagerAdapter {
    private List<T> mList;
    private String[] mStrings;
    public MyFramgentPagerAdapter(FragmentManager fm,List<T> list,String[] titles) {
        super(fm);
        mList=list;
        mStrings=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStrings == null ? super.getPageTitle(position) : mStrings[position];
    }

}
