package com.myself.appcommon.alertdialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.myself.appcommon.R;

/**
 *
 */
public class IOSAlertDialog {
    public static final String TAG = IOSAlertDialog.class.getSimpleName();

    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private TextView txt_title;
    private TextView txt_msg;
    private EditText edittxt_result;
    private LinearLayout dialog_group;
    private LinearLayout dialog_head;
    private ImageView dialog_marBottom;
    private LinearLayout ll_style_1;
    private LinearLayout ll_style_2;
    private Button btn_neg;
    private Button btn_next_step;
    private Button btn_pos;
    private Button btn_previous_step;
    private Button btn_complete;
    private ImageView img_line;
    private Display display;
    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showEditText = false;
    private boolean showHeadLayout = false;
    private boolean showLayout = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;
    private boolean showNextBtn = false;
    private boolean showCompleteBtn = false;
    private boolean showButtonStyle = false;

    public IOSAlertDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public IOSAlertDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.ios_alertdialog, null);

        // 获取自定义Dialog布局中的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setVisibility(View.GONE);
        txt_msg = (TextView) view.findViewById(R.id.txt_msg);
        txt_msg.setVisibility(View.GONE);
        edittxt_result = (EditText) view.findViewById(R.id.edittxt_result);
        edittxt_result.setVisibility(View.GONE);
        dialog_group = (LinearLayout) view.findViewById(R.id.dialog_group);
        dialog_group.setVisibility(View.GONE);
        dialog_head = (LinearLayout) view.findViewById(R.id.dialog_head);
        dialog_head.setVisibility(View.GONE);
        dialog_marBottom = (ImageView) view.findViewById(R.id.dialog_marBottom);

        ll_style_1 = (LinearLayout) view.findViewById(R.id.ll_style_1);
        ll_style_1.setVisibility(View.GONE);
        ll_style_2 = (LinearLayout) view.findViewById(R.id.ll_style_2);
        ll_style_2.setVisibility(View.GONE);

        btn_neg = (Button) view.findViewById(R.id.btn_neg);
        btn_neg.setVisibility(View.GONE);
        btn_pos = (Button) view.findViewById(R.id.btn_pos);
        btn_pos.setVisibility(View.GONE);

        btn_previous_step = (Button) view.findViewById(R.id.btn_previous_step);
        btn_previous_step.setVisibility(View.GONE);
        btn_next_step = (Button) view.findViewById(R.id.btn_next_step);
        btn_next_step.setVisibility(View.GONE);
        btn_complete = (Button) view.findViewById(R.id.btn_complete);
        btn_complete.setVisibility(View.GONE);

        img_line = (ImageView) view.findViewById(R.id.img_line);
        img_line.setVisibility(View.GONE);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display.getWidth() * 0.85), LayoutParams.WRAP_CONTENT));

        return this;
    }

    public IOSAlertDialog setTitle(String title) {
        showTitle = true;
        if ("".equals(title)) {
            txt_title.setText("标题");
        } else {
            txt_title.setText(title);
        }
        return this;
    }

    public IOSAlertDialog setEditText(String msg) {
        showEditText = true;
        if ("".equals(msg)) {
            edittxt_result.setHint("内容");
        } else {
            edittxt_result.setText(msg);
        }
        return this;
    }

    public String getResult() {
        return edittxt_result.getText().toString();
    }

    public IOSAlertDialog setMsg(String msg) {
        showMsg = true;
        if ("".equals(msg)) {
            txt_msg.setText("内容");
        } else {
            txt_msg.setText(msg);
        }
        return this;
    }

    public IOSAlertDialog setHeadView(View view) {
        showHeadLayout = true;
        if (view == null) {
            showHeadLayout = false;
        } else
            dialog_head.addView(view, android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        return this;
    }

    public IOSAlertDialog setView(View view) {
        showLayout = true;
        if (view == null) {
            showLayout = false;
        } else
            dialog_group.addView(view, android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        return this;
    }

    public IOSAlertDialog removeView(View view) {
        showLayout = true;
        if (view == null) {
            showLayout = false;
        } else
            dialog_group.removeView(view);
        return this;
    }

    public IOSAlertDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    /**
     * PreviousStep
     *
     * @param text
     * @param listener
     * @return
     */
    public IOSAlertDialog setPreviousStepButton(String text, final OnClickListener listener) {
        showButtonStyle = true;
        if (!TextUtils.isEmpty(text)) {
            btn_previous_step.setText(text);
        }
        btn_previous_step.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setLayout(true);
                listener.onClick(v);
            }
        });
        return this;
    }

    /**
     * nextStep
     *
     * @param text
     * @param listener
     * @return
     */
    public IOSAlertDialog setNextStepButton(String text, final OnClickListener listener) {
        showNextBtn = true;
        showButtonStyle = true;
        if (!TextUtils.isEmpty(text)) {
            btn_next_step.setText(text);
        }
        btn_next_step.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setLayout(false);
                listener.onClick(v);
            }
        });
        return this;
    }

    /**
     * complete
     *
     * @param text
     * @param listener
     * @return
     */
    public IOSAlertDialog setCompleteButton(String text, final OnClickListener listener) {
        showButtonStyle = true;
        if (!TextUtils.isEmpty(text)) {
            btn_complete.setText(text);
        }
        btn_complete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    /**
     * 取消
     *
     * @param text
     * @param listener
     * @return
     */
    //hide
    public IOSAlertDialog setNegativeButton(final String text, final OnClickListener listener) {
        showNegBtn = true;
        showButtonStyle = false;
        if (!TextUtils.isEmpty(text)) {
            btn_neg.setText(text);
        }
        btn_neg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    /**
     * 确认
     *
     * @param text
     * @param listener
     * @return
     */
    //hide
    public IOSAlertDialog setPositiveButton(String text, final OnClickListener listener) {
        showPosBtn = true;
        showButtonStyle = false;
        if (!TextUtils.isEmpty(text)) {
            btn_pos.setText(text);
        }
        btn_pos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    public IOSAlertDialog setButtonStyle(int style) {
        if (style == 0)
            showButtonStyle = true;
        return this;
    }

    public void setLayout(boolean b) {
        if (!showTitle && !showMsg) {
            txt_title.setText("");
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showTitle) {
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showEditText) {
            edittxt_result.setVisibility(View.VISIBLE);
        }

        if (showMsg) {
            txt_msg.setVisibility(View.VISIBLE);
        }

        if (showHeadLayout) {
            dialog_head.setVisibility(View.VISIBLE);
            dialog_marBottom.setVisibility(View.VISIBLE);
        }

        if (showLayout) {
            dialog_group.setVisibility(View.VISIBLE);
            dialog_marBottom.setVisibility(View.VISIBLE);
        }

        if (showButtonStyle) {
            ll_style_1.setVisibility(View.VISIBLE);
            ll_style_2.setVisibility(View.GONE);
        } else {
            ll_style_1.setVisibility(View.GONE);
            ll_style_2.setVisibility(View.VISIBLE);
        }

        if (!showPosBtn && !showNegBtn) {
//            btn_pos.setText("确定");
            btn_pos.setText("");
            btn_pos.setVisibility(View.VISIBLE);
            // btn_pos.setBackgroundResource(R.drawable.alertdialog_single_selector);
            btn_pos.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if (showPosBtn && showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
//            btn_pos.setBackgroundResource(R.drawable.dialog_right_btn_select);
            btn_neg.setVisibility(View.VISIBLE);
//            btn_neg.setBackgroundResource(R.drawable.dialog_left_btn_select);
            img_line.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && !showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            // btn_pos.setBackgroundResource(R.drawable.alertdialog_single_selector);
        }

        if (!showPosBtn && showNegBtn) {
            btn_neg.setVisibility(View.VISIBLE);
            // btn_neg.setBackgroundResource(R.drawable.alertdialog_single_selector);
        }

        if (b) {
            btn_next_step.setVisibility(View.VISIBLE);
            btn_previous_step.setVisibility(View.GONE);
            btn_complete.setVisibility(View.GONE);
        } else {
            btn_next_step.setVisibility(View.GONE);
            btn_previous_step.setVisibility(View.VISIBLE);
            btn_complete.setVisibility(View.VISIBLE);
        }
    }

    public void show() {
        setLayout(true);
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }
}
