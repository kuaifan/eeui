package com.lljjcoder.weiui.library.citylist.Toast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.weiui.library.citypickerview.R;


/**
 * author：admin on 2016/5/4 10:50
 * email：fangkaijin@gmail.com
 */
public class AlarmDailog extends Toast
{
    private Toast toast;
    private Context context;
    private TextView noticeText;

    public AlarmDailog(Context context)
    {
        super(context);
        this.context = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate( R.layout.dialog_alarm_ui, null);
        noticeText = (TextView) layout.findViewById( R.id.noticeText);
        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        //让Toast显示为我们自定义的样子
        toast.setView(layout);
    }

    public void setShowText(String dialogNotice)
    {
        noticeText.setText(dialogNotice);
    }

    public void show()
    {
        toast.show();
    }

}
