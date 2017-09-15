package com.example.administrator.mytestallhere.learn.recyclerview;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**@see #getMultiDataViewType(int, Object)
 * 如果多种类型需要重写这个方法getMultiDataViewType
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.VH> {
    public List<T> mData = new ArrayList<T>();
    public static final int TYPE_CLICKABLE_WITH_RPESSEFFECT=0;
    public ArrayList<View> mHeaderViews;

    public ArrayList<View> mFooterViews;

    public final int HEADER_TYPE = 4000;

    public final int FOOTER_TYPE = 6000;




    public ArrayList<Integer> mLayouts;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();

    public BaseRecyclerViewAdapter(List<T> list, OnItemClick<T> l, int layoutId) {
        this.mData = list;
        this.mLayouts = new ArrayList<>();
        this.mLayouts.add(layoutId);
        this.mOnItemClick = l;
    }
    public BaseRecyclerViewAdapter(List<T> list, OnItemClick<T> l, Integer... layoutIds) {
        this.mData = list;
        this.mLayouts = new ArrayList<>(Arrays.asList(layoutIds));
        this.mOnItemClick = l;
    }
    public BaseRecyclerViewAdapter(List<T> list, Integer... layoutIds) {
        this.mData = list;
        this.mLayouts = new ArrayList<>(Arrays.asList(layoutIds));
    }
    public BaseRecyclerViewAdapter(List<T> list) {
        this.mData = list;
    }
    public void  setItemLayouts( Integer... layoutIds){
        this.mLayouts = new ArrayList<>(Arrays.asList(layoutIds));
    }

    public BaseRecyclerViewAdapter(List<T> list, int layoutId) {
        this.mData = list;
        this.mLayouts = new ArrayList<>();
        this.mLayouts.add(layoutId);
    }
    public BaseRecyclerViewAdapter(List<T> list, OnItemClick<T> l, ArrayList<Integer> layoutIds) {
        this.mData = list;
        this.mLayouts = layoutIds;
        this.mOnItemClick = l;
    }

    public BaseRecyclerViewAdapter(List<T> list, ArrayList<Integer> layoutIds) {
        this.mData = list;
        this.mLayouts = layoutIds;
    }

    public void setDataList(List<T> list){
        mData = list;
    }
    public void addDataList(List<T> list){
        if(list ==null){
            return;
        }
        mData.addAll(list);
    }
    public List<T> getData(){
        return mData;
    }
    public T getDataItem(int postion){
        if(mData==null){
            return null;
        }
        return mData.get(postion);
    }
    public int getListDataCount(){
        if(mData ==null){
            return 0;
        }
        return mData.size();
    }

    public static class VH extends RecyclerView.ViewHolder {

        SparseArray<View> viewHolder;

        public VH(View itemView) {
            super(itemView);
        }

        public <V extends View> V get(int id) {
            if (viewHolder == null) {
                viewHolder = new SparseArray<View>();
            }
            View childView = viewHolder.get(id);
            if (childView == null) {
                childView = itemView.findViewById(id);
                viewHolder.put(id, childView);
            }
            return (V) childView;
        }
        public void setText(int tvId,CharSequence charSequence){
            TextView tv = get(tvId);
            if (tv != null) {
                tv.setText(charSequence);
            }
        }
        public void setTextColorStr(int tvId, Pair<String,String>... color$String){
            TextView tv = get(tvId);
            String source = "<font color=\"%1$s\">%2$s</font>";
            StringBuilder sb = new StringBuilder();
            for (Pair<String,String> p: color$String){
                sb.append(String.format(source,p.first,p.second));
            }
            tv.setText(Html.fromHtml(sb.toString()));
        }
        public void setTextColor(int tvId, Pair<String,Integer>... str$Color){
            TextView tv = get(tvId);
            StringBuilder sb = new StringBuilder();
            for (Pair<String,Integer> p: str$Color){
                sb.append(p.first);
            }
            SpannableStringBuilder builder = new SpannableStringBuilder(sb.toString());
            int start = 0;
            for (Pair<String,Integer> p: str$Color){
                if(TextUtils.isEmpty(p.first)){
                    continue;
                }
                ForegroundColorSpan fcs = new ForegroundColorSpan(p.second);
                builder.setSpan(fcs,start,start + p.first.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                start += p.first.length();

            }
            tv.setText(builder);
        }
        public void setTextWithFormat(int tvId, String source, CharSequence target){
            TextView tv = get(tvId);
            tv.setText(String.format(source,target));
        }
        public void setVisibility(int viewId,int visibility){
            View v = get(viewId);
            if(v !=null){
                v.setVisibility(visibility);
            }
        }
        public void setBackgroud(int viewId,Drawable drawable){
            View v = get(viewId);
            if(v !=null){
                //CompatUtil.setBackground(v,drawable);
            }
        }


        public void setTextColor(@ColorInt int color, @IdRes int ... ids){
            for(int id:ids){
                TextView tv = get(id);
                tv.setTextColor(color);
            }
        }
        public void setText(@IdRes int id, CharSequence text1, CharSequence text2, @ColorInt int color1, @ColorInt int color2){
            TextView tv = get(id);
            //TextSpan.setTextMultColor(tv,text1,text2,color1,color2);

        }

    }

    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        if(mData!=null && mData.size()!=0){

            return mData.size() + getHeaderCount() + getFooterCount();
        }
        return getHeaderCount() + getFooterCount();
    }

    public int getDataViewTypeCount(){
        return mLayouts.size() ;
    }
    final private int getDataViewType(int position){
        int dataPosition =position  - getHeaderCount();
        return getMultiDataViewType(dataPosition,mData.get(dataPosition));
    }
    /**
     * 如果多种类型需要重写这个方法
     * @param dataListPostion
     * @return
     */
    public  int getMultiDataViewType(int dataListPostion,T item){

        return 0;
    }
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int type) {
        VH holder = null;
        View v = null;
        if (type < HEADER_TYPE) {
            v = LayoutInflater.from(parent.getContext()).inflate(mLayouts.get(type), parent, false);
        }
        else if (isHeaderType(type)) {
            v = getHeaderView(type);
        }
        else if (isFooterType(type)) {
            v = getFooterView(type);
        }
        if (v == null) {
            throw new NullPointerException("  v == null " + type);
        }
        holder = new VH(v);
        return holder;

    }


    /**
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (isFooterPosition(position)) {
            return FOOTER_TYPE + position - mData.size() - getHeaderCount();
        }else if (isHeadPosition(position)) {
            return HEADER_TYPE + position;
        }
        return getDataViewType(position);
    }

    public boolean isHeadPosition(int position) {
        return mHeaderViews != null && position < mHeaderViews.size();
    }

    public boolean isFooterPosition(int position) {

        return mFooterViews != null && position >= getItemCount() - mFooterViews.size();

    }

    public View getHeaderView(int type) {
        if (isHeaderType(type)) {
            return mHeaderViews.get(type - HEADER_TYPE);
        }
        return null;
    }

    public View getFooterView(int type) {
        if (isFooterType(type)) {
            return mFooterViews.get(type - FOOTER_TYPE);
        }

        return null;
    }

    public boolean isHeaderType(int type) {
        return type >= HEADER_TYPE && type < FOOTER_TYPE;
    }

    public boolean isFooterType(int type) {
        return type >= FOOTER_TYPE;
    }

    public void addHeader(View v) {
        if (v == null || v.getParent() != null) {
            return;
        }
        if (mHeaderViews == null) {
            mHeaderViews = new ArrayList<View>();
        }
        if (!mHeaderViews.contains(v)) {
            mHeaderViews.add(v);
        }
    }

    public void addFooter(View v) {
        if (v == null || v.getParent() != null) {
            return;
        }
        if (mFooterViews == null) {
            mFooterViews = new ArrayList<View>();
        }
        if (!mFooterViews.contains(v)) {
            mFooterViews.add(v);
        }
    }
    public boolean removeFooter(View v){
        if(mFooterViews!=null && mFooterViews.size()!=0 && v!=null){
            if(mFooterViews.contains(v)){
                mFooterViews.remove(v);
                this.notifyDataSetChanged();
                return true;
            }

        }
        return false;
    }
    public boolean removeHeader(View v){
        if(mHeaderViews!=null && mHeaderViews.size()!=0&& v!=null){
            if(mHeaderViews.contains(v)){
                mHeaderViews.remove(v);
                this.notifyDataSetChanged();
                return true;
            }

        }
        return false;
    }
    public boolean isFooterAdded(View v){
        if(mFooterViews!=null && mFooterViews.size()!=0){
            return  mFooterViews.contains(v);
        }
        return false;

    }

    public int getHeaderCount() {
        return mHeaderViews == null ? 0 : mHeaderViews.size();
    }

    public int getFooterCount() {
        return mFooterViews == null ? 0 : mFooterViews.size();
    }

    @Override
    public void onBindViewHolder(VH viewHolder, int position) {
        viewHolder.itemView.setActivated(selectedItems.get(position, false));
        if (getItemViewType(position) < HEADER_TYPE) {
            ArrayList<Integer> mClickIds = bindDataToView(viewHolder, position, mData.get(position - getHeaderCount()));
            Click mClick = new Click(position, mData.get(position - getHeaderCount()));
            LongClick mLongClick = new LongClick(position, mData.get(position - getHeaderCount()));
            if (mClickIds!=null && mClickIds.size()!=0) {
                for (Integer id : mClickIds) {
                    if(id == viewHolder.itemView.getId()){
                        //setItemViewBack(viewHolder.itemView);
                    }
                    viewHolder.get(id).setOnClickListener(mClick);
                    viewHolder.get(id).setOnLongClickListener(mLongClick);
                }
            }
            else {
                viewHolder.itemView.setOnClickListener(mClick);
                viewHolder.itemView.setOnLongClickListener(mLongClick);
                //setItemViewBack(viewHolder.itemView);
            }
        }
    }
    private void setItemViewBack(View itemView){
        Drawable drawable = itemView.getBackground();
        boolean useSelector=false;
        if(drawable!=null&&drawable instanceof ColorDrawable){
            ColorDrawable colorDrawable =(ColorDrawable)drawable;
            if(colorDrawable.getColor()== Color.WHITE){
                useSelector =true;
            }
        }else if(drawable ==null){
            useSelector =true;
        }
        if(useSelector){
            //CompatUtil.setBackground(itemView, ActivityCompat.getDrawable(itemView.getContext(), R.drawable.ptr_rotate_arrow));
        }
    }
    public abstract ArrayList<Integer> bindDataToView(VH viewHolder, int position, T model);


    public  class GridSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
        @Override
        public int getSpanSize(int position) {
            if (isHeadPosition(position) || isFooterPosition(position)) {
                return gridLayoutManager.getSpanCount();
            }
            return 1;
        }
    }
    private GridLayoutManager gridLayoutManager;
    private GridSpanSizeLookup mGridSpanSizeLookup;
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            gridLayoutManager = ((GridLayoutManager) manager);
            if (mGridSpanSizeLookup == null) {
                mGridSpanSizeLookup = new GridSpanSizeLookup();
            }
            gridLayoutManager.setSpanSizeLookup(mGridSpanSizeLookup);
        }
    }
    @Override
    public void onViewAttachedToWindow(VH holder) {
        super.onViewAttachedToWindow(holder);
        if (isStaggeredGridLayout(holder)) {
            handleLayoutIfStaggeredGridLayout(holder, holder.getLayoutPosition());
        }
    }

    private boolean isStaggeredGridLayout(RecyclerView.ViewHolder holder) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            return true;
        }
        return false;
    }

    protected void handleLayoutIfStaggeredGridLayout(RecyclerView.ViewHolder holder, int position) {
        if (isHeadPosition(position) || isFooterPosition(position)) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            p.setFullSpan(true);
        }
    }
    public void toggleSelection(int pos) {
        if (selectedItems.get(pos, false)) {
            selectedItems.delete(pos);
        }
        else {
            selectedItems.put(pos, true);
        }
        notifyItemChanged(pos);

    }

    public void setSelected(int pos) {
        selectedItems.put(pos, true);
        notifyItemChanged(pos);

    }

    public void clearSelection(int pos) {
        if (selectedItems.get(pos, false)) {
            selectedItems.delete(pos);
        }
        notifyItemChanged(pos);
    }

    public void clearSelections() {
        if (selectedItems.size() > 0) {
            selectedItems.clear();
            notifyDataSetChanged();
        }
    }

    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    public List<Integer> getSelectedItemsPositions() {
        List<Integer> items = new ArrayList<Integer>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }

    public void removeItem(int pos) {
        mData.remove(pos);
    }
    public void removeItem(T item) {
        mData.remove(item);
    }
    public void clearDateList(){
        if(mData ==null){
            return;
        }
        mData.clear();
    }

    private OnItemClick<T> mOnItemClick;
    private OnItemLongClickListener onLongClickListener;

    public void setOnItemClickListener(OnItemClick<T> l) {
        this.mOnItemClick = l;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener l) {
        this.onLongClickListener = l;
    }


    private class LongClick implements View.OnLongClickListener {

        int mPosition;
        T mCurModel;
        public LongClick(int position,T t) {
            this.mPosition = position;
            this.mCurModel =t;
        }

        @Override
        public boolean onLongClick(View v) {
            //在监听器不为空的时候，进行回调
            if (onLongClickListener != null) {
                onLongClickListener.onLongClick(v, mPosition,mCurModel);
            }
            //返回true，消费掉该事件，阻止其继续传递
            return true;
        }
    }

    private class Click implements View.OnClickListener {
        int mPosition;

        T mCurModel;

        public Click(int position, T t) {
            this.mCurModel = t;
            this.mPosition = position;
        }



        @Override
        public void onClick(View v) {
            if (mOnItemClick != null) {
                mOnItemClick.onItemClick(v, mPosition, mCurModel);
            }
        }
    }

    public interface OnItemClick<T> {
        void onItemClick(View v, int position, T item);
    }

    public interface OnItemLongClickListener<T> {
        void onLongClick(View v, int position, T item);
    }
    public boolean isValideDataPosition(int position){
        if(mData!=null && mData.size()!=0){
            if(position>=0 && position<mData.size()){
                return true;
            }
        }
        return false;
    }
    public boolean isPositionChanged(int position,T item){
        if(mData!=null && mData.size()!=0){
            if(position == mData.indexOf(item)+getHeaderCount()){
                return false;
            }
        }
        return true;
    }

    public void removeItemWithAnimAndNotifyChanged(int position){
        notifyItemRemoved(position);
        removeItem(position-getHeaderCount());
        int count = getItemCount();
        if(position != count){
            notifyItemRangeChanged(position,count -position);
        }
    }



}