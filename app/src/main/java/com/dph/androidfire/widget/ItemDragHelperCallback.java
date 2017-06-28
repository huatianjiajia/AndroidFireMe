package com.dph.androidfire.widget;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.widget
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/26 16:30
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ItemDragHelperCallback extends ItemTouchHelper.Callback{
    private OnItemMoveListener mOnItemMoveListener;
    private boolean mIsLongPressEnabled;


    public boolean isLongPressEnabled() {
        return mIsLongPressEnabled;
    }

    public void setLongPressEnabled(boolean mIsLongPressEnabled) {
        this.mIsLongPressEnabled = mIsLongPressEnabled;
    }

    public interface OnItemMoveListener{
        boolean onItemMove(int fromPosition,int toPosition);
    }


    public ItemDragHelperCallback(OnItemMoveListener mOnItemMoveListener) {
        this.mOnItemMoveListener = mOnItemMoveListener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags=setDragFlags(recyclerView);
        int swipFlags=0;
        return makeMovementFlags(dragFlags,swipFlags);
    }

    private int setDragFlags(RecyclerView recyclerView) {
        int dragFlags;
        RecyclerView.LayoutManager layoutManager=recyclerView.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager||layoutManager instanceof StaggeredGridLayoutManager){
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        } else {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        }
        return dragFlags;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return !isDifferentItemViewType(viewHolder, target) &&
                mOnItemMoveListener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());

    }
    private boolean isDifferentItemViewType(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return viewHolder.getItemViewType() != target.getItemViewType();
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);

    }
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
