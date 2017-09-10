package cn.gridlife.bzblibrary;

import android.Manifest;
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

import cn.gridlife.bzblibrary.bean.MainListBean;
import cn.gridlife.bzblibrary.a_baidulocation.BDLocationActivity;

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
        bean = new MainListBean();
        bean.setId(1);
        bean.setTitle("百度定位Demo");
        bean.setContent("2017/9/7实现百度定位共功能");
        dataList.add(bean);

        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new ListViewItemClicked());


    }
    class ListViewItemClicked implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            startAC(dataList.get(i).getId());
        }
    }
    private void startAC(int id){
        switch (id)
        {
            case 1:
            startActivity(new Intent(this,BDLocationActivity.class));
            break;
        }
    }
    class MainListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if(dataList==null||dataList.isEmpty()){
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
            if(convertView==null){
                holder=new ViewHolder();
                convertView= LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main_item,null,true);
                holder.tvTitle=convertView.findViewById(R.id.tv_title);
                holder.tvContent=convertView.findViewById(R.id.tv_content);
                convertView.setTag(holder);
            }else {
                holder= (ViewHolder) convertView.getTag();
            }
            holder.tvTitle.setText(dataList.get(position).getTitle());
            holder.tvContent.setText(dataList.get(position).getContent());

            return convertView;
        }
    }
    class ViewHolder{
        TextView tvTitle;
        TextView tvContent;
    }

}
