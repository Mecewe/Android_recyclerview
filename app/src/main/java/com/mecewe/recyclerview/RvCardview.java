package com.mecewe.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class RvCardview extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<String> datas;
    private List<String> title;
    private List<Item> icardList;
    private OnSwitchClickListener mOnSwitchClickListener;
    private OnButtonClickListener mOnButtonClickListener;
    public List<Type> typeList = new ArrayList<Type>();
    private int num;

    private List<Type> bt1List = new ArrayList<>();
    private List<Type> bt2List = new ArrayList<>();
    private List<Type> bt3List = new ArrayList<>();


    //标记下选中和未选中这两种状态
    public enum Type{
        Checked, UnCheck
    }

    public RvCardview(int num,List<Item> icardList, List<String> datas, List<String> title) {
        this.num = num;
        this.title = title;
        this.datas = datas;
        this.icardList = icardList;
        initData();
    }

    //初始化button数据  暂时假设均为关闭
    private void initData() {
        for (int i = 0; i < num; i++) {
            Type type = Type.UnCheck;
            typeList.add(type);
            bt1List.add(Type.UnCheck);
            bt2List.add(Type.UnCheck);
            bt3List.add(Type.UnCheck);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.rvc1_item_cardview, parent, false);
            return new Item1ViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.rvc2_item_cardview, parent, false);
            return new Item2ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof Item1ViewHolder) {
            ((Item1ViewHolder) holder).mTextView1.setText(title.get(position));
            ((Item1ViewHolder) holder).mTextView2.setText(datas.get(position));

            ((Item1ViewHolder) holder).aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (buttonView.isPressed()) {

                        if (mOnSwitchClickListener != null) {
                            mOnSwitchClickListener.onClick(icardList.get(position), position, isChecked);
                            if (isChecked){
                                typeList.set(position, Type.Checked);
                            }
                            else{
                                typeList.set(position, Type.UnCheck);
                            }
                        }
                    } else {
                        return;
                    }
                }
            });

            //保留滑动时原有位置效果不变
            if (typeList.get(position).equals(Type.Checked) ) {
                ((Item1ViewHolder) holder).aSwitch.setChecked(true);
            } else if (typeList.get(position).equals(Type.UnCheck)) {
                ((Item1ViewHolder) holder).aSwitch.setChecked(false);
            } else {
                ((Item1ViewHolder) holder).aSwitch.setChecked(false);
            }
        } else if (holder instanceof Item2ViewHolder) {
            ((Item2ViewHolder) holder).mTextView1.setText(title.get(position));
            ((Item2ViewHolder) holder).mTextView2.setText(datas.get(position));
            ((Item2ViewHolder) holder).mTextView2.setText(datas.get(position));
            ((Item2ViewHolder) holder).button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnButtonClickListener != null) {
                        mOnButtonClickListener.onClick(icardList.get(position), position, v);
                        bt1List.set(position, Type.Checked);
                        bt2List.set(position, Type.UnCheck);
                        bt3List.set(position, Type.UnCheck);
                    }
                }
            });
            ((Item2ViewHolder) holder).button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnButtonClickListener != null) {
                        mOnButtonClickListener.onClick(icardList.get(position), position, v);
                        bt1List.set(position, Type.UnCheck);
                        bt2List.set(position, Type.Checked);
                        bt3List.set(position, Type.UnCheck);
                    }
                }
            });
            ((Item2ViewHolder) holder).button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnButtonClickListener != null) {
                        mOnButtonClickListener.onClick(icardList.get(position), position, v);
                        bt1List.set(position, Type.UnCheck);
                        bt2List.set(position, Type.UnCheck);
                        bt3List.set(position, Type.Checked);
                    }
                }
            });

            //实现按钮互斥，同时保持滑动时按钮位置效果不变
            if ((bt1List.size() !=0) && (bt2List.size() !=0) &&(bt3List.size() !=0) && (bt1List.get(position).equals(Type.Checked))&&(bt2List.get(position).equals(Type.UnCheck))&&(bt3List.get(position).equals(Type.UnCheck))) {
                ((Item2ViewHolder) holder).button1.setEnabled(false);
                ((Item2ViewHolder) holder).button2.setEnabled(true);
                ((Item2ViewHolder) holder).button3.setEnabled(true);
            } else if ((bt1List.size() !=0) && (bt2List.size() !=0) &&(bt3List.size() !=0) && (bt1List.get(position).equals(Type.UnCheck))&&(bt2List.get(position).equals(Type.Checked))&&(bt3List.get(position).equals(Type.UnCheck))) {
                ((Item2ViewHolder) holder).button1.setEnabled(true);
                ((Item2ViewHolder) holder).button2.setEnabled(false);
                ((Item2ViewHolder) holder).button3.setEnabled(true);
            } else if ((bt1List.size() !=0) && (bt2List.size() !=0) &&(bt3List.size() !=0) && (bt1List.get(position).equals(Type.UnCheck))&&(bt2List.get(position).equals(Type.UnCheck))&&(bt3List.get(position).equals(Type.Checked))) {
                ((Item2ViewHolder) holder).button1.setEnabled(true);
                ((Item2ViewHolder) holder).button2.setEnabled(true);
                ((Item2ViewHolder) holder).button3.setEnabled(false);
            } else {
                ((Item2ViewHolder) holder).button1.setEnabled(true);
                ((Item2ViewHolder) holder).button2.setEnabled(true);
                ((Item2ViewHolder) holder).button3.setEnabled(true);
            }


        }
    }

    //通过icardList选取合适的item
    @Override
    public int getItemViewType(int i) {
        if (icardList.get(i).getLocation().equals("0")) {
            return 0;
        }
        else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public OnItemClickListener itemClickListener;

    /**
     * 设置接口
     *
     * @param itemClickListener
     */
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * 点击事件接口
     */
    public interface OnItemClickListener {
        //item的点击事件
        void onItemClick(View view, int position);

        //item中文字的点击事件
        void onTextClick(View view, int position);
    }

    /**
     * 按钮点击事件接口
     */
    public interface OnButtonClickListener {
        void onClick(Item item, int position, View view);
    }

    /**
     * 开关点击事件接口
     */
    public interface OnSwitchClickListener {

        void onClick(Item item, int position, boolean isChecked);
    }

    public static class Item1ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView1;
        TextView mTextView2;
        Switch aSwitch;

        public Item1ViewHolder(View itemView) {
            super(itemView);
            mTextView1 = (TextView) itemView.findViewById(R.id.tv_name);
            mTextView2 = (TextView) itemView.findViewById(R.id.tv_data1);
            aSwitch = (Switch) itemView.findViewById(R.id.switch1);

        }
    }

    public class Item2ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView1;
        TextView mTextView2;
        Button button1, button2, button3;

        public Item2ViewHolder(View itemView) {
            super(itemView);
            mTextView1 = (TextView) itemView.findViewById(R.id.tv_name);
            mTextView2 = (TextView) itemView.findViewById(R.id.tv_data1);
            button1 = (Button) itemView.findViewById(R.id.bt_1);
            button2 = (Button) itemView.findViewById(R.id.bt_2);
            button3 = (Button) itemView.findViewById(R.id.bt_3);

        }
    }


    public void setOnSwitchClickListener(OnSwitchClickListener onSwitchClickListener) {
        mOnSwitchClickListener = onSwitchClickListener;
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        mOnButtonClickListener = onButtonClickListener;
    }
}



