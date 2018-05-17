package vip.kuaifan.weiui.ui.component.recyler.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vip.kuaifan.weiui.R;

import vip.kuaifan.weiui.extend.module.weiuiCommon;

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

    private boolean pullTips = true;
    private String pullTipsDefault = "正在加载数据...";
    private String pullTipsLoad = "正在加载更多...";
    private String pullTipsNo = "没有更多数据了";

    public RecylerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == normalType) {
            return new NormalHolder(LayoutInflater.from(context).inflate(R.layout.layout_weiui_recyler_item, null));
        } else {
            return new FootHolder(LayoutInflater.from(context).inflate(R.layout.layout_weiui_recyler_footer, null));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
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

        NormalHolder(View itemView) {
            super(itemView);
            v_container = itemView.findViewById(R.id.v_container);
            //
            v_container.setOnClickListener(this);
            v_container.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.v_container && mOnItemClickListener != null) {
                mOnItemClickListener.onClick(getAdapterPosition());
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
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public int getRealLastPosition() {
        return viewDatas.size();
    }

    public void updateList(int index, View view, boolean hasMore) {
        if (view != null) {
            viewDatas.add(index, view);
        }
        this.hasMore = hasMore;
    }

    public void removeList(View view, boolean hasMore) {
        if (view != null) {
            viewDatas.remove(view);
        }
        this.hasMore = hasMore;
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
}
