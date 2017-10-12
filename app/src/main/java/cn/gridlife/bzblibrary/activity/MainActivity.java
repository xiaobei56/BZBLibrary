package cn.gridlife.bzblibrary.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.gridlife.bzblibrary.R;
import cn.gridlife.bzblibrary.b_viewpager.ViewPagerShow;
import cn.gridlife.bzblibrary.bean.MainListBean;
import cn.gridlife.bzblibrary.a_baidulocation.BDLocationActivity;
import cn.gridlife.bzblibrary.c_function.FunctionActivity;
import cn.gridlife.bzblibrary.d_voice_to_text.VoiceToTextActivity;

/**
 * Created by BZB on 2017/9/7.
 */
public class
MainActivity extends Activity {


    private ListView listView;
    private List<MainListBean> dataList;
    private MainListBean bean;
    private MainListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();


    }

    private void initView() {
        listView = (ListView) findViewById(R.id.lv);

    }

    private void initData() {
        if (adapter == null) {
            adapter = new MainListAdapter();
        }
        listView.setAdapter(adapter);
        dataList = new ArrayList<>();
        ///////////////////////////////////////////////////////////////////////////
        // 增加条目
        ///////////////////////////////////////////////////////////////////////////
        addData(1, "百度定位Demo", "2017/9/7实现百度定位共功能");
        addData(2, "ViewPager展示", "首页ViewPager展示信息");
        addData(3, "函数", "哥哥");
        addData(4,"语音转文字","基于思必驰语音sdk开发");
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new ListViewItemClicked());


    }

    private void addData(int id, String title, String content) {
        bean = new MainListBean();
        bean.setId(id);
        bean.setTitle(title);//"百度定位Demo"
        bean.setContent(content);//"2017/9/7实现百度定位共功能"
        dataList.add(bean);
    }

    class ListViewItemClicked implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            startAC(dataList.get(i).getId());
        }
    }

    private void startAC(int id) {
        switch (id) {
            ///////////////////////////////////////////////////////////////////////////
            // 设置点击事件
            ///////////////////////////////////////////////////////////////////////////
            case 1:
                startActivity(new Intent(this, BDLocationActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, ViewPagerShow.class));
                break;
            case 3:
                startActivity(new Intent(this, FunctionActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, VoiceToTextActivity.class));
        }
    }

    class MainListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (dataList == null || dataList.isEmpty()) {
                return 0;
            }
            return dataList.size();
        }

        @Override
        public Object getItem(int i) {
            return dataList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return dataList.get(i).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main_item, null, true);
                holder.tvTitle = convertView.findViewById(R.id.tv_title);
                holder.tvContent = convertView.findViewById(R.id.tv_content);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvTitle.setText(dataList.get(position).getTitle());
            holder.tvContent.setText(dataList.get(position).getContent());

            return convertView;
        }
    }

    class ViewHolder {
        TextView tvTitle;
        TextView tvContent;
    }

}
