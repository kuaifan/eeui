package io.rong.imlib.library.message;

import android.content.Context;
import android.widget.RelativeLayout;

import io.rong.imlib.model.MessageContent;

public abstract class BaseMsgView extends RelativeLayout {

    public BaseMsgView(Context context) {
        super(context);
    }

    public abstract void setContent(MessageContent msgContent);
}
