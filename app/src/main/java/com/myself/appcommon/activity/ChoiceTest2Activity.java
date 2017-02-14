package com.myself.appcommon.activity;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myself.appcommon.R;
import com.myself.appcommon.base.BaseActivity;
import com.myself.appcommon.pickerscrollview.bean.Pickers;
import com.myself.appcommon.pickerscrollview.views.PickerScrollView;

import java.util.ArrayList;
import java.util.List;

public class ChoiceTest2Activity extends BaseActivity {

    private Button bt_scrollchoose; // 滚动选择器按钮
    private PickerScrollView pickerscrlllview; // 滚动选择器
    private List<Pickers> list; // 滚动选择器数据
    private String[] id;
    private String[] name;

    private Button bt_yes; // 确定按钮
    private LinearLayout picker_rel; // 选择器布局
    private TextView tv_text;

    @Override
    public boolean needTranslucent() {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_choice_test2);
    }

    @Override
    public void initStaticData() {

    }

    @Override
    public void initData() {
        list = new ArrayList<Pickers>();
        id = new String[]{"1", "2", "3", "4", "5", "6"};
        name = new String[]{"中国银行", "农业银行", "招商银行", "工商银行", "建设银行", "民生银行"};
        for (int i = 0; i < name.length; i++) {
            list.add(new Pickers(name[i], id[i]));
        }
        // 设置数据，默认选择第一条
        pickerscrlllview.setData(list);
        pickerscrlllview.setSelected(0);
    }

    @Override
    public void initView() {
        bt_scrollchoose = (Button) findViewById(R.id.bt_scrollchoose);
        picker_rel = (LinearLayout) findViewById(R.id.picker_rel);
        pickerscrlllview = (PickerScrollView) findViewById(R.id.pickerscrlllview);
        bt_yes = (Button) findViewById(R.id.picker_yes);
        tv_text = (TextView) findViewById(R.id.tv_text);
    }

    @Override
    public void initListener() {
        pickerscrlllview.setOnSelectListener(pickerListener);
        bt_yes.setOnClickListener(onClickListener);
    }

    private Pickers mPickers;
    // 滚动选择器选中事件
    PickerScrollView.OnSelectListener pickerListener = new PickerScrollView.OnSelectListener() {

        @Override
        public void onSelect(Pickers pickers) {
            mPickers = pickers;
            String conetnt = "选择：" + pickers.getShowId() + "--银行：" + pickers.getShowConetnt();
//            System.err.println(conetnt);
//            Toast.makeText(ChoiceTest2Activity.this, conetnt, Toast.LENGTH_SHORT).show();
            tv_text.setText(conetnt);
        }
    };

    // 点击监听事件
    View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            tv_text.setText(mPickers.getShowConetnt());
        }
    };

}
