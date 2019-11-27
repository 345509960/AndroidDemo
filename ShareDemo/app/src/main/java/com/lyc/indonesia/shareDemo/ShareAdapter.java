package com.lyc.indonesia.shareDemo;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by yeyao on 17/4/5 下午4:21
 */
public class ShareAdapter extends RecyclerView.Adapter<ShareAdapter.VH> {

    private final Context context;
    private List<ResolveInfo> items;


    public ShareAdapter(Context context, List<ResolveInfo> items) {
        this.context = context;
        this.items = items;

    }// end HomeListViewPrototype


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_share_grid, viewGroup, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, final int position) {
        // set native name of App to share
        vh.shareName.setText(items.get(position).activityInfo.loadLabel(context.getPackageManager()).toString());

        // share native image of the App to share
        vh.imageShare.setImageDrawable(items.get(position).activityInfo.applicationInfo.loadIcon(context.getPackageManager()));
        vh.imageShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener!=null){
                    mOnClickListener.onClick(position);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class VH extends RecyclerView.ViewHolder{
        public final TextView shareName;
        public final ImageView imageShare;

        public VH(View v) {
            super(v);
            shareName = v.findViewById(R.id.shareName);
            imageShare = v.findViewById(R.id.shareImage);
        }
    }



    public void setmOnClickListener(OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    private OnClickListener mOnClickListener;


    public interface OnClickListener{
       void onClick(int position);
    }
}
