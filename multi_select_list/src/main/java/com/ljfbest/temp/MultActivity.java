package com.ljfbest.temp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MultActivity extends Activity implements OnClickListener {
    private static final String TAG = "multi";
    private GridView lv_data;
    private Button btAll, btConfirm, btCancel;
    private ArrayList<String> mult_data;
    private MultAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mult);
        lv_data = (GridView) findViewById(R.id.lv_data);
        btAll = (Button) findViewById(R.id.bt_all);
        btConfirm = (Button) findViewById(R.id.bt_confirm);
        btCancel = (Button) findViewById(R.id.bt_cancel);
        btAll.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        btConfirm.setOnClickListener(this);
        mult_data = new ArrayList<>();
        for (int i = 0; i < 90; i++) {

            mult_data.add("条" + i);
        }

        adapter = new MultAdapter();
        lv_data.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv_data.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //全选
            case R.id.bt_all:
                for (int i = 0; i < lv_data.getAdapter().getCount(); i++) {
                    lv_data.setItemChecked(i, true);
                }
                break;
            //清空
            case R.id.bt_cancel:
                for (int i = 0; i < lv_data.getAdapter().getCount(); i++) {
                    lv_data.setItemChecked(i, false);
                }
                break;
            // 确 定
            case R.id.bt_confirm:

                //所有选择的数据都在checkedItemIds数组里面
                long[] checkedItemIds = lv_data.getCheckedItemIds();
                int checkedItemPosition = lv_data.getCheckedItemPosition();
                Log.e(TAG, "onClick: " + checkedItemIds.length + checkedItemPosition);
                for (int i = 0; i < checkedItemIds.length; i++) {
                    Log.e(TAG, "checkedItemIds: " + checkedItemIds[i]);
                }
                break;
        }
    }

    class MultAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mult_data.size();
        }

        //设置固定的ID
        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public Object getItem(int i) {
            return mult_data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            View  viewItem = View.inflate(MultActivity.this, R.layout.item_data, null);
            TextView tv = (TextView) viewItem.findViewById(R.id.tv);
            tv.setText(mult_data.get(position));
            return viewItem;
        }
    }
}
