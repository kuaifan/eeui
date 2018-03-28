package vip.kuaifan.weiui.ui.component.recyler.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.taobao.weex.utils.WXUtils;

import java.util.ArrayList;
import java.util.List;

import vip.kuaifan.weiui.R;

import vip.kuaifan.weiui.ui.component.recyler.bean.SwipeButtonBean;

/**
 * Created by WDM on 2018/3/1.
 */

public class RecylerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "RecylerAdapter";

    private List<View> viewDatas = new ArrayList<>();
    private Context context;
    private int normalType = 0;
    private int footType = 1;
    private boolean hasMore = false;
    private boolean fadeFooter = false;
    private OnItemClickListener mOnItemClickListener;
    private List<SwipeButtonBean> mSwipeItems = new ArrayList<>();

    private boolean pullTips = true;
    private String pullTipsDefault = "正在加载数据...";
    private String pullTipsLoad = "正在加载更多...";
    private String pullTipsNo = "没有更多数据了";

    private String dividerColor = "#e8e8e8";
    private int dividerHeight = 1;

    public RecylerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == normalType) {
            return new NormalHolder(LayoutInflater.from(context).inflate(R.layout.layout_weiui_recyler_item_swipe, null));
        } else {
            return new FootHolder(LayoutInflater.from(context).inflate(R.layout.layout_weiui_recyler_footer, null));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalHolder) {
            NormalHolder normalHolder = ((NormalHolder) holder);
            View view = viewDatas.get(position);
            if (view != null) {
                ViewGroup parentViewGroup = (ViewGroup) view.getParent();
                if (parentViewGroup != null ) {
                    parentViewGroup.removeView(view);
                }
                if (normalHolder.v_container.getChildCount() > 0) {
                    normalHolder.v_container.removeAllViews();
                }
                normalHolder.v_container.addView(view);
            }
        } else if (holder instanceof FootHolder) {
            FootHolder footHolder = ((FootHolder) holder);
            if (!pullTips) {
                fadeFooter = true;
                footHolder.l_footer.setVisibility(View.GONE);
                return;
            }
            //
            footHolder.l_footer.setVisibility(View.VISIBLE);
            if (hasMore) {
                fadeFooter = false;
                footHolder.v_progress.setVisibility(View.VISIBLE);
                String tips = viewDatas.size() > 0 ? pullTipsLoad : pullTipsDefault;
                footHolder.v_tips.setText(tips);
                if (tips.isEmpty()) {
                    footHolder.v_tips.setVisibility(View.GONE);
                }
            } else {
                if (viewDatas.size() > 0) {
                    footHolder.v_progress.setVisibility(View.GONE);
                    footHolder.v_tips.setText(pullTipsNo);
                    if (pullTipsNo.isEmpty() || footHolder.v_tips.getVisibility() == View.GONE) {
                        fadeFooter = true;
                        footHolder.l_footer.setVisibility(View.GONE);
                    }
                }else{
                    fadeFooter = true;
                    footHolder.l_footer.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return viewDatas.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return footType;
        } else {
            return normalType;
        }
    }

    class NormalHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private FrameLayout v_container;
        private LinearLayout v_swipeView;
        private View v_divider, v_swipedivider;

        NormalHolder(View itemView) {
            super(itemView);
            v_container = itemView.findViewById(R.id.v_container);
            v_swipeView = itemView.findViewById(R.id.v_swipeview);
            v_divider = itemView.findViewById(R.id.v_divider);
            v_swipedivider = itemView.findViewById(R.id.v_swipedivider);
            //
            v_container.setOnClickListener(this);
            v_container.setOnLongClickListener(this);
            //
            if (mSwipeItems.size() > 0) {
                for (int i = 0; i < mSwipeItems.size(); i++) {
                    v_swipeView.addView(newView(mSwipeItems.get(i), i));
                }
            }
        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.v_container) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(getAdapterPosition());
                }
            }else if (view instanceof TextView){
                if (mOnItemClickListener != null) {
                    TextView temp = (TextView) view;
                    mOnItemClickListener.onSwipeClick(getAdapterPosition(), (int) temp.getTag(), temp.getText().toString());
                }
            }
        }

        @Override
        public boolean onLongClick(View view) {
            int id = view.getId();
            if (id == R.id.v_container) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onLongClick(getAdapterPosition());
                }
            }
            return true;
        }

        private View newView(SwipeButtonBean bean, int position) {
            TextView mView = new TextView(context);
            mView.setLayoutParams(new LinearLayout.LayoutParams(bean.getWidth() > 0 ? bean.getWidth() : LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            mView.setGravity(Gravity.CENTER);
            mView.setText(bean.getText());
            if (bean.getSize() > 0) {
                mView.setTextSize(bean.getSize());
            }
            if (bean.getPadding() > 0) {
                mView.setPadding(bean.getPadding(), 0, bean.getPadding(), 0);
            }
            if (!bean.getColor().isEmpty()) {
                mView.setTextColor(Color.parseColor(bean.getColor()));
            }
            if (!bean.getBackgroundColor().isEmpty()) {
                mView.setBackgroundColor(Color.parseColor(bean.getBackgroundColor()));
            }
            if (dividerHeight > 0) {
                v_divider.setVisibility(View.VISIBLE);
                v_divider.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dividerHeight));
                v_divider.setBackgroundColor(Color.parseColor(dividerColor));
                v_swipedivider.setVisibility(View.VISIBLE);
                v_swipedivider.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dividerHeight));
                v_swipedivider.setBackgroundColor(Color.parseColor(dividerColor));
            }else{
                v_divider.setVisibility(View.GONE);
                v_swipedivider.setVisibility(View.GONE);
            }
            //
            ViewGroup parentViewGroup = (ViewGroup) mView.getParent();
            if (parentViewGroup != null ) {
                parentViewGroup.removeView(mView);
            }
            //
            mView.setTag(position);
            mView.setOnClickListener(this);
            return mView;
        }
    }

    class FootHolder extends RecyclerView.ViewHolder {
        private LinearLayout l_footer;
        private ProgressBar v_progress;
        private TextView v_tips;

        FootHolder(View itemView) {
            super(itemView);
            l_footer = itemView.findViewById(R.id.l_footer);
            v_progress = itemView.findViewById(R.id.v_progress);
            v_tips = itemView.findViewById(R.id.v_tips);
            if (pullTips) {
                v_tips.setVisibility(View.VISIBLE);
            }else{
                v_tips.setVisibility(View.GONE);
            }
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
        void onLongClick(int position);
        void onSwipeClick(int position, int swipePosition, String swipeText);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setSwipeItems(List<SwipeButtonBean> buttonList) {
        this.mSwipeItems = buttonList;
    }

    public int getRealLastPosition() {
        return viewDatas.size();
    }

    public void updateList(View view, boolean hasMore) {
        if (view != null) {
            viewDatas.add(view);
        }
        this.hasMore = hasMore;
        notifyDataSetChanged();
    }

    public void removeList(View view, boolean hasMore) {
        if (view != null) {
            viewDatas.remove(view);
        }
        this.hasMore = hasMore;
        notifyDataSetChanged();
    }

    public boolean isFadeFooter() {
        return fadeFooter;
    }

    public void setPullTips(boolean var) {
        pullTips = var;
    }

    public void setPullTipsDefault(String var) {
        pullTipsDefault = var;
    }

    public void setPullTipsLoad(String var) {
        pullTipsLoad = var;
    }

    public void setPullTipsNo(String var) {
        pullTipsNo = var;
    }

    public void setDividerColor(String var) {
        dividerColor = var;
    }

    public void setDividerHeight(int var) {
        dividerHeight = var;
    }
}
