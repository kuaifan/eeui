package vip.kuaifan.weiui.ui.component.grid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.List;

import vip.kuaifan.weiui.R;
import vip.kuaifan.weiui.extend.module.weiuiCommon;
import vip.kuaifan.weiui.extend.module.weiuiParse;

public class GridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mInflater;
    private List<View> viewDatas;

    private OnItemClickListener mOnItemClickListener;

    private int curIndex;
    private int pageSize;
    private int columnsSize;
    private int paramsHeight;

    private boolean dividerShow;
    private int dividerColor;
    private int dividerWidth;

    public GridAdapter(Context context, List<View> viewDatas, int curIndex, int pageSize, int columnsSize) {
        mInflater = LayoutInflater.from(context);
        this.viewDatas = viewDatas;
        this.curIndex = curIndex;
        this.pageSize = pageSize;
        this.columnsSize = columnsSize;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseHolder(mInflater.inflate(R.layout.layout_weiui_grid_recyler_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BaseHolder) {
            int curPosition = position + curIndex * pageSize;
            BaseHolder mBaseHolder = (BaseHolder) holder;
            View view = viewDatas.get(curPosition);
            if (view != null) {
                ViewGroup parentViewGroup = (ViewGroup) view.getParent();
                if (parentViewGroup != null ) {
                    parentViewGroup.removeView(view);
                }
                if (dividerShow) {
                    if (position < columnsSize) {
                        mBaseHolder.v_top.setVisibility(View.GONE);
                    }else{
                        mBaseHolder.v_top.setBackgroundColor(dividerColor);
                        weiuiCommon.setViewWidthHeight(mBaseHolder.v_top, -1, dividerWidth);
                    }
                    if (position % columnsSize == 0) {
                        mBaseHolder.v_left.setVisibility(View.GONE);
                    }else{
                        ViewGroup.LayoutParams mParams = view.getLayoutParams();
                        if (mParams != null) {
                            paramsHeight = mParams.height;
                        }
                        mBaseHolder.v_left.setBackgroundColor(dividerColor);
                        weiuiCommon.setViewWidthHeight(mBaseHolder.v_left, dividerWidth, paramsHeight);
                    }
                }else{
                    mBaseHolder.v_top.setVisibility(View.GONE);
                    mBaseHolder.v_left.setVisibility(View.GONE);
                }
                if (weiuiParse.parseStr(view.getTag()).equals("__replenish")) {
                    mBaseHolder.l_main.setEnabled(false);
                    mBaseHolder.l_main.setFocusable(false);
                    mBaseHolder.l_main.setBackgroundColor(0x00000000);
                }
                mBaseHolder.l_main.addView(view);
            }
        }
    }

    @Override
    public int getItemCount() {
        return viewDatas.size() > (curIndex + 1) * pageSize ? pageSize : (viewDatas.size() - curIndex * pageSize);
    }

    public void setDivider(boolean isShow, int color, int width) {
        this.dividerShow = isShow;
        this.dividerColor = color;
        this.dividerWidth = width;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onClick(int pos, int position);
        void onLongClick(int pos, int position);
    }

    class BaseHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private View v_left, v_top;
        private FrameLayout l_main;

        BaseHolder(View itemView) {
            super(itemView);
            v_left = itemView.findViewById(R.id.v_left);
            v_top = itemView.findViewById(R.id.v_top);
            l_main = itemView.findViewById(R.id.l_main);
            l_main.setOnClickListener(this);
            l_main.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onClick(curIndex, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onLongClick(curIndex, getAdapterPosition());
            }
            return true;
        }
    }
}