package com.mecewe.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RvCardview rvAdapter;
    private List<String> datas = new ArrayList<>();
    private List<String> title = new ArrayList<>();
    public List<Item> icardList = new ArrayList<>();
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //配置初始数据
        num = 24;
        for (int i = 0; i < num; i++) {
            title.add(" " + i);
            datas.add(" " + i);
            title.set(i, (String.valueOf(i)));
            datas.set(i, (String.valueOf(i)));

            Item icard = new Item();
            if (i%2 == 0) {
                icard.setLocation("0");
            } else if (i%2 == 1) {
                icard.setLocation("1");
            }
            icardList.add(icard);

        }





        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        rvAdapter = new RvCardview(num,icardList, datas, title);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rvAdapter);
        rvAdapter.setItemClickListener(new RvCardview.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }


            @Override
            public void onTextClick(View view, int position) {
            }
        });

        rvAdapter.setOnSwitchClickListener(new RvCardview.OnSwitchClickListener() {
            @Override
            public void onClick(Item item, int position, boolean isChecked) {

                String t = MainActivity.this.title.get(position);
                String b = isChecked ? "打开" : "关闭";
                if (isChecked) {
                    Toast.makeText(MainActivity.this, b + "了" + t + "的开关", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, b + "了" + t + "的开关", Toast.LENGTH_SHORT).show();
                }
            }
        });


        rvAdapter.setOnButtonClickListener(new RvCardview.OnButtonClickListener() {
            @Override
            public void onClick(Item item, int position, View view) {
                String b = "";
                if (view instanceof Button) {

                    b = ((Button) view).getText().toString();
                }
                String t = MainActivity.this.title.get(position);

                Toast.makeText(MainActivity.this, "点击了" + t + "的" + b, Toast.LENGTH_SHORT).show();

                rvAdapter.notifyDataSetChanged();   //实时更新item中的数据


                //也可以通过view.getId()来判断是点击了哪一个button，然后进行对应的处理
            }
        });


    }
}
