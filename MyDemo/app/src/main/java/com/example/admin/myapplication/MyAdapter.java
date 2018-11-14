package com.example.admin.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.loadingviewfinal.HeaderAndFooterRecyclerViewAdapter;
import cn.finalteam.loadingviewfinal.RecyclerViewFinal;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<FatherContent> fatherContentList;

    private List<List<ChildBean>> childBeanList = new ArrayList<>();
    private Context context;

    // 给个最底下的提交按钮
    private static final int NORMAL_VIEW = 0;
    private static final int FOOT_VIEW = 1;


    public MyAdapter(Context context,List list) {
        super();
        this.context = context;
        this.fatherContentList = list;
    }


    @Override
    public int getItemViewType(int position) {

        if (position == getItemCount() - 1) {
            return FOOT_VIEW;
        }
        return NORMAL_VIEW;
    }



    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        if (viewType == NORMAL_VIEW) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.item_topic_father, parent, false);
            return new ViewHolder(itemView, viewType);
        } else {
            View footView = LayoutInflater.from(context).inflate(R.layout.item_moretopic_foot, parent, false);
            return new ViewHolder(footView, viewType);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == NORMAL_VIEW) {
            final FatherContent fatherContent = fatherContentList.get(position);
            //设置第一个recycleview的题目
            holder.tvTitle.setText(Integer.toString(position + 1) + "." + fatherContent.getTitle() + "");
            //因为选项懒得设置不同的文字了 所以直接写死
            String[] strarr = {"选项1", "选项2", "选项3", "选项4"};
            List<String[]> listArray = new ArrayList<>();
            listArray.add(strarr);
            for (int i = 0; i < listArray.size(); i++) {
                List<ChildBean> list = new ArrayList<>();
                String[] thisArray = listArray.get(i);
                for (int j = 0; j < thisArray.length; j++) {
                    ChildBean choisBean = new ChildBean();
                    choisBean.setAns(thisArray[j]);
                    list.add(choisBean);
                }
                childBeanList.add(list);
            }

            final List<ChildBean> childBeans = childBeanList.get(position);
            //给选项的recycleList设置东西了
            holder.chilidList.setLayoutManager(new GridLayoutManager(context, 4));

            final CommonAdapter adapter = new CommonAdapter<ChildBean>(context, R.layout.item_choice_list, childBeans) {
                @Override
                public void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, ChildBean shop, int position) {
                    TextView checkBox = holder.getView(R.id.cb_select);
                    checkBox.setText(shop.getAns());
                    if (childBeans.get(position).isChecked()) {
                        childBeans.get(position).setChecked(true);
                        checkBox.setBackgroundResource(R.drawable.bu);
                        checkBox.setTextColor(Color.parseColor("#a8492e"));
                    } else {
                        childBeans.get(position).setChecked(false);
                        checkBox.setBackgroundResource(R.drawable.yiban);
                        checkBox.setTextColor(Color.parseColor("#7F7F7F"));
                    }
                }
            };


            holder.chilidList.setAdapter(adapter);

            holder.chilidList.setOnItemClickListener(new HeaderAndFooterRecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(RecyclerView.ViewHolder holder, int position) {
                    fatherContent.setSelectIndex(position);
                    for (int i = 0; i < childBeans.size(); i++) {
                        if (i == position) {

                            childBeans.get(i).setChecked(true);
                            adapter.notifyDataSetChanged();

                        } else {
                            childBeans.get(i).setChecked(false);

                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return fatherContentList.size()+1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        RecyclerViewFinal chilidList;
        RelativeLayout footView;

        public ViewHolder( View itemView , int viewType) {
            super(itemView);
            if (viewType == NORMAL_VIEW) {
                tvTitle = (TextView) itemView.findViewById(R.id.father_title);
                chilidList = (RecyclerViewFinal) itemView.findViewById(R.id.rv_child);
                //如果是footView那么给footView赋值。
            } else if (viewType == FOOT_VIEW) {
                footView = (RelativeLayout) itemView;
            }
        }


    }


    //让activity可以拿到这个list
    public List<FatherContent> getFatherContentList() {
        return fatherContentList;
    }
}
